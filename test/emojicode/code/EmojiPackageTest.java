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

import emojicodeeditor.Bundle;
import java.util.List;
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
public class EmojiPackageTest {
    
    public EmojiPackageTest() {
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
        
        System.out.println("Daniel Testar 123 ABC qwe");
        System.out.format("Bundle: %s\n", Bundle.getInstance().getMessage("Test"));
        Bundle.getInstance().getMessage("Test");
        System.out.println("getPackage");
        EmojiPackage instance = null;
        EmojiPackage expResult = null;
        fail("The test case is a prototype.");
//DANIEL        EmojiPackage result = instance.getPackage();
//DANIEL        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//DANIEL        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class EmojiPackage.
     */
    @Test
    public void testAdd_EmojiPackage() {
        System.out.println("add");
        EmojiPackage emojiPackage = null;
//DANIEL        EmojiPackage.add(emojiPackage);
        // TODO review the generated test code and remove the default call to fail.
//DANIEL        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class EmojiPackage.
     */
    @Test
    public void testAdd_EmojiClass() {
        System.out.println("add");
        EmojiClass emojiClass = null;
        EmojiPackage instance = null;
//DANIEL        instance.add(emojiClass);
        // TODO review the generated test code and remove the default call to fail.
//DANIEL        fail("The test case is a prototype. -- Daniel");
    }

    /**
     * Test of add method, of class EmojiPackage.
     */
    @Test
    public void testAdd_List() {
        System.out.println("add");
        List<EmojiClass> emojiClasses = null;
        EmojiPackage instance = null;
//DANIEL        instance.add(emojiClasses);
        // TODO review the generated test code and remove the default call to fail.
//DANIEL        fail("The test case is a prototype.");
    }
    
}
