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

import emojicode.Emojicode;
import emojicode.Token;
import emojicode.TokenType;
import emojicode.code.predifined_packages.s.EmojiString;
import static emojicode.code.predifined_packages.s.EmojiString.STRING_EMOJI;
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import emojicode.runtime.Debugger;
import java.util.ArrayList;
import java.util.List;

/**
 * Emojicode method call.
 * http://www.emojicode.org/docs/reference/classes-valuetypes.html#srule-method-call
 *
 * @author Daniel Bergqvist
 */
public final class MethodCall extends Parent {
    
    /**
     * Type of method call.
     */
    public enum MethodCallType {
        /**
         * Undefined value.
         * Used to catch if the value is not set.
         */
        UNDEFINED,
        
        /**
         * Concatenate two or more strings.
         */
        CONCATENATE_STRINGS,
        
        /**
         * Convert an integer to a string.
         */
        CONVERT_INTEGER_TO_STRING,
        
        /**
         * Call a method on an object.
         */
        CALL_METHOD,
        
        /**
         * Call a method on a constant string.
         */
        CALL_METHOD_ON_CONSTANT_STRING
    };
    
    
    /**
     * The type of method call.
     */
    MethodCallType methodCallType = MethodCallType.UNDEFINED;
    
    /**
     * The type of method.
     */
    private final EmojiMethod.MethodType methodType;
    
    /**
     * The name of the method.
     */
    private String methodName;
    
    /**
     * The name of the class that the method is declared in.
     */
    private String className;
    
    /**
     * The variable that has the class instance that the method is declared in.
     */
    private LocalVariableDefinition variableDefinition;
    
//    Parent.LocalVariableDefinition variable;
    
    /**
     * If this method call is on a constant string, the method call is for this string, or this
     * string is null.
     */
    private String constantString;
    
    /**
     * The emojicode method that this method call expression should call.
     */
    private EmojiMethod emojiMethod;
    
    /**
     * The variable to use if we shall create a string from an integer.
     */
    private LocalVariableDefinition createStringFromIntegerVariable;
    
