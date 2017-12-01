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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Bergqvist
 */
public class EmojiPackage extends Parent {
    
    public static final Map<String, EmojiPackage> packages = new HashMap<>();
    
    public static final String DEFAULT_PACKAGE_NAME = "_";
    
    final String name;
    String namespace;
    public final Map<String, EmojiPackage> importedPackages = new HashMap<>();
    public final Map<String, EmojiClass> classes = new HashMap<>();
    
    
    // Read all predifined packages
    static {
        emojicode.code.predifined_packages.s.RegisterClasses.register();
    }
    
    
    public EmojiPackage(String name) {
        super(null);
        this.name = name;
    }
    
    
    @Override
    protected EmojiPackage getPackage() {
        return this;
    }
    
    
    public static void add(EmojiPackage emojiPackage) {
        packages.put(emojiPackage.name, emojiPackage);
    }
    
    public void add(EmojiClass emojiClass) {
        classes.put(emojiClass.name, emojiClass);
    }
    
    public void add(List<EmojiClass> emojiClasses) {
        for (EmojiClass emojiClass : emojiClasses)
            classes.put(emojiClass.name, emojiClass);
    }
    
}
