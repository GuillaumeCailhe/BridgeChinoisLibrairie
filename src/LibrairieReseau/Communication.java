/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibrairieReseau;

import LibrairieCarte.Carte;
import LibrairieMoteur.ResultatFinDePartie;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pepefab
 */
public class Communication implements Runnable {

    Socket client;
    DataInputStream fluxEntrant;
    DataOutputStream fluxSortant;

    Queue<Message> buffer;

    public Communication(Socket client) {
        this.client = client;
        try {
            this.fluxEntrant = new DataInputStream(client.getInputStream());
            this.fluxSortant = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new java.lang.Error("Erreur I/O socket");
        }

        this.buffer = new LinkedList<Message>();
    }

    @Override
    public void run() {
        while(true){
            try {
                recevoirDonnees();
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void envoyer(CodeMessage code) throws IOException{
        byte[] donnees = new byte[1];
        donnees[0] = code.getCode();
        envoyerDonnees(donnees);
    }
    
    public void envoyerEntier(CodeMessage code, byte entier) throws IOException{
        byte[] donnees = new byte[2];
        donnees[0] = code.getCode();
        donnees[1] = entier;
        envoyerDonnees(donnees);
    }    
    
    public void envoyerString(CodeMessage code, String chaine) throws IOException{
        byte[] donnees = new byte[1];
        donnees[0] = code.getCode();
        donnees[1] = (byte) chaine.getBytes().length;
        int i = 2;
        for(byte b : chaine.getBytes()){
            donnees[i] = b;
            i++;
        }
        envoyerDonnees(donnees);
    }    
    
    public void envoyerCartes(CodeMessage code, ArrayList<Carte> cartes) throws IOException{
        byte[] donnees = new byte[2+cartes.size()*2];
        donnees[0] = code.getCode();
        donnees[1] = (byte) cartes.size();
        int i = 2;
        for(Carte c : cartes){
            donnees[i] = (byte) c.getValeur().getValeur();
            donnees[i+1] = (byte) c.getSymbole().getSymbole();
            i++;
        }
        envoyerDonnees(donnees);
    }
    
    private void envoyerDonnees(byte[] donnees) throws IOException{
        this.fluxSortant.write(donnees);
    }

    public void recevoirDonnees() throws IOException {
        if(fluxEntrant.available() > 0){
            CodeMessage code = CodeMessage.values()[fluxEntrant.readByte()];
            Message msg;
            switch (code) {
                case PARTIE_JCJ:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PARTIE_JCFACILE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PARTIE_JCINTERMEDIAIRE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PARTIE_JCDIFFICILE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PARTIE_CHARGER:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PARTIE_DEMARRER:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PSEUDO:
                    msg = new MessageString(code,fluxEntrant);
                    break;
                case MAIN:
                    msg = new MessageCartes(code,fluxEntrant);
                    break;
                case PILES:
                    msg = new MessageCartes(code,fluxEntrant);
                    break;
                case TOUR_OK:
                    msg = new Message(code,fluxEntrant);
                    break;
                case TOUR_KO:
                    msg = new Message(code,fluxEntrant);
                    break;
                case VICTOIRE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case EGALITE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case DEFAITE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case JOUER:
                    msg = new MessageEntier(code,fluxEntrant);
                    break;
                case JOUER_OK:
                    msg = new Message(code,fluxEntrant);
                    break;
                case JOUER_KO:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PIOCHER:
                    msg = new MessageEntier(code,fluxEntrant);
                    break;
                case PIOCHER_OK:
                    msg = new Message(code,fluxEntrant);
                    break;
                case PIOCHER_KO:
                    msg = new Message(code,fluxEntrant);
                    break;
                case CAPITULER_MANCHE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case CAPITULER_PARTIE:
                    msg = new Message(code,fluxEntrant);
                    break;
                case SAUVEGARDER:
                    msg = new Message(code,fluxEntrant);
                    break;
                case SAUVEGARDER_OK:
                    msg = new Message(code,fluxEntrant);
                    break;
                case SAUVEGARDER_KO:
                    msg = new Message(code,fluxEntrant);
                    break;
                case ANNULER:
                    msg = new Message(code,fluxEntrant);
                    break;
                case ANNULER_OK:
                    msg = new Message(code,fluxEntrant);
                    break;
                case ANNULER_KO:
                    msg = new Message(code,fluxEntrant);
                    break;
                case MESSAGE_CHAT:
                    msg = new MessageString(code,fluxEntrant);
                    break;
                default:
                    throw new Error("Code de message indécodable");
            }
            this.buffer.add(msg);
        }
    }
    
    public int getNbMessages(){
        return buffer.size();
    }
    
    public Message getMessage(){
        if(buffer.size() > 0){
            return buffer.remove();
        } else {
            return null;
        }
    }
    
    public Socket getSocket(){
        return client;
    }

}
