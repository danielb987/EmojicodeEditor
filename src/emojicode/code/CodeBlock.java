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
        
        this.startPosition = token.fStartPosition;
        this.middlePosition = token.fEndPosition;
        
        while (! tokenStream.nextTokenIs(TokenType.BlockEnd)) {
            Statement statement = new Statement(this);
            statement.parse(tokenStream);
            statements.add(statement);
        }
        
        token = tokenStream.consumeToken(TokenType.BlockEnd);
        this.endPosition = token.fEndPosition;
    }
    
    
    public void run(List<List<Variable>> variableStack) {
        
        Debugger.getInstance().step(this, startPosition, middlePosition, endPosition);
        
        variableStack.add(new ArrayList<>());
        for (Statement statement : statements) {
            statement.run(variableStack);
        }
        variableStack.remove(variableStack.size()-1);
    }
    
}
