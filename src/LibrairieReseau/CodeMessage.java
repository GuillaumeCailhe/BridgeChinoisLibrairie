/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

/**
 *
 * @author Pepefab
 */
public enum CodeMessage {
    /*
    0-49 : requête partie
    50-99 : init/fin manche
    100-127 : en jeu
    */
    PARTIE_JCJ((byte) 0),
    PARTIE_JCFACILE((byte) 1),
    PARTIE_JCINTERMEDIAIRE((byte) 2),
    PARTIE_JCDIFFICILE((byte) 3),
    PARTIE_CHARGER((byte) 4),
    PARTIE_DEMARRER((byte) 5), 
    
    PSEUDO((byte) 50),
    MAIN((byte) 51),
    PILES((byte) 52),
    TOUR_OK((byte) 53),
    TOUR_KO((byte) 54),
    VICTOIRE_MANCHE((byte) 55),
    EGALITE_MANCHE((byte) 56),
    DEFAITE_MANCHE((byte) 57),
    
    JOUER((byte) 100),
    JOUER_OK((byte) 101),
    JOUER_KO((byte) 102),
    PIOCHER((byte) 103),
    PIOCHER_OK((byte) 104),
    PIOCHER_KO((byte) 105),
    CAPITULER_MANCHE((byte) 106),
    CAPITULER_PARTIE((byte) 107),
    SAUVEGARDER((byte) 108),
    SAUVEGARDER_OK((byte) 109),
    SAUVEGARDER_KO((byte) 110),
    ANNULER((byte) 109),
    ANNULER_OK((byte) 110),
    ANNULER_KO((byte) 111),
    MESSAGE_CHAT((byte) 112);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
}
