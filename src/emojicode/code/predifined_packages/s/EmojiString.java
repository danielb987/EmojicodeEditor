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
package emojicode.code.predifined_packages.s;

import emojicode.code.EmojiClassPredefinied;
import emojicode.code.EmojiMethod;
import emojicode.code.EmojiPackage;
import emojicode.code.EmojiArgument;
import emojicode.code.EmojiClassInstance;
import emojicode.code.EmojiMethodArgumentType;
import emojicode.code.EmojiMethodPredefined;
import emojicode.code.EmojiReturnValue;
import emojicode.compiler.CompilerError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel Bergqvist
 */
public final class EmojiString extends EmojiClassPredefinied {
    
    private static EmojiString instance;
    
    
    public static EmojiString createInstance(final EmojiPackage emojiPackage) {
        instance = new EmojiString(emojiPackage);
        return instance;
    }
    
    
    public static EmojiString getInstance() {
        return instance;
    }
    
    
    private EmojiString(final EmojiPackage emojiPackage) {
        super(getStringClassEmoji(), emojiPackage);
        
        registerMethods();
    }
    
    void registerMethods() {
        try {
            // Initializer
            // Prompts the user for input. (via the standard input/output)
            addInitializerOrMethod(
                    new EmojiMethodPredefined(null,
                                              this,
                                              EmojiMethod.MethodType.INITIALIZER,
                                              "😯",
                                              new ArrayList<>(),
                                              new ArrayList<>(), null) {
                @Override
                public void execute(final EmojiClassInstance aInstance,
                                    final List<EmojiArgument> arguments,
                                    final EmojiReturnValue returnValue) {
                    prompts_the_user_for_input(aInstance, arguments, returnValue);
                }
            }, null, null);

            // Puts this 🔡 to the standard output
            addInitializerOrMethod(
                    new EmojiMethodPredefined(null,
                                              this,
                                              EmojiMethod.MethodType.METHOD,
                                              "😀", new ArrayList<>(),
                                              Collections.singletonList(
                                                      EmojiMethodArgumentType.StringArgument),
                                              null) {
                @Override
                public void execute(final EmojiClassInstance aInstance,
                                    final List<EmojiArgument> arguments,
                                    final EmojiReturnValue returnValue) {
                    puts_this_string_to_the_standard_output(aInstance, arguments, returnValue);
                }
            }, null, null);
        
        } catch (CompilerError e) {
            // If we get a CompilerError exception, we have a bug the program.
            throw new RuntimeException("Failed to register methods", e);
        }
    }
    
    
    public void prompts_the_user_for_input(final EmojiClassInstance instance,
                                           final List<EmojiArgument> arguments,
                                           final EmojiReturnValue returnValue) {
        try {
            String line = StandardIO.getInstance().getInput().readLine();
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read standard input", ex);
        }
    }
    
    
    public void puts_this_string_to_the_standard_output(final EmojiClassInstance instance,
                                                        final List<EmojiArgument> arguments,
                                                        final EmojiReturnValue returnValue) {
        if (instance instanceof EmojiClassInstanceString) {
            PrintWriter output = StandardIO.getInstance().getOutput();
            output.println(((EmojiClassInstanceString)instance).string);
            output.flush();
        } else {
            throw new RuntimeException(
                    String.format("Instance %s is not instance of EmojiClassInstance_String",
                            instance.getClass().getName()));
        }
//        try {
//            String line = StandardIO.getInstance().getInput().readLine();
//        } catch (IOException ex) {
//            throw new RuntimeException("Cannot read standard input", ex);
//        }
    }
    
    
    public static String getStringClassEmoji() {
        int emojiCode = 0x1F521;    // 🔡
        return new String(Character.toChars(emojiCode));
    }
    
/*    
    @Override
    public EmojiClassInstance createInstance() {
        return new EmojiClassInstance_String();
    }
*/    
    
    public EmojiClassInstance createInstance(final String string) {
        return new EmojiClassInstanceString(string);
    }
    
    
    
    private class EmojiClassInstanceString implements EmojiClassInstance {
        
        private String string;
        
        private EmojiClassInstanceString() {
        }
        
        private EmojiClassInstanceString(final String string) {
            this.string = string;
        }
    }
    
}
