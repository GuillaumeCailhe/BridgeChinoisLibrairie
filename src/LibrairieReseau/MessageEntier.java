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
public class MessageEntier extends Message {
    
    private int donnees;
    
    public MessageEntier(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        donnees = fluxEntrant.readByte();
    }
    
    @Override
    public Integer getDonnees(){
        return this.donnees;
    }
}
