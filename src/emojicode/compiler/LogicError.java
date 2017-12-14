package emojicode.compiler;

import emojicode.SourcePosition;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Logic error in the emojicode program.
 * This exception may be thrown when the emojicode program is executed.
 * 
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public class LogicError extends Exception {

    /**
     * The position in the emojicode source where the error is located.
     */
    public final SourcePosition sourcePosition;
    
    
    /**
     * Constructs an instance of <code>LogicError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LogicError(String msg) {
        super(msg);
        sourcePosition = null;
    }

    /**
     * Constructs an instance of <code>CompilerError</code> with the specified
     * detail message.
     *
     * @param sourcePosition the position in the emojicode source where the error is located.
     * @param msg the detail message.
     */
    public LogicError(SourcePosition sourcePosition, String msg) {
        super(msg);
        this.sourcePosition = sourcePosition;
    }
    
    
    /**
     * This class may not be serialized so throw an exception.
     * @param oos the object stream
     * @throws IOException this method always throws an IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("This class is NOT serializable.");
    }
    
}
