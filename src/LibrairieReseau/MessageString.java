/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Pepefab
 */
public class MessageString extends Message {
    
    String donnees;
    
    public MessageString(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        int taille = fluxEntrant.readByte();
        donnees = "";
        System.out.println("test");
        for(int i=0; i<taille; i++){
            donnees += fluxEntrant.readChar();
        }
        System.out.println(donnees);
    }
    
}
