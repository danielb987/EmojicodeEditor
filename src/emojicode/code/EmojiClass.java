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
package emojicode.code;

import emojicode.SourcePosition;
import emojicode.compiler.CompilerError;
import java.util.HashMap;
import java.util.Map;

/**
 * EmojiClass
 * http://www.emojicode.org/docs/reference/classes-valuetypes.html
 * 
 * @author Daniel Bergqvist
 */
public abstract class EmojiClass extends Parent {
    
    final String name;
    
    private final Map<String, EmojiMethod> initializers = new HashMap<>();
    private final Map<String, EmojiMethod> typeMethods = new HashMap<>();
    private final Map<String, EmojiMethod> methods = new HashMap<>();
    
    
    public EmojiClass(String name, Parent parent) {
        super(parent, Parent.HasVariables.YES);
        this.name = name;
    }
    
    
//    public abstract EmojiClassInstance createInstance();
    
    
    public final void addInitializerOrMethod(   EmojiMethod emojiMethod,
                                                SourcePosition startPosition,
                                                SourcePosition endPosition)
            throws CompilerError {
        
//        System.out.format("Class: %s, method: %s, type: %s\n", name, emojiMethod.name, emojiMethod.methodType.name());
//        System.out.flush();
        
        switch (emojiMethod.methodType) {
            case INITIALIZER:
            case REQUIRED_INITIALIZER:
                initializers.put(emojiMethod.name, emojiMethod);
                break;
                
            case METHOD:
                methods.put(emojiMethod.name, emojiMethod);
                break;
                
            case TYPE_METHOD:
                typeMethods.put(emojiMethod.name, emojiMethod);
                break;
                
            default:
                throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", emojiMethod.name, name));
        }
    }
    
    
    public final EmojiMethod getInitializer(String methodName, SourcePosition startPosition, SourcePosition endPosition) throws CompilerError {
        EmojiMethod method = initializers.get(methodName);
        if (method == null)
            throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", methodName, name));
        return method;
    }
    
    
    public final EmojiMethod getTypeMethod(String methodName, SourcePosition startPosition, SourcePosition endPosition) throws CompilerError {
        EmojiMethod method = typeMethods.get(methodName);
        if (method == null)
            throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", methodName, name));
        return method;
    }
    
    
    public final EmojiMethod getMethod(String methodName, SourcePosition startPosition, SourcePosition endPosition) throws CompilerError {
        EmojiMethod method = methods.get(methodName);
        if (method == null)
            throw new CompilerError(startPosition, endPosition, String.format("Method %s in class %s is not found", methodName, name));
        return method;
    }
    
}
