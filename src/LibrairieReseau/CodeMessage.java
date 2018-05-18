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
    JOUER((byte) 17),
    JOUER_OK((byte) 18),
    JOUER_KO((byte) 19),
    PIOCHER((byte) 20), 
    PIOCHER_OK((byte) 21),
    PIOCHER_KO((byte) 22), 
    JOUER_ADVERSAIRE((byte) 23), // [carte jouée]
    PIOCHER_ADVERSAIRE((byte) 24), // [carte piochée, carte retournée]
    // ACTIONS HORS TEMPS DE JEU
    CAPITULER_MANCHE((byte) 24),
    CAPITULER_PARTIE((byte) 25),
    SAUVEGARDER((byte) 26),
    SAUVEGARDER_OK((byte) 27),
    SAUVEGARDER_KO((byte) 28),
    ANNULER((byte) 29),
    ANNULER_OK((byte) 30),
    ANNULER_KO((byte) 31),
    MESSAGE_CHAT((byte) 32);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
    
}
