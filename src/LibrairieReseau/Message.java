/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import java.io.DataInputStream;

/**
 *
 * @author Pepefab
 */
public class Message {
    
    private CodeMessage code;
    private Object donnees;
    
    public Message(CodeMessage code, DataInputStream fluxEntrant){
        this.code = code;
        this.donnees = null;
    }
 
    public CodeMessage getCode(){
        return code;
    }
    
    public Object getDonnees(){
        return donnees;
    }
    
}
