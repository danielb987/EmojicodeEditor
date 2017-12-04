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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Daniel Bergqvist
 */
public class EmojicodeDocumentation {
    
    private static final EmojicodeDocumentation emojicodeDocumentation = new EmojicodeDocumentation();
    
//////////    private final List<EmojicodePackage> emojicodePackages = new ArrayList<>();
    
//    private Map<String, Emoji> emojiMap = new HashMap<>();
//    private List<EmojiToolbarData> emojiToolbarDataList = new ArrayList<>();
    
    public static EmojicodeDocumentation getInstance() {
        return emojicodeDocumentation;
    }
    
    private EmojicodeDocumentation() {
//        EmojicodeSyntax.getInstance();
//        emojicodePackages.add(EmojicodePackage.NULL_EMOJICODE_PACKAGE);
//        readStandardPackages();
    }
    
//    public List<EmojicodePackage> getEmojicodePackages() {
//        return new ArrayList<>(emojicodePackages);
//    }
    
    private Object readFile(String fileName) throws ParseException, IOException {
        
        JSONParser parser = new JSONParser();
        
        System.out.println("Filename: "+fileName);
        byte[] encoded;
        encoded = Files.readAllBytes(Paths.get(fileName));
        Object obj = parser.parse(new String(encoded, Charset.forName("UTF-8")));
////        print(obj, "");
        return obj;
    }
    
    private String convertTooltipTextToHTML(String str) {
        // Return null if we don't have any documentation
        if (str == null)
            return null;
//        System.out.format("Str: %s%n", str.trim().replaceAll("\\s+", " "));
//        StringBuilder string = new StringBuilder("<html>"+str.trim().replaceAll("\\n", "<br />")+"</html>");
        StringBuilder string = new StringBuilder("<html>");
        string.append("<b>Description</b><br />");
//        string.append(str.trim().replaceAll("\\n", "<br />"));
//        str = str.trim();
        str = str.replaceAll("\\n", "<br />");
        str = str.replaceAll("^\\s+", "");
        string.append(str.replaceAll("\\s", "&nbsp;"));
        string.append("<hr /><b>Parameters</b><br />");
        string.append("Test");
        string.append("</html>");
        return string.toString();
//        return "<html>"+str.trim().replaceAll("\\n", "<br />")+"</html>";
//        return str.trim().replaceAll("\\s+", " ");
    }
    
