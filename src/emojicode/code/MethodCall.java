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

import emojicode.Token;
import emojicode.TokenType;
import emojicode.code.predifined_packages.s.EmojiString;
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import emojicode.runtime.Debugger;
import java.util.ArrayList;
import java.util.List;

/**
 * Method call
 * http://www.emojicode.org/docs/reference/classes-valuetypes.html#srule-method-call
 * 
 * @author Daniel Bergqvist
 */
public class MethodCall extends Parent {
    
    final EmojiMethod.MethodType methodType;
    String methodName;
    String className;
    EmojiClass emojiClass;
    Parent.Variable variable;
    String constantString;
    EmojiMethod emojiMethod;
    
    
    public MethodCall(Parent parent, EmojiMethod.MethodType methodType) {
        super(parent);
        this.methodType = methodType;
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
//        System.out.format("MethodCall\n");
        
        Token methodIdentifierToken = tokenStream.consumeToken(TokenType.Identifier);
        methodName = methodIdentifierToken.toString();
        
        this.startPosition = methodIdentifierToken.startPosition;
        
        Token classIdentifierToken = tokenStream.nextToken();
        
        if (classIdentifierToken.type == TokenType.String) {
            constantString = classIdentifierToken.toString();
            this.middlePosition = classIdentifierToken.endPosition;
            this.endPosition = classIdentifierToken.endPosition;
            emojiClass = EmojiPackage.packages.get("s").classes.get(EmojiString.getStringClassEmoji());
            tokenStream.consumeToken();
            if (emojiClass == null)
                throw new CompilerError(classIdentifierToken.startPosition, classIdentifierToken.endPosition, String.format("Class %s in package s is not found", classIdentifierToken.toString()));
            emojiMethod = emojiClass.getMethod(methodName, methodIdentifierToken.startPosition, classIdentifierToken.endPosition);
            if (emojiMethod == null)
                throw new CompilerError(methodIdentifierToken.startPosition, classIdentifierToken.endPosition, String.format("Method %s in class %s in package s is not found", methodName, classIdentifierToken.toString()));
            
        } else if (classIdentifierToken.type == TokenType.Identifier) {
            tokenStream.consumeToken();
            EmojiPackage emojiPackage = getPackage();
            emojiClass = emojiPackage.classes.get(classIdentifierToken.toString());
            if (emojiClass == null)
                throw new CompilerError(classIdentifierToken.startPosition, classIdentifierToken.endPosition, String.format("Class %s in package %s is not found", classIdentifierToken.toString(), emojiPackage.name));
            
//DANIEL            System.out.format("AAAA Class: %s, method: %s, type: %s\n", emojiClass.name, methodName, methodType.name());
//            System.out.format("AAAA Class: %s, method: %s, type: %s\n", emojiClass.name, emojiMethod.name, emojiMethod.methodType.name());
//            System.out.flush();
            
            if (methodType == EmojiMethod.MethodType.TYPE_METHOD)
                emojiMethod = emojiClass.getTypeMethod(methodName, methodIdentifierToken.startPosition, classIdentifierToken.endPosition);
            else
                emojiMethod = emojiClass.getMethod(methodName, methodIdentifierToken.startPosition, classIdentifierToken.endPosition);
            if (emojiMethod == null)
                throw new CompilerError(methodIdentifierToken.startPosition, classIdentifierToken.endPosition, String.format("Method %s in class %s in package s is not found", methodName, classIdentifierToken.toString()));
            
            List<EmojiMethodArgumentType> argumentTypes = emojiMethod.argumentTypes;
            for (EmojiMethodArgumentType argumentType : argumentTypes) {
//                Token token = tokenStream.nextToken();
            }
            
        } else {
            throw new CompilerError(classIdentifierToken.startPosition, classIdentifierToken.endPosition, "Why did we get here??? TokenType: "+classIdentifierToken.type.name());
//            throw new CompilerError("Method call has no class instance");
        }
        
    }
    
    
    public void run() {
//        System.out.println("MethodCall.execute()");
        Debugger.getInstance().step(this, startPosition, middlePosition, endPosition);
        List<EmojiArgument> arguments = new ArrayList<>();
        EmojiReturnValue returnValue = new EmojiReturnValue();
        EmojiClassInstance instance;
        if (variable != null)
            instance = variable.instance;
        else if (constantString != null)
            instance = EmojiString.getInstance().createInstance(constantString);
        else
            throw new RuntimeException("Method has no class instance to run on");
        emojiMethod.execute(instance, arguments, returnValue);
    }
    
}
