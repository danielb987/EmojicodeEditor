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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Analyze the report from CheckStyle
 * @author Daniel Bergqvist
 */
public class CheckStyleAnalyzer {
    
    private final String filename;
    
    
    public CheckStyleAnalyzer(String aFilename) {
        this.filename = aFilename;
    }
    
    
    public void analyze() {
        
        try {
            File inputFile = new File(filename);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
            userhandler.createReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void analyze2() {
        
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (!line.startsWith("<?xml "))
                throw new RuntimeException("XML file doesn't start with <?xml");
            
            line = br.readLine();
            
            while (line != null) {
                
                parseLine(line.trim());
                line = br.readLine();
                if (count++ > 10)
                    break;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckStyleAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckStyleAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void parseLine(String line) {
        
        String patternString = "^\\<(\\/)?\\s*(\\w+)((?:\\s+(?:\\w+\\=\\\".*?\\\")*)?\\s*(\\/?))\\>$";
        
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        
        System.out.println();
        System.out.format("%s\n", line);
        
        if (matcher.find()) {
            boolean hasFirstSlash = "/".equals(matcher.group(1));
            String tagName = matcher.group(2);
            String restOfLine = matcher.group(3);
            for (int i=0; i < matcher.groupCount(); i++) {
//                System.out.println("found: " + matcher.group(i));
            }
            
            boolean hasEndSlash = false;
            if (restOfLine.endsWith("/")) {
                hasEndSlash = true;
                restOfLine = restOfLine.substring(0, restOfLine.length()-1);
            }
            
            System.out.format("%b, %b, %s, %s\n", hasFirstSlash, hasEndSlash, tagName, restOfLine.trim());
            
            String subPatternString = "^(\\s+\\w+\\=\\\".*?\\\")*$";
            
            Pattern subPattern = Pattern.compile(subPatternString);
            Matcher subMatcher = pattern.matcher(restOfLine);
            
            if (matcher.find()) {
                for (int i=0; i < matcher.groupCount(); i++) {
                    System.out.println("found: " + matcher.group(i));
                }
            }
        } else {
            throw new RuntimeException("XML file has invalid line: "+line);
        }
    }
    
    
    
    
    
    
    private static class UserHandler extends DefaultHandler {

        String currentFilename = "";
        String currentError = "";
        AtomicInteger currentFileTotalErrorCount;
        
        int numFiles = 0;
        int numErrors = 0;
        
        Map<String, String> errorMessages = new HashMap<>();
        Map<String, AtomicInteger> totalErrorCount = new HashMap<>();
        Map<String, AtomicInteger> fileTotalErrorCount = new HashMap<>();
        Map<String, Map<String, AtomicInteger>> fileErrorCount = new HashMap<>();
        Map<String, AtomicInteger> currentFileErrorCount = new HashMap<>();
        
        
        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bNickName = false;
        boolean bMarks = false;
        String rollNo = null;
        
        
        public void createReport() {
            
            System.out.format("%d errors in %d files\n", numErrors, numFiles);
            
            for (Map.Entry<String, Map<String, AtomicInteger>> entry : fileErrorCount.entrySet()) {
                System.out.format("Filename: %s\n", entry.getKey());
                for (Map.Entry<String, AtomicInteger> subEntry : entry.getValue().entrySet()) {
                    System.out.format("Error: %s, count: %d\n", subEntry.getKey(), subEntry.getValue().get());
                }
            }
        }
        
        /**
         * Receive notification of the start of an element.
         *
         * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
         * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
         * @param qName The qualified name (with prefix), or the empty string if qualified names are not available.
         * @param attributes The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
         * @throws SAXException Any SAX exception, possibly wrapping another exception.
         */
        @Override
        public void startElement(
                String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            
//            System.out.println();
            
//            for (int i=0;  i < attributes.getLength(); i++) {
//                System.out.format("%s: %s: %s\n", qName, attributes.getQName(i), attributes.getValue(i));
//            }
            
            if ("checkstyle".equalsIgnoreCase(qName)) {
//                System.out.format("checkstyle tag\n");
            }
            if ("file".equalsIgnoreCase(qName)) {
//                System.out.format("file tag\n");
                numFiles++;
                currentFilename = attributes.getValue("name");
                currentFileErrorCount = new HashMap<>();
                fileErrorCount.put(currentFilename, currentFileErrorCount);
                currentFileTotalErrorCount = new AtomicInteger(0);
                fileTotalErrorCount.put(currentFilename, currentFileTotalErrorCount);
            }
            if ("error".equalsIgnoreCase(qName)) {
//                System.out.format("error tag\n");
                numErrors++;
                String error = attributes.getValue("source");
                String errorMessage = attributes.getValue("message");
                if (error == null) {
                    System.out.println("Error is null");
                    for (int i=0;  i < attributes.getLength(); i++) {
                        System.out.format("-- %s: %s\n", attributes.getQName(i), attributes.getValue(i));
                        System.exit(0);
                    }
                }
//                System.out.format("File: %s, error: %s, message: %s\n", currentFilename, error, errorMessage);
                
                currentFileTotalErrorCount.addAndGet(1);
                
                errorMessages.putIfAbsent(error, errorMessage);
                AtomicInteger value = totalErrorCount.putIfAbsent(error, new AtomicInteger(1));
                if (value != null) {
                    value.addAndGet(1);
                }
                
//                currentFileErrorCount = new HashMap<>();
                value = currentFileErrorCount.putIfAbsent(error, new AtomicInteger(1));
                if (value != null) {
                    value.addAndGet(1);
                }
            }
        }

        @Override
        public void endElement(
                String uri, String localName, String qName) throws SAXException {

            if (qName.equalsIgnoreCase("student")) {
                if (("393").equals(rollNo) && qName.equalsIgnoreCase("student")) {
                    System.out.println("End Element :" + qName);
                }
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {

            if (bFirstName && ("393").equals(rollNo)) {
                //age element, set Employee age
                System.out.println("First Name: " + new String(ch, start, length));
                bFirstName = false;
            } else if (bLastName && ("393").equals(rollNo)) {
                System.out.println("Last Name: " + new String(ch, start, length));
                bLastName = false;
            } else if (bNickName && ("393").equals(rollNo)) {
                System.out.println("Nick Name: " + new String(ch, start, length));
                bNickName = false;
            } else if (bMarks && ("393").equals(rollNo)) {
                System.out.println("Marks: " + new String(ch, start, length));
                bMarks = false;
            }
        }
    }
    
}
