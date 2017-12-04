/*
 * The MIT License
 *
 * Copyright 2017 Daniel Bergqvist.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package emojicode.runtime;

import emojicode.SourcePosition;
import emojicode.code.EmojiPackageUserDefinied;
import emojicode.code.Parent;

/**
 *
 * @author Daniel Bergqvist
 */
public final class Debugger {
    
    private static final Debugger INSTANCE = new Debugger();
    
    private final Object lock = new Object();
    private boolean wasSignalled = false;
    
    private DebugActions debugActions;
    
    private volatile boolean programIsRunning = false;
    private volatile boolean pauseOnEveryStep = false;
    private volatile boolean stopProgram  = true;
    private int runToCursor = -1;
//    private boolean stepByStep = true;
    
//    private Thread programThread;
    
    
    public static Debugger getInstance() {
        return INSTANCE;
    }
    
    
    private Debugger() {
    }
    
    
    private void doWait() {
        synchronized (lock) {
            while (!wasSignalled) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    private void doNotify() {
        synchronized (lock) {
            wasSignalled = true;
            lock.notify();
        }
    }


    public void stopDebugger() {
        System.out.println("BBB");
        stopProgram = true;
        doNotify();
    }
    
    
    public boolean isProgramRunning() {
        return programIsRunning;
    }
    
    
    public void setPauseOnEveryStep(final boolean doPauseOnEveryStep) {
        this.pauseOnEveryStep = doPauseOnEveryStep;
    }
    
    
    public void runProgram(final EmojiPackageUserDefinied emojiPackage,
                           final DebugActions debugActions)
            throws DebuggerException {
        
        if (programIsRunning) {
            throw new DebuggerException.DebuggerException_ProgramIsRunning();
        }
        this.debugActions = debugActions;
        stopProgram = false;
        this.runToCursor = -1;
        
        new Thread(() -> {
            try {
                programIsRunning = true;
                emojiPackage.run();
                this.debugActions.programEnded();
            } finally {
                programIsRunning = false;
            }
        }).start();
    }
    
    
    public void runProgramToCursor(final EmojiPackageUserDefinied emojiPackage,
                                   final DebugActions debugActions,
                                   final int runToCursor)
            throws DebuggerException {
        
        if (programIsRunning) {
            throw new DebuggerException.DebuggerException_ProgramIsRunning();
        }
        this.debugActions = debugActions;
        stopProgram = false;
        this.runToCursor = runToCursor;
        
        new Thread(() -> {
            try {
                programIsRunning = true;
                emojiPackage.run();
                this.debugActions.programEnded();
            } finally {
                programIsRunning = false;
            }
        }).start();
    }
    
    
    public void step(final Parent parent,
                     final SourcePosition startPosition,
                     final SourcePosition middlePosition,
                     final SourcePosition endPosition) {
        
        if (startPosition == null) {
            throw new IllegalArgumentException("startPosition must not be null");
        }
        if (endPosition == null) {
            throw new IllegalArgumentException("endPosition must not be null");
        }
        
        if (stopProgram) {
            throw new StopProgramException();
        }
        
        if (pauseOnEveryStep || ((runToCursor >= 0) && runToCursor == startPosition.getIndex())) {
            debugActions.next(parent, startPosition, middlePosition, endPosition);
            
            doWait();
            
            // The user might have decided to stop the program while we have waited
            if (stopProgram) {
                throw new StopProgramException();
            }
        }
    }
    
    
    public void continueRun() {
        doNotify();
    }
    
    
    public void continueRunToCursor(final int runToCursor) {
        this.runToCursor = runToCursor;
        doNotify();
    }
    
    
    
    public interface DebugActions {
        
        void next(Parent parent,
                  SourcePosition startPosition,
                  SourcePosition middlePosition,
                  SourcePosition endPosition);
        
        void programEnded();
        
        void programAborted(SourcePosition position);
        
        void programError(SourcePosition position);
        
    }
    
    
    
    public final class DebugActionsSwingSafe {
        
        private final DebugActions debugActions;
        
        
        public DebugActionsSwingSafe(final DebugActions debugActions) {
            this.debugActions = debugActions;
        }
        
        
        public void next(final Parent parent,
                         final SourcePosition startPosition,
                         final SourcePosition middlePosition,
                         final SourcePosition endPosition) {
            
            java.awt.EventQueue.invokeLater(() -> {
                debugActions.next(parent, startPosition, middlePosition, endPosition);
            });
        }
        
        
        public void programEnded() {
            java.awt.EventQueue.invokeLater(() -> {
                debugActions.programEnded();
            });
        }
        
        
        public void programAborted(final SourcePosition position) {
            java.awt.EventQueue.invokeLater(() -> {
                debugActions.programAborted(position);
            });
        }
        
        
        public void programError(final SourcePosition position) {
            java.awt.EventQueue.invokeLater(() -> {
                debugActions.programError(position);
            });
        }
        
    }
    
    
    
    // This exception is thrown by the debugger when the program is aborted.
    // This exception should never be catched by anyone else than the debugger
    // itself. It is therefore private. However, it is possible to catch it if
    // a catch statment catches Throwable or Exception, but it should not be
    // done.
    private class StopProgramException extends RuntimeException {
        
        private static final long serialVersionUID = 1L;
    }
    
    
    
    // http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
    private class MyWaitNotify {
        
        private final Object myMonitorObject = new Object();
        boolean wasSignalled = false;
        
    }
    
}
