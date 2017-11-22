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
 *
 * @author Daniel Bergqvist
 */
public class Parent {
    
    private final Parent parent;
    protected SourcePosition startPosition;
    protected SourcePosition middlePosition;
    protected SourcePosition endPosition;
    private final Map<String, Variable> variables = new HashMap<>();
    
    protected Parent(Parent parent) {
        this.parent = parent;
    }
    
    
    protected EmojiPackage getPackage() {
        if (parent != null)
            return parent.getPackage();
        
        throw new RuntimeException("Root parent must be a package");
    }
    
    
    protected void addVariable(SourcePosition startPosition, SourcePosition endPosition, Variable variable) throws CompilerError {
        if (variables.putIfAbsent(variable.name, variable) != null)
            throw new CompilerError(startPosition, endPosition, String.format("Variable %s already exists in this context", variable.name));
    }
    
    protected Variable getVariable(SourcePosition startPosition, SourcePosition endPosition, String name) throws CompilerError {
        Variable variable = variables.get(name);
        if ((variable == null) && (parent != null)) {
            variable = parent.getVariable(startPosition, endPosition, name);
        }
        if (variable == null) {
            throw new CompilerError(startPosition, endPosition, String.format("Variable %s does not exists", name));
        }
        return variable;
    }
    
    
    public class Variable {
        public final String name;
        public final EmojiClassInstance instance;
        
        public Variable(String name, EmojiClassInstance instance) {
            this.name = name;
            this.instance = instance;
        }
    }
    
}
