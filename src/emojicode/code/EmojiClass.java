package emojicode.code;

import emojicode.SourcePosition;
import emojicode.compiler.CompilerError;
import java.util.HashMap;
import java.util.Map;

/**
 * EmojiClass
 * http://www.emojicode.org/docs/reference/classes-valuetypes.html
 */
public abstract class EmojiClass extends Parent {
    
    final String name;
    
    private final Map<String, EmojiMethod> fInitializers = new HashMap<>();
    private final Map<String, EmojiMethod> fTypeMethods = new HashMap<>();
    private final Map<String, EmojiMethod> fMethods = new HashMap<>();
    
    
    public EmojiClass(final String name, final Parent parent) {
        super(parent, Parent.HasVariables.YES);
        this.name = name;
    }
    
    
//    public abstract EmojiClassInstance createInstance();
    
    
    public final void addInitializerOrMethod(   final EmojiMethod emojiMethod,
                                                final SourcePosition startPosition,
                                                final SourcePosition endPosition)
            throws CompilerError {
        
//        System.out.format("Class: %s, method: %s, type: %s\n",
//                          name, emojiMethod.name, emojiMethod.methodType.name());
//        System.out.flush();
        
        switch (emojiMethod.methodType) {
            case INITIALIZER:
            case REQUIRED_INITIALIZER:
                fInitializers.put(emojiMethod.name, emojiMethod);
                break;
                
            case METHOD:
                fMethods.put(emojiMethod.name, emojiMethod);
                break;
                
            case TYPE_METHOD:
                fTypeMethods.put(emojiMethod.name, emojiMethod);
                break;
                
            default:
                throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", emojiMethod.name, name));
        }
    }
    
    
    public final EmojiMethod getInitializer(final String methodName,
                                            final SourcePosition startPosition,
                                            final SourcePosition endPosition)
            throws CompilerError {
        
        EmojiMethod method = fInitializers.get(methodName);
        
        if (method == null) {
            throw new CompilerError(startPosition,
                                    endPosition,
                                    String.format("Method %s in class %s is not found",
                                                  methodName,
                                                  name));
        }
        
        return method;
    }
    
    
    public final EmojiMethod getTypeMethod(final String methodName,
                                           final SourcePosition startPosition,
                                           final SourcePosition endPosition)
            throws CompilerError {
        
        EmojiMethod method = fTypeMethods.get(methodName);
        if (method == null)
            throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", methodName, name));
        return method;
    }
    
    
    public final EmojiMethod getMethod(final String methodName,
                                       final SourcePosition startPosition,
                                       final SourcePosition endPosition)
            throws CompilerError {
        
        EmojiMethod method = fMethods.get(methodName);
        if (method == null) {
            throw new CompilerError(startPosition,
                                    endPosition,
                                    String.format("Method %s in class %s is not found",
                                                  methodName, name));
        }
        return method;
    }
    
}
