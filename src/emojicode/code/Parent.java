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
 * Root class of the emojicode code tree.
 */
public class Parent {
    
    protected enum HasVariables { YES, NO };
    protected enum IsFrozen { YES, NO };
    
    public enum EmojicodeVariableType { INTEGER, FLOAT, STRING, OBJECT };
    
    
    private final Parent parent;
    protected SourcePosition startPosition;
    protected SourcePosition middlePosition;
    protected SourcePosition endPosition;
    private final Map<String, LocalVariableDefinition> variables;
    
    protected int currentStackBlockIndex;
    protected int currentStackVariableIndex;
    
    
    protected Parent(final Parent aParent, final HasVariables hasVariables) {
        this.parent = aParent;
        
        switch (hasVariables) {
            case YES:
                this.variables = new HashMap<>();
                break;
            case NO:
                this.variables = null;
                break;
            default:
                throw new RuntimeException("hasVariables has invalid value");
        }
    }
    
    
    /**
     * Get the package.
     * This method must be overridden by EmojiPackage class.
     * This method must not be overridden by any other class than EmojiPackage class.
     * @return the package
     */
    protected EmojiPackage getPackage() {
        if (parent != null) {
            return parent.getPackage();
        }
        
        throw new RuntimeException("Root parent must be a package");
    }
    
    
    protected final void addVariable(final SourcePosition aStartPosition,
                                     final SourcePosition aEndPosition,
                                     final LocalVariableDefinition variable)
            throws CompilerError {
        
        if (variables != null) {
            if (variables.putIfAbsent(variable.name, variable) != null) {
                throw new CompilerError(aStartPosition,
                                        aEndPosition,
                                        String.format("Variable %s already exists in this context",
                                                variable.name));
            }
        } else {
            if (parent == null) {
                throw new RuntimeException("Variables cannot be created in this scope");
            }
            parent.addVariable(aStartPosition, aEndPosition, variable);
        }
    }
    
    
    protected final LocalVariableDefinition getVariable(final SourcePosition aStartPosition,
                                         final SourcePosition aEndPosition,
                                         final String name)
            throws CompilerError {
        
        if (variables != null) {
            LocalVariableDefinition variable = variables.get(name);
            if (variable != null) {
                return variable;
            } else {
                if (parent == null) {
                    throw new CompilerError(aStartPosition,
                                            aEndPosition,
                                            String.format("Variable %s does not exists", name));
                }
                return parent.getVariable(aStartPosition, aEndPosition, name);
            }
        } else {
            if (parent == null) {
                throw new RuntimeException("Variables cannot be exist in this scope");
            }
            return parent.getVariable(aStartPosition, aEndPosition, name);
        }
    }
    
    
    public static final class Variable {
        public final EmojicodeVariableType type;
        
        /**
         * An instance of the variable.
         * This can be a Float, Integer or a EmojiClassInstance.
         */
        public Object instance;
//        public final EmojiClassInstance instance;
        
        public Variable(final EmojicodeVariableType aType, final Object aInstance) {
//        public Variable(final EmojicodeVariableType aType, final EmojiClassInstance aInstance) {
            this.type = aType;
            this.instance = aInstance;
        }
    }
    
    
    public static final class LocalVariableDefinition {
        public final String name;
        public final EmojicodeVariableType type;
        public final IsFrozen isFrozen;
//        public final EmojiClassInstance instance;
        public final int stackBlockIndex;
        public final int stackVariableIndex;
        
        public LocalVariableDefinition(final String aName,
                                       final EmojicodeVariableType aType,
                                       final IsFrozen isFrozen,
                                       final int aStackBlockIndex,
                                       final int aStackVariableIndex) {
            this.name = aName;
            this.type = aType;
            this.isFrozen = isFrozen;
            this.stackBlockIndex = aStackBlockIndex;
            this.stackVariableIndex = aStackVariableIndex;
        }
/*        
        public LocalVariableDefinition(final String aName, final EmojiClassInstance aInstance) {
            this.name = aName;
            this.instance = aInstance;
        }
*/        
    }
    
}
