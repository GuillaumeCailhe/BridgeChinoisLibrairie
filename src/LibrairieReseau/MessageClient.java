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
public class MessageClient {
    
    CodeMessage code;
    Object donnees;
    
    public MessageClient(CodeMessage code, DataInputStream fluxEntrant){
        this.code = code;
    }
    
}
