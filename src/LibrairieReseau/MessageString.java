/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Pepefab
 */
public class MessageString extends Message {
    
    String donnees;
    
    public MessageString(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        int taille = fluxEntrant.readByte();
        byte[] intermediaire = new byte[taille];
        fluxEntrant.read(intermediaire, 0, taille);
        this.donnees = new String(intermediaire);
    }
   
    @Override
    public String getDonnees(){
        return this.donnees;
    }
}
