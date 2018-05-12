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
    // RECHERCHE PARTIE
    PARTIE_JCJ((byte) 0), 
    PARTIE_JCFACILE((byte) 1),
    PARTIE_JCINTERMEDIAIRE((byte) 2),
    PARTIE_JCDIFFICILE((byte) 3),
    PARTIE_CHARGER((byte) 4),
    PARTIE_DEMARRER((byte) 5), 
    PARTIE_NBMANCHES((byte) 6),
    // INIT PARTIE/MANCHE
    PSEUDO((byte) 7),
    MAIN((byte) 8),
    PILES((byte) 9),
    TOUR_OK((byte) 10),
    TOUR_KO((byte) 11),
    // FIN MANCHE
    VICTOIRE_MANCHE((byte) 12),
    EGALITE_MANCHE((byte) 13),
    DEFAITE_MANCHE((byte) 14),
    // ACTION EN JEU CLASSIQUE
    JOUER((byte) 15),
    JOUER_OK((byte) 16),
    JOUER_KO((byte) 17),
    PIOCHER((byte) 18),
    PIOCHER_OK((byte) 19),
    PIOCHER_KO((byte) 20),
    // ACTIONS HORS TEMPS DE JEU
    CAPITULER_MANCHE((byte) 21),
    CAPITULER_PARTIE((byte) 22),
    SAUVEGARDER((byte) 23),
    SAUVEGARDER_OK((byte) 24),
    SAUVEGARDER_KO((byte) 25),
    ANNULER((byte) 26),
    ANNULER_OK((byte) 27),
    ANNULER_KO((byte) 28),
    MESSAGE_CHAT((byte) 29);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
    
}
