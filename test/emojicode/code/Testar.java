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

// import emojicode.ATest;
// import emojicodeeditor.Bundle;
import emojicode.ATest;
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
public class Testar {
    
    public Testar() {
    }
    
    public static void main(String[] args) {
        System.out.println("Test Bundle");
        ATest aTest = new ATest("HejABC");
        System.out.println("ATest.get(): "+aTest.get());
/*        
        String str = Bundle.getInstance().getMessage("Testar");
        System.out.format("Str: %s\n", str);
        str = Bundle.getInstance().getMessage("ToolbarGroup_Operators");
        System.out.format("Str: %s\n", str);
*/        
        System.out.println("Hejsan ABC DEF");
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

    /**
     * Test of getPackage method, of class EmojiPackage.
     */
    @Test
    public void testGetPackage() {
        
        ATest aTest = new ATest("HejXYZ");
        System.out.println("ATest.get(): "+aTest.get());
    }
    
}
