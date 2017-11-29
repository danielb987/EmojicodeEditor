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

import emojicode.test_templates.*;
import emojicode.code.EmojiPackage_UserDefinied;
import emojicode.compiler.CompilerError;
import emojicode.compiler.LogicError;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Bergqvist
 */
public class CompilerTest {
    
    private static final String EMOJICODE_VERSION_0_5_FOLDER = "emojicode_test/compilation/";
    
    
    public CompilerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCompile() {
        
        System.out.println("Daniel AAA CC DD");
        // We need to turn tests of temporary to get generation of Javadoc
        // work until we have got the tests to work.
        if (1==0)
            return;
        System.out.println("Daniel BBB");
        
        int numTests = 0;
        int numSuccess = 0;
        int numFailures = 0;
        
        String failureMessage = "";
        
        File f = new File(EMOJICODE_VERSION_0_5_FOLDER);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".emojic");
            }
        });
        
        for (File file : matchingFiles) {
            numTests++;
            try {
                testCompileFile(file.getAbsolutePath());
                numSuccess++;
            } catch (RuntimeException ex) {
                failureMessage += file.getName() + ": Runtime exception: "+ex.getMessage()+"\n";
                numFailures++;
            } catch (CompilerError ex) {
                failureMessage += file.getName() + ": Compiler error: "+ex.getMessage()+"\n";
                numFailures++;
            } catch (LogicError ex) {
                failureMessage += file.getName() + ": Logic error: "+ex.getMessage()+"\n";
                numFailures++;
            } catch (IOException ex) {
                failureMessage += file.getName() + ": IO error: "+ex.getMessage()+"\n";
                numFailures++;
            }
        }
        
        String result = String.format("\n\nNum tests: %d, num success: %d, num failures: %d\n\n", numTests, numSuccess, numFailures);
        
        if (! failureMessage.isEmpty())
            fail(failureMessage + result);
        else
            System.out.println(result);
    }
    
    
    public void testCompileFile(String filename) throws IOException, CompilerError, LogicError {
//        System.out.println("testCompile: "+filename);
        String source = readFile(filename);
        EmojiPackage_UserDefinied emojiPackage = new EmojiPackage_UserDefinied("daniel", filename, source);
        emojiPackage.parse();
    }
    
    
    private static String readFile(String filename) throws IOException {
        byte[] encoded;
        encoded = Files.readAllBytes(Paths.get(filename));
        return new String(encoded, Charset.forName("UTF-8"));
    }
    
}
