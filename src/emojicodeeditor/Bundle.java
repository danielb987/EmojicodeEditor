/*
 * The MIT License
 *
 * Copyright 2017 daniel.
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Bergqvist
 */
public class Bundle {
    
    private final String name = "/emojicodeeditor/Bundle.properties";
    
    private final List<String> keys = new ArrayList<>();
    private final Map<String, String> map = new HashMap<>();
    
    private static volatile Bundle bundle;
    private static final Object lock = new Object();
    
    /**
     * Get an instance of Bundle.
     * 
     * @return instance
     */
    // We need to chech bundle outside the synchronized block for performance
    // issues since synchronize is slow and we only need to do synchronize the
    // first time then we want to create the instance.
    // And we need to check bundle inside the synchronized block since
    // another thread may already have created the instance.
    @SuppressWarnings("DoubleCheckedLocking")
    public static Bundle getInstance() {
        if (bundle == null) {
            synchronized(lock) {
                if (bundle == null)
                    bundle = new Bundle();
            }
        }
        return bundle;
    }
    
    private Bundle() {
        
        ClassLoader cl = this.getClass().getClassLoader();
/*        
        try {
//            Enumeration<URL> r = cl.getResources(name);
            Enumeration<URL> r = cl.getResources(Resources);
            while (r.hasMoreElements())
                System.out.println(r.nextElement().toString());
        } catch (IOException ex) {
            Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (1==1)
            System.exit(1);
*/        
//        InputStream in = cl.getResourceAsStream(name);
        InputStream in = Bundle.class.getResourceAsStream(name);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")))) {
            String str;
            while ((str = reader.readLine()) != null) {
                str = str.trim();
                if ((! str.startsWith("#")) && (! str.isEmpty())) {    // Ignore comments and empty lines
                    System.out.println(str);
                    int pos = str.indexOf("=");
                    String parts[] = str.split("=",2);
                    String key = parts[0].trim();
                    keys.add(key);
                    map.put(key, parts[1].trim());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*    
    public String getMessage(Locale locale, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(name, locale);
        if (rb.containsKey(key))
            return rb.getString(key);
        else
            return "Resource '" + key + "' is missing";
    }
    
    public String getMessage(String key) {
        return getMessage(Locale.getDefault(), key);
    }
*/    
    /**
     * Get a message from the bundle
     * @param key the key to the message
     * @return the message
     */
    public String getMessage(String key) {
        return map.get(key);
    }
    
    public List<String> getKeys() {
        return new ArrayList<>(keys);
    }
    
}