    private void parsePackage(String packageName, Object obj) {
/*        
        EmojicodePackage emojicodePackage = new EmojicodePackage(packageName);
        emojicodePackages.add(emojicodePackage);
        
        EmojiToolbarData emojiToolbarData;
        JSONObject jsonObject = (JSONObject)obj;
        
        JSONArray valueTypes = (JSONArray) jsonObject.get("valueTypes");
        if (valueTypes.size() > 0) {
//            emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" .valueTypes");
            emojiToolbarData = new EmojiToolbarData(packageName+" .ValueTypes", "Instances of value types are passed by value", "Instances of value types are passed by value");
            emojicodePackage.addToolbarData(emojiToolbarData);
            for (Object o : valueTypes) {
                JSONObject valueType = (JSONObject)o;
                String name = (String) valueType.get("name");
                String documentation = (String) valueType.get("documentation");
                Emojis.Emoji emoji = new Emojis.Emoji(name, documentation, convertTooltipTextToHTML(documentation));
                emojiToolbarData.add(emoji);
            }
        }
        
        JSONArray classes = (JSONArray) jsonObject.get("classes");
        if (classes.size() > 0) {
//            emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" .classes");
            emojiToolbarData = new EmojiToolbarData(packageName+" .Classes", "Instances of classes are always allocated on the heap and passed by reference.", "Instances of classes are always allocated on the heap and passed by reference.");
            emojicodePackage.addToolbarData(emojiToolbarData);
            for (Object o : classes) {
                JSONObject _class = (JSONObject)o;
                String name = (String) _class.get("name");
                String documentation = (String) _class.get("documentation");
                Emojis.Emoji emoji = new Emojis.Emoji(name, documentation, convertTooltipTextToHTML(documentation));
                emojiToolbarData.add(emoji);
            }
        }
        
        classes = (JSONArray) jsonObject.get("classes");
        if (classes.size() > 0) {
//            emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" .classes");
            for (Object o : classes) {
                JSONObject _class = (JSONObject)o;
                String name = (String) _class.get("name");
                String documentation = (String) _class.get("documentation");
                
                JSONArray classMethods = (JSONArray) _class.get("typeMethods");
                if ((classMethods != null) && (classMethods.size() > 0)) {
//                    emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" ."+name+".methods ",documentation);
//                    emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" ."+name+". ",documentation);
                    emojiToolbarData = new EmojiToolbarData(packageName+" ."+name+". ",documentation,convertTooltipTextToHTML(documentation));
                    emojicodePackage.addToolbarData(emojiToolbarData);
                    for (Object om : classMethods) {
                        JSONObject _method = (JSONObject)om;
                        String methodName = (String) _method.get("name");
                        String methodDocumentation = (String) _method.get("documentation");
                        Emojis.Emoji emoji = new Emojis.Emoji(methodName,methodDocumentation,convertTooltipTextToHTML(methodDocumentation));
                        emojiToolbarData.add(emoji);
                    }
                }
                
                JSONArray methods = (JSONArray) _class.get("methods");
                if ((methods != null) && (methods.size() > 0)) {
//                    emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" ."+name+".methods ",documentation);
//                    emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" ."+name+". ",documentation);
                    emojiToolbarData = new EmojiToolbarData(packageName+" ."+name+". ",documentation,convertTooltipTextToHTML(documentation));
                    emojicodePackage.addToolbarData(emojiToolbarData);
                    for (Object om : methods) {
                        JSONObject _method = (JSONObject)om;
                        String methodName = (String) _method.get("name");
                        String methodDocumentation = (String) _method.get("documentation");
                        Emojis.Emoji emoji = new Emojis.Emoji(methodName,methodDocumentation,convertTooltipTextToHTML(methodDocumentation));
                        emojiToolbarData.add(emoji);
                    }
                }
                
//                Emojis.Emoji emoji = new Emojis.Emoji(name, documentation);
//                emojiToolbarData.add(emoji);
            }
        }
        
        JSONArray enums = (JSONArray) jsonObject.get("enums");
        if (enums.size() > 0) {
//            emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" .enums");
            emojiToolbarData = new EmojiToolbarData(packageName+" .Enums","Enumerations are a special kind of value type that represent a set of options from which one can be chosen.","Enumerations are a special kind of value type that represent a set of options from which one can be chosen.");
            emojicodePackage.addToolbarData(emojiToolbarData);
            for (Object o : enums) {
                JSONObject _enum = (JSONObject)o;
                String name = (String) _enum.get("name");
                String documentation = (String) _enum.get("documentation");
                Emojis.Emoji emoji = new Emojis.Emoji(name,documentation,convertTooltipTextToHTML(documentation));
                emojiToolbarData.add(emoji);
            }
        }
        
        JSONArray protocols = (JSONArray) jsonObject.get("protocols");
        if (protocols.size() > 0) {
//            emojiToolbarData = Emojis.getInstance().getNewToolbarData(packageName+" .protocols");
            emojiToolbarData = new EmojiToolbarData(packageName+" .Protocols");
            emojicodePackage.addToolbarData(emojiToolbarData);
            for (Object o : protocols) {
                JSONObject protocol = (JSONObject)o;
                String name = (String) protocol.get("name");
                String documentation = (String) protocol.get("documentation");
                Emojis.Emoji emoji = new Emojis.Emoji(name,documentation,convertTooltipTextToHTML(documentation));
                emojiToolbarData.add(emoji);
            }
        }
        
//        JSONObject jsonObject = (JSONObject) ((JSONObject)obj).get("valueTypes");
//        jsonObject
*/
    }
    
    private void readPackage(String name, String fileName) {
        
        Object obj;
        try {
            obj = readFile(fileName);
////            printJSONObject(obj, "   ");
            parsePackage(name, obj);
        } catch (ParseException | IOException ex) {
            Logger.getLogger(EmojicodeDocumentation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readStandardPackages() {
        
        final String packagesPath = "emojicode/packages/";
        
        Object obj;
        try {
            obj = readFile(packagesPath + "packages.json");
            
            JSONArray jsonArray = (JSONArray)obj;
            
            for (Object o : jsonArray) {
                System.out.format("Package: %s%n", o);
                readPackage((String)o, packagesPath+((String)o)+"/package.json");
            }
        } catch (ParseException | IOException ex) {
            Logger.getLogger(EmojicodeDocumentation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
