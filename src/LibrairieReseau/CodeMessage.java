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
    VICTOIRE_PLI((byte) 12),
    DEFAITE_PLI((byte) 13),
    VICTOIRE_MANCHE((byte) 14),
    EGALITE_MANCHE((byte) 15),
    DEFAITE_MANCHE((byte) 16),
    // ACTION EN JEU CLASSIQUE
    ATOUT((byte) 17),
    JOUER((byte) 18),
    JOUER_OK((byte) 19),
    JOUER_KO((byte) 20),
    PIOCHER((byte) 21), 
    PIOCHER_OK((byte) 22),
    PIOCHER_KO((byte) 23), 
    JOUER_ADVERSAIRE((byte) 24), // [carte jouée]
    PIOCHER_ADVERSAIRE((byte) 25), // [carte piochée, carte retournée]
    // ACTIONS HORS TEMPS DE JEU
    CAPITULER_MANCHE((byte) 26),
    CAPITULER_PARTIE((byte) 27),
    SAUVEGARDER((byte) 28),
    SAUVEGARDER_OK((byte) 28),
    SAUVEGARDER_KO((byte) 29),
    ANNULER((byte) 30),
    ANNULER_OK((byte) 31),
    ANNULER_KO((byte) 32),
    MESSAGE_CHAT((byte) 33);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
    
}
