/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieCarte;

import LibrairieCarte.SymboleCarte;
import LibrairieCarte.ValeurCarte;

/**
 *
 * @author Pepefab
 */
public class Carte implements Comparable<Carte> {
    private ValeurCarte valeur;
    private SymboleCarte symbole;
    
    /**
     * 
     * @param valeur : la valeur de la carte (2,3,...,valet,dame,roi,as)
     * @param symbole : le symbole de la carte (pique, coeur, trefle, carreau)
     */
    public Carte(ValeurCarte valeur, SymboleCarte symbole){
        this.valeur = valeur;
        this.symbole = symbole;
    }

// <editor-fold  desc="get"> 
    /**
     * 
     * @return la valeur de la carte (2,3,...,roi,as)
     */
    public ValeurCarte getValeur() {
        return valeur;
    }

    /**
     * 
     * @return le symbole de la carte (pique, 
     */
    public SymboleCarte getSymbole() {
        return symbole;
    }
// </editor-fold>
    
    
    /**
     * 
     * @param c
     * @return 1 si la carte passée en paramètre est inférieure, -1 si elle est supérieure
     */
    @Override
    public int compareTo(Carte c) {
        int valeur;
        
        
        if(this.symbole.estAtout() && !c.symbole.estAtout()){
            return 1;
        } else if (!this.symbole.estAtout() && c.symbole.estAtout()){
            return -1;
        } else {
            valeur = this.getValeur().compareTo(c.getValeur());
            if(valeur > 0){
                return 1;
            } else if (valeur == 0){
                valeur = this.getSymbole().compareTo(c.getSymbole());
                if(valeur > 0){
                    return 1;
                } else if(valeur < 0){
                    return -1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }
    }
    
    @Override
    public String toString(){
        return this.valeur + " de " + this.symbole;
    }
}
