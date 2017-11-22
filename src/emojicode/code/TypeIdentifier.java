/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emojicode.code;

import emojicode.SourcePosition;

/**
 *
 * @author daniel
 */
public class TypeIdentifier {
    
    String name;
    String namespace;
    SourcePosition position;
    
    public TypeIdentifier(String name, String namespace, SourcePosition position) {
        this.name = name;
        this.namespace = namespace;
        this.position = position;
    }
    
}
