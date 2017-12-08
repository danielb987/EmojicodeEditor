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
import emojicode.compiler.CompilerError;
import emojicode.compiler.TokenStream;
import emojicode.runtime.Debugger;
import java.util.ArrayList;

/**
 * The main block
 * http://www.emojicode.org/docs/reference/basics.html
 * 
 * @author Daniel Bergqvist
 */
// public class MainBlock extends CodeBlock {
public class MainBlock extends Parent {
    
    private final DocumentationComment documentationComment;
    private CodeBlock codeBlock;
    
    
    public MainBlock(DocumentationComment documentationComment, Parent parent) {
        super(parent, Parent.HasVariables.NO);
        this.documentationComment = documentationComment;
    }
    
    
    public void parse(TokenStream tokenStream) throws CompilerError {
        
        Token token = tokenStream.consumeIdentifierToken(Emojicode.E_CHEQUERED_FLAG);     // 🏁 - start flag = start of program
        
        this.startPosition = token.startPosition;
        this.middlePosition = token.endPosition;
        // A main block may have a return type
        // http://www.emojicode.org/docs/reference/classes-valuetypes.html#srule-return-type
        
//        super.parse(tokenStream);
        codeBlock = new CodeBlock(this);
        codeBlock.parse(tokenStream);
        
        this.endPosition = codeBlock.endPosition;
    }
    
    
    public void run() {
        Debugger.getInstance().step(this, startPosition, middlePosition, endPosition);
        codeBlock.run(new ArrayList<>());
    }
    
}
