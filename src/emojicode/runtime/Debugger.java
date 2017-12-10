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
import java.io.IOException;
import java.io.ObjectOutputStream;

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
                this.debugActions.programFinished();
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
                this.debugActions.programFinished();
            } finally {
                programIsRunning = false;
            }
        }).start();
    }
    
    /**
     * Called at every step when an emojicode program is run.
     * @param parent the parent of the current emojicode part that is to be executed.
     * @param startPosition the start position of the emojicode part that is to be executed now.
     * @param middlePosition the middle position of the emojicode part that is to be executed now.
     * This could for example be a method call, there the method and the class is on the left and
     * the parameter list is on the right of this point.
     * @param endPosition the end position of the emojicode part that is to be executed now.
     */
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
    
    
    
    /**
     * Actions to do when debugging the program.
     */
    public interface DebugActions {
        
        /**
         * Called at every step when an emojicode program is run.
         * @param parent the parent of the current emojicode part that is to be executed.
         * @param startPosition the start position of the emojicode part that is to be executed now.
         * @param middlePosition the middle position of the emojicode part that is to be executed now.
         * This could for example be a method call, there the method and the class is on the left and
         * the parameter list is on the right of this point.
         * @param endPosition the end position of the emojicode part that is to be executed now.
         */
        void next(Parent parent,
                  SourcePosition startPosition,
                  SourcePosition middlePosition,
                  SourcePosition endPosition);
        
        /**
         * Called if the program has finished.
         * The program has not finished if it is aborted or if there is a program error.
         */
        void programFinished();
        
        /**
         * Called if the program is aborted.
         * @param position
         */
        void programAborted(SourcePosition position);
        
        /**
         * Called if the program is stopped by an error.
         * @param position
         */
        void programError(SourcePosition position);
        
    }
    
    
    
    public final class DebugActionsSwingSafe {
        
        private final DebugActions debugActions;
        
        /**
         *
         * @param debugActions
         */
        public DebugActionsSwingSafe(final DebugActions debugActions) {
            this.debugActions = debugActions;
        }
        
        
        /**
         * Called at every step when an emojicode program is run.
         * @param parent the parent of the current emojicode part that is to be executed.
         * @param startPosition the start position of the emojicode part that is to be executed now.
         * @param middlePosition the middle position of the emojicode part that is to be executed now.
         * This could for example be a method call, there the method and the class is on the left and
         * the parameter list is on the right of this point.
         * @param endPosition the end position of the emojicode part that is to be executed now.
         */
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
                debugActions.programFinished();
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
    
    
    
    /**
     * An exeption thrown when the program is aborted.
     * This exception should never be catched by anyone else than the debugger
     * itself. It is therefore private. However, it is possible to catch it if
     * a catch statment catches Throwable or Exception, but it should not be
     * done.
     */
    
    // This class may never be serialized. It throws an exception in writeObject.
    @SuppressWarnings("serial")
    private class StopProgramException extends RuntimeException {
        
        /**
         * This class may not be serialized so throw an exception.
         * @param oos the object stream
         * @throws IOException this method always throws an IOException
         */
        private void writeObject(ObjectOutputStream oos) throws IOException {
            throw new IOException("This class is NOT serializable.");
        }
    }
    
    
    
    // http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
    private class MyWaitNotify {
        
        private final Object myMonitorObject = new Object();
        boolean wasSignalled = false;
        
    }
    
}
