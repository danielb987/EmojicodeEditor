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

import emojicode.compiler.CompilerError;
import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public class ExpressionCreateVariable extends Expression {
    
    LocalVariableDefinition variable2;
    
    public ExpressionCreateVariable(Parent parent) {
        super(parent);
    }
    
    
    public void test() throws CompilerError {
        startPosition = null;
        endPosition = null;
        
        LocalVariableDefinition variable = new LocalVariableDefinition("Kalle",
                                                                       EmojicodeVariableType.OBJECT,
                                                                       false,
                                                                       currentStackBlockIndex,
                                                                       currentStackVariableIndex);
        
        addVariable(startPosition, endPosition, variable);
        
        variable2 = getVariable(startPosition, endPosition, "Testar");
    }
    
    
    @Override
    public void run(List<List<Variable>> variableStack) {
        variableStack.get(variable2.stackBlockIndex).add(new Variable(variable2.type, null));
//        Variable variable = variableStack.get(variable2.stackBlockIndex).get(variable2.stackVariableIndex);
    }
    
}
