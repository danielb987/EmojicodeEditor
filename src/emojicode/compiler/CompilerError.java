package emojicode.compiler;

import emojicode.SourcePosition;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A syntax error in the emojicode program.
 * @author Daniel Bergqvist
 */

// This class may never be serialized. It throws an exception in writeObject.
@SuppressWarnings("serial")

public class CompilerError extends Exception {

    /**
     * The position in the emojicode source where the error is located.
     */
    public final SourcePosition sourcePosition;

    /**
     * The position in the emojicode source where the error ends.
     */
    public final SourcePosition endPosition;
    
    
    /**
     * Constructs an instance of <code>CompilerError</code> with the specified
     * detail message.
     *
     * @param sourcePosition the position in the emojicode source where the error is located.
     * @param endPosition the position in the emojicode source where the error ends.
     * @param msg the detail message.
     */
    public CompilerError(SourcePosition sourcePosition, SourcePosition endPosition, String msg) {
        super(msg);
        this.sourcePosition = sourcePosition;
        this.endPosition = endPosition;
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
