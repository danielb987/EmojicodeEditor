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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private final String reportFilename;
    
    
    public CheckStyleAnalyzer(String aFilename, String aReportFilename) {
        this.filename = aFilename;
        this.reportFilename = aReportFilename;
    }
    
    
    public void analyze() {
        
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(reportFilename)))) {
            File inputFile = new File(filename);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler(writer);
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
        
        private final PrintWriter writer;
        
        private String currentFilename = "";
        private String currentError = "";
        private AtomicInteger currentFileTotalErrorCount;
        
        private int numFiles = 0;
        private int numErrors = 0;
        
        private final Map<String, String> errorMessages = new HashMap<>();
        private final Map<String, AtomicInteger> totalErrorCount = new HashMap<>();
        private final Map<String, AtomicInteger> fileTotalErrorCount = new HashMap<>();
        private final Map<String, Map<String, AtomicInteger>> fileErrorCount = new HashMap<>();
        private Map<String, AtomicInteger> currentFileErrorCount = new HashMap<>();
        
        
        UserHandler(PrintWriter aWriter) {
            this.writer = aWriter;
        }
        
        public void createReport() {
            
            writer.println("<!DOCTYPE html>");
            writer.println("<html dir=\"ltr\" lang=\"en\">");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\" />");
            writer.println("<title>Checkstyle report</title>");
            writer.println("</head>");
            writer.println("<body>");
            
            System.out.format("%d errors in %d files\n", numErrors, numFiles);
            
            for (Map.Entry<String, Map<String, AtomicInteger>> entry : fileErrorCount.entrySet()) {
                writer.println("<table>");
                writer.format("<tr><td>%s</td><td>%d</td></tr>\n", entry.getKey(), fileTotalErrorCount.get(entry.getKey()).get());
                System.out.format("Filename: %s\n", entry.getKey());
                for (Map.Entry<String, AtomicInteger> subEntry : entry.getValue().entrySet()) {
                    writer.format("<tr><td>%s</td><td>%d</td></tr>\n", subEntry.getKey(), subEntry.getValue().get());
                    System.out.format("Error: %s, count: %d\n", subEntry.getKey(), subEntry.getValue().get());
                }
                writer.println("</table>");
            }
            
            writer.println("</body>");
            writer.println("</html>");
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
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
        }
    }
    
}
