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
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import emojicode.runtime.Debugger;
import java.util.ArrayList;
import java.util.List;

/**
 * Code block
 * http://www.emojicode.org/docs/reference/controlflow.html#srule-block
 * 
 * @author Daniel Bergqvist
 */
public class CodeBlock extends Parent {
    
    private final List<Statement> statements = new ArrayList<>();
    
    
    public CodeBlock(Parent parent) {
        super(parent, Parent.HasVariables.YES);
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        Token token = tokenStream.consumeToken(TokenType.BlockBegin);
        
        this.startPosition = token.startPosition;
        this.middlePosition = token.endPosition;
        
        while (! tokenStream.nextTokenIs(TokenType.BlockEnd)) {
            Statement statement = new Statement(this);
            statement.parse(tokenStream);
            statements.add(statement);
        }
        
        token = tokenStream.consumeToken(TokenType.BlockEnd);
        this.endPosition = token.endPosition;
    }
    
    
    public void run() {
        Debugger.getInstance().step(this, startPosition, middlePosition, endPosition);
        for (Statement statement : statements) {
            statement.run();
        }
    }
    
}
