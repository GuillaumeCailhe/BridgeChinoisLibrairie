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
    PSEUDO((byte) 0),
    MAIN((byte) 1),
    JOUER((byte) 2),
    JOUER_OK((byte) 3),
    JOUER_KO((byte) 4),
    PIOCHER((byte) 5),
    PIOCHER_OK((byte) 6),
    PIOCHER_KO((byte) 7),
    CAPITULER((byte) 8),
    ANNULER((byte) 9),
    SAUVEGARDER((byte) 10),
    VICTOIRE((byte) 12),
    DEFAITE((byte) 13),
    EGALITE((byte) 14),
    MESSAGE_CHAT((byte) 15),
    PARTIE_JCJ((byte) 100),
    PARTIE_JCFACILE((byte) 101),
    PARTIE_JCINTERMEDIAIRE((byte) 102),
    PARTIE_JCDIFFICILE((byte) 103),
    PARTIE_CHARGER((byte) 104);
    
    private byte code;

    CodeMessage(byte code){
        this.code = code;
    }
    
    public byte getCode(){
        return code;
    }
    
}
