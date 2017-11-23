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
package emojicodeeditor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Daniel Bergqvist
 */
public class GenerateTests {
    
    private static final String EMOJICODE_VERSION_0_5_FOLDER = "emojicode_test/compilation/";
    private static final String COMPILER_VERSION_0_5_TEMPLATE_FILE = "test/emojicode/test_templates/TestCompilerTemplate.java";
    private static final String COMPILER_VERSION_0_5_TEST_FOLDER = "test/emojicode/version_0_5/compiler/compilation/";
    private static final String RUNTIME_VERSION_0_5_TEST_FOLDER = "test/emojicode/version_0_5/runtime/compilation/";
    
    
    private GenerateTests() {
    }
    
    
    private static String readFile(String filename) throws IOException {
        byte[] encoded;
        encoded = Files.readAllBytes(Paths.get(filename));
        return new String(encoded, Charset.forName("UTF-8"));
    }
    
    
    private static void writeFile(String filename, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)))) {
            writer.print(content);
        }
    }
    
    
    public static void generateCompilerTest(String emojicodeFilename, String testFilename) throws IOException {
        String javaClassName = emojicodeFilename.replaceAll("\\.emojic$", "Test");
        String javaCodeFilename = emojicodeFilename.replaceAll("\\.emojic$", "Test.java");
        String emojiSource = readFile(EMOJICODE_VERSION_0_5_FOLDER + emojicodeFilename);
        String javaSource = readFile(COMPILER_VERSION_0_5_TEMPLATE_FILE);
        
//        System.out.println(javaSource);
        
        javaSource = javaSource.replace("package emojicode.test_templates;", "package emojicode.version_0_5.compiler.compilation;");
        javaSource = javaSource.replace("public class TestCompilerTemplate {", String.format("public class %s {", javaClassName));
        javaSource = javaSource.replace("public TestCompilerTemplate() {", String.format("public %s() {", javaClassName));
        javaSource = javaSource.replace("SOURCE_FILENAME = \"\";", String.format("SOURCE_FILENAME = \"%s\";", emojicodeFilename));
        javaSource = javaSource.replace("SOURCE_FILENAME = \"\";", String.format("SOURCE_FILENAME = \"%s\";", emojicodeFilename));
        
        writeFile(COMPILER_VERSION_0_5_TEST_FOLDER+javaCodeFilename, javaSource);
    }
    
    
    public static void generateRuntimeTest(String codeFilename, String resultFilename, String testFilename) throws IOException {
        String source = readFile(EMOJICODE_VERSION_0_5_FOLDER + codeFilename);
    }
    
    
    public static void generate() {
        try {
            generateCompilerTest("hello.emojic", "HelloTest.java");
            generateRuntimeTest("hello.emojic", "hello.txt", "HelloTest.java");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
