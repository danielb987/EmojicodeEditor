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
 * Method
 * http://www.emojicode.org/docs/reference/classes-valuetypes.html
 *
 * A type method is a method that is called on the type rather than on the
 * instance of the type.
 *
 * @author Daniel Bergqvist
 */
public class EmojiMethodUserDefined extends EmojiMethod {
    
    private final CodeBlock codeBlock;
    
    
    public EmojiMethodUserDefined(final DocumentationComment documentationComment,
                                  final Parent parent,
                                  final MethodType methodType,
                                  final String name,
                                  final List<EmojiMethodArgumentType> genericArgumentTypes,
                                  final List<EmojiMethodArgumentType> argumentTypes,
                                  final EmojiArgument returnType,
                                  final CodeBlock codeBlock) {
        super(documentationComment, parent, methodType, name, genericArgumentTypes, argumentTypes, returnType);
        
        this.codeBlock = codeBlock;
    }
    
/*    
    public EmojiMethod_UserDefined(DocumentationComment documentationComment, Parent parent) {
        super(documentationComment,
              parent,
              new ArrayList<EmojiMethodArgumentType>(),
              new ArrayList<EmojiMethodArgumentType>());
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError, LogicError {
        
//        tokenStream.consumeIdentifierToken(Emojicode.E_RABBIT);     // 🐇 - type method
        
        tokenStream.consumeIdentifierToken(Emojicode.E_PIG);     // 🐖 - method
        
//        typeIdentifier = tokenStream.getIdentifierToken();          // Type identifier
/.*        
        while (tokenStream.nextTokenIs(TokenType.Variable)) {
            Token token = tokenStream.consumeToken();
            System.out.println("Parameter: "+token.toString());
            parameters.add(token.toString());
        }
*./        
/.*        
        tokenStream.consumeToken(TokenType.BlockBegin);
        
        tokenStream.assumeHasMoreTokens();
        
        System.out.println("Enumeration");
        while (tokenStream.consumeTokenIdentifierIf(Emojicode.E_RADIO_BUTTON)) {
            System.out.format("Next token: %s, %s\n", tokenStream.nextToken().type.name(),
                tokenStream.nextToken().toString());
            enumerationValues.add(tokenStream.getIdentifierToken());
        }
        
        System.out.format("\n\nNext token: %s, %s\n", tokenStream.nextToken().type.name(),
            tokenStream.nextToken().toString());
//        while (tokenStream.hasMoreTokens()) {
//            Token token = tokenStream.nextToken();
//        }
*./        
    }
*/    
    
    @Override
    public void execute(final EmojiClassInstance instance,
                        final List<EmojiArgument> arguments,
                        final EmojiReturnValue returnValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
