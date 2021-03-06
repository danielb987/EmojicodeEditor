package emojicode.code;

import emojicode.Emojicode;
import emojicode.Token;
import emojicode.TokenType;
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import java.util.List;

/**
 * Expression
 * http://www.emojicode.org/docs/reference/syntax.html#srule-expression
 */
public class Expression extends Parent {
    
    /**
     * The kind of expression this is.
     */
    public enum ExpressionType {
        /**
         * Undefined value.
         * Used to catch if the value is not set.
         */
        UNDEFINED,
        
        /**
         * Return a value.
         */
        RETURN_VALUE,
        
        /**
         * Create a new instance of a class.
         */
        CREATE_NEW_INSTANCE,
        
        /**
         * Call a type method.
         */
        CALL_TYPE_METHOD,
        
        /**
         * Call a method.
         */
        CALL_METHOD,
        
        /**
         * Constant string.
         */
        CONSTANT_STRING,
        
        /**
         * Constant integer.
         */
        CONSTANT_INTEGER,
        
        /**
         * Constant float.
         */
        CONSTANT_FLOAT
    };
    
    private ExpressionType expressionType = ExpressionType.UNDEFINED;
    
    private Token createInstanceOfClass;
    private Token executeConstructor;
    
    private Expression returnExpression;
    private MethodCall methodCall;
    
    private String constantString;
    
    
    public Expression(Parent parent) {
        super(parent, Parent.HasVariables.NO);
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        Token token = tokenStream.nextToken();
        
//        System.out.format("TokenType: %s, token: %s\n", token.type.name(), token.toString());
        
        this.startPosition = token.fStartPosition;
        
        switch (token.fType) {
            
            case Identifier:
                
                if (token.toString().equals("🍎")) {     // 🍎 = return the value of the following expression
                    
                    expressionType = ExpressionType.RETURN_VALUE;
                    
                    tokenStream.consumeToken();
                    returnExpression = new Expression(this);
                    returnExpression.parse(tokenStream);
                    
                } else if (token.toString().equals(Emojicode.E_LARGE_BLUE_DIAMOND)) {     // 🔷 = create new instance of a class
                    
                    expressionType = ExpressionType.CREATE_NEW_INSTANCE;
                    
                    tokenStream.consumeToken();
                    if (tokenStream.nextTokenIs(TokenType.This))
                        createInstanceOfClass = tokenStream.consumeToken(TokenType.This);
                    else
                        createInstanceOfClass = tokenStream.consumeToken(TokenType.Identifier);
                    
                    if (tokenStream.nextTokenIs(TokenType.New))
                        executeConstructor = tokenStream.consumeToken(TokenType.New);
                    else
                        executeConstructor = tokenStream.consumeToken(TokenType.Identifier);
                    
                } else if (token.toString().equals(Emojicode.E_DOUGHNUT)) {     // 🍩 = call type method
                    
                    expressionType = ExpressionType.CALL_TYPE_METHOD;
                    
                    tokenStream.consumeToken();
                    methodCall = new MethodCall(this, EmojiMethod.MethodType.TYPE_METHOD);
                    methodCall.parse(tokenStream);
                    this.endPosition = methodCall.endPosition;
                    
                } else {                // call method
                    
                    expressionType = ExpressionType.CALL_METHOD;
                    
                    methodCall = new MethodCall(this, EmojiMethod.MethodType.METHOD);
                    methodCall.parse(tokenStream);
                    this.endPosition = methodCall.endPosition;
                }
                
                break;
                
            case String:
                
                expressionType = ExpressionType.RETURN_VALUE;
                constantString = token.toString();
                
                tokenStream.consumeToken();
                    
                break;
                
            default:
                throw new RuntimeException("Why did we get here??? TokenType: "+token.fType.name());
        }
    }
    
    
    public void run(List<List<Variable>> variableStack) {
        if (methodCall != null)
            methodCall.run(variableStack);
    }
    
}
