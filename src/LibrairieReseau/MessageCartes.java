/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;
import LibrairieCarte.Carte;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Pepefab
 */
public class MessageCartes extends Message{
    
    private ArrayList<Carte> donnees;
    
    public MessageCartes(CodeMessage code, DataInputStream fluxEntrant) throws IOException {
        super(code,fluxEntrant);
        this.donnees = new ArrayList<Carte>();
        byte taille = fluxEntrant.readByte(); // nombre de cartes
        ValeurCarte valeurCarte;
        SymboleCarte symboleCarte;
        for(int i = 0; i < taille; i++){
            int val = fluxEntrant.readByte();
            int symbole = fluxEntrant.readByte();
            if(val == -1 || symbole == -1){ // null
                this.donnees.add(null);
            } else {
                valeurCarte = ValeurCarte.values()[val];
                symboleCarte = SymboleCarte.values()[symbole];
                this.donnees.add(new Carte(valeurCarte,symboleCarte));
            }
        }
    }
   
    @Override
    public ArrayList<Carte> getDonnees(){
        return this.donnees;
    }
}