    /**
     * The strings to concatenate if we shall concatenate strings.
     */
    private List<Expression> stringsToConcatenate;
    
    
    /**
     * Create a emojicode method call.
     * @param parent The parent
     * @param aMethodType The type of method
     */
    public MethodCall(final Parent parent, final EmojiMethod.MethodType aMethodType) {
        super(parent, Parent.HasVariables.NO);
        this.methodType = aMethodType;
    }
    
    
    /**
     * Parse this method call.
     * @param tokenStream the token stream
     * @throws CompilerError compiler error
     */
    public void parse(final TokenStream tokenStream) throws CompilerError {
        
//        System.out.format("MethodCall\n");
        
        EmojiClass emojiClass;
        
        Token methodIdentifierToken = tokenStream.consumeToken(TokenType.Identifier);
        methodName = methodIdentifierToken.toString();
        
        this.startPosition = methodIdentifierToken.fStartPosition;
        
        if (EmojiString.STRING_EMOJI.equals(methodIdentifierToken)) {
            
            // Convert an integer to a string
            
            Token variableNameToken = tokenStream.consumeToken(TokenType.Identifier);
            createStringFromIntegerVariable = getVariable(variableNameToken.fStartPosition,
                                                          variableNameToken.fEndPosition,
                                                          variableNameToken.toString());
            
            methodCallType = MethodCallType.CONVERT_INTEGER_TO_STRING;
//DANIEL            createStringFromIntegerBase = tokenStream.getIntegerToken();
            createStringFromIntegerVariable = null;
            
        } else {
            System.out.println("aaa");
            
            Token classIdentifierToken = tokenStream.nextToken();
            
            if (Emojicode.E_COOKIE.equals(classIdentifierToken.toString())) {
                
                tokenStream.consumeToken();
                
                System.out.println("bbb");
                
                // Concatenate strings
                methodCallType = MethodCallType.CONCATENATE_STRINGS;
                stringsToConcatenate = new ArrayList<>();

                Token token = tokenStream.nextToken();
                while ((token.fType != TokenType.Identifier) || (!Emojicode.E_COOKIE.equals(token.toString()))) {
                    System.out.println("ccc");
                    Expression expression = new Expression(this);
                    expression.parse(tokenStream);
                    stringsToConcatenate.add(expression);
                    System.out.println("ddd");
                }

                tokenStream.consumeIdentifierToken(Emojicode.E_COOKIE);
                
                System.out.println("eee");
                
            } else if (classIdentifierToken.fType == TokenType.String) {
                
                methodCallType = MethodCallType.CALL_METHOD_ON_CONSTANT_STRING;
            
                constantString = classIdentifierToken.toString();
                this.middlePosition = classIdentifierToken.fEndPosition;
                this.endPosition = classIdentifierToken.fEndPosition;
                emojiClass = EmojiPackage
                                .packages
                                    .get("s")
                                        .classes
                                            .get(EmojiString.STRING_EMOJI);
    //                                        .get(EmojiString.getStringClassEmoji());

                tokenStream.consumeToken();

                if (emojiClass == null) {
                    throw new CompilerError(classIdentifierToken.fStartPosition,
                                            classIdentifierToken.fEndPosition,
                                            String.format("Class %s in package s is not found",
                                                          classIdentifierToken.toString()));
                }

                emojiMethod = emojiClass.getMethod(methodName,
                                                   methodIdentifierToken.fStartPosition,
                                                   classIdentifierToken.fEndPosition);

                if (emojiMethod == null) {
                    throw new CompilerError(methodIdentifierToken.fStartPosition,
                                            classIdentifierToken.fEndPosition,
                                            String.format(
                                                    "Method %s in class %s in package s is not found",
                                                    methodName,
                                                    classIdentifierToken.toString()));
                }
                
            } else if (classIdentifierToken.fType == TokenType.Identifier) {
                
                methodCallType = MethodCallType.CALL_METHOD;
                
                tokenStream.consumeToken();
                EmojiPackage emojiPackage = getPackage();
                emojiClass = emojiPackage.classes.get(classIdentifierToken.toString());
                if (emojiClass == null) {
                    throw new CompilerError(classIdentifierToken.fStartPosition,
                                            classIdentifierToken.fEndPosition,
                                            String.format("Class %s in package %s is not found",
                                                          classIdentifierToken.toString(),
                                                          emojiPackage.name));

    //DANIEL            System.out.format("AAAA Class: %s, method: %s, type: %s\n",
    //DANIEL                              emojiClass.name, methodName, methodType.name());
    //            System.out.format("AAAA Class: %s, method: %s, type: %s\n",
    //                              emojiClass.name, emojiMethod.name, emojiMethod.methodType.name());
    //            System.out.flush();
                }

                if (methodType == EmojiMethod.MethodType.TYPE_METHOD) {
                    emojiMethod = emojiClass.getTypeMethod(methodName,
                                                           methodIdentifierToken.fStartPosition,
                                                           classIdentifierToken.fEndPosition);
                } else {
                    emojiMethod = emojiClass.getMethod(methodName,
                                                       methodIdentifierToken.fStartPosition,
                                                       classIdentifierToken.fEndPosition);
                }
                if (emojiMethod == null) {
                    throw new CompilerError(methodIdentifierToken.fStartPosition,
                                            classIdentifierToken.fEndPosition,
                                            String.format(
                                                    "Method %s in class %s in package s is not found",
                                                    methodName,
                                                    classIdentifierToken.toString()));
                }

                List<EmojiMethodArgumentType> argumentTypes = emojiMethod.argumentTypes;
                for (EmojiMethodArgumentType argumentType : argumentTypes) {
    //                Token token = tokenStream.nextToken();
                }
                
            } else {
                throw new CompilerError(classIdentifierToken.fStartPosition,
                                        classIdentifierToken.fEndPosition,
                                        "Why did we get here??? TokenType: "
                                                + classIdentifierToken.fType.name());

    //            throw new CompilerError("Method call has no class instance");
            }
        }
        
    }
    
    
    /**
     * Run this method call.
     * @param variableStack 
     */
    public void run(List<List<Variable>> variableStack) {
//        System.out.println("MethodCall.execute()");
        Debugger.getInstance().step(this, startPosition, middlePosition, endPosition);
        List<EmojiArgument> arguments = new ArrayList<>();
        EmojiReturnValue returnValue = new EmojiReturnValue();
        
        EmojiClassInstance instance;
        if (variableDefinition != null) {
            Variable variable = variableStack
                                    .get(variableDefinition.stackBlockIndex)
                                        .get(variableDefinition.stackVariableIndex);
            instance = (EmojiClassInstance) variable.instance;
        }
        else if (constantString != null) {
            instance = EmojiString.getInstance().createInstance(constantString);
        } else {
            throw new RuntimeException("Method has no class instance to run on");
        }
        emojiMethod.execute(instance, arguments, returnValue);
    }
    
}
