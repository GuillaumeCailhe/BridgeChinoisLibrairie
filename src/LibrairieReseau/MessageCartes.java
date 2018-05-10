/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import LibrairieCarte.Carte;
import java.io.DataInputStream;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class MessageCartes extends Message{
    
    private ArrayList<Carte> donnees;
    
    public MessageCartes(CodeMessage code, DataInputStream fluxEntrant) {
        super(code,fluxEntrant);
        this.donnees = null;
    }
   
    
}
