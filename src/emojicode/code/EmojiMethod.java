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

import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public abstract class EmojiMethod extends Parent {
    
    public final MethodType methodType;
    public final String name;
    private final DocumentationComment documentationComment;
    
    public final List<EmojiMethodArgumentType> genericArgumentTypes;
    public final List<EmojiMethodArgumentType> argumentTypes;
    
    public final EmojiArgument returnType;
    
    
    public EmojiMethod( DocumentationComment documentationComment,
                        Parent parent,
                        MethodType methodType,
                        String name,
                        List<EmojiMethodArgumentType> genericArgumentTypes,
                        List<EmojiMethodArgumentType> argumentTypes,
                        EmojiArgument returnType
                        ) {
        super(parent, Parent.HasVariables.YES);
        this.documentationComment = documentationComment;
        this.methodType = methodType;
        this.name = name;
        this.genericArgumentTypes = genericArgumentTypes;
        this.argumentTypes = argumentTypes;
        this.returnType = returnType;
    }
    
    
    public abstract void execute(EmojiClassInstance instance, List<EmojiArgument> arguments, EmojiReturnValue returnValue);
    
    
    
    public static enum MethodType {
        INITIALIZER,
        REQUIRED_INITIALIZER,
        METHOD,
        TYPE_METHOD,
    }
    
}
