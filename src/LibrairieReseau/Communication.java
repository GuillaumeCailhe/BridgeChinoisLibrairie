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
    
    
// <editor-fold desc="Lancer une partie">
    /**
     * Indique que le joueur souhaite lancer une partie PVP.
     */
    public void envoyerJcJ() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PARTIE_JCJ);
        envoyerDonnees(sb.toString());
    }
    
    /**
     * Indique que le joueur souhaite lancer une partie contre IA facile.
     */
    public void envoyerJcFacile() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PARTIE_JCFACILE);
        envoyerDonnees(sb.toString());
    }
    
    /**
     * Indique que le joueur souhaite lancer une partie contre IA intermédiaire.
     */
    public void envoyerJcIntermediaire() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PARTIE_JCINTERMEDIAIRE);
        envoyerDonnees(sb.toString());
    }
    
    /**
     * Indique que le joueur souhaite lancer une partie contre IA diffilce.
     */
    public void envoyerJcDifficile() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PARTIE_JCDIFFICILE);
        envoyerDonnees(sb.toString());
    }
    
    /**
     * Indique que le joueur souhaite charger sa sauvegarde (enregistré côté serveur, unique ?).
     */
    public void envoyerChargerPartie() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PARTIE_CHARGER);
        envoyerDonnees(sb.toString());
    }
    
// </editor-fold>
    
// <editor-fold desc="Initialisation partie">
    /**
     * Sert à envoyer le pseudo de l'adversaire
     *
     * @param pseudo
     */
    public void envoyerPseudo(String pseudo) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PSEUDO.getCode());
        sb.append((byte) pseudo.length());
        sb.append(pseudo);
        envoyerDonnees(sb.toString());
    }

    /**
     * Sert à envoyer la main au début d'une manche
     *
     * @param main
     */
    public void envoyerMain(ArrayList<Carte> main) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.MAIN);
        for (Carte c : main) {
            sb.append((byte) c.getValeur().getValeur());
            sb.append((byte) c.getSymbole().getSymbole());
        }
        envoyerDonnees(sb.toString());
    }
// </editor-fold>

    /**
     * Sert à alerter un joueur de la validité de son coup.
     *
     * @param estAccepte booléen représentant la validité du coup.
     */
    public void envoyerAcceptationCoup(boolean estAccepte) {
        StringBuilder sb = new StringBuilder();

        if (estAccepte) {
            sb.append(CodeMessage.JOUER_OK);
        } else {
            sb.append(CodeMessage.JOUER_KO);
        }

        envoyerDonnees(sb.toString());
    }

    /**
     * Sert à alerter un joueur de la validité de son coup après une pioche.
     *
     * @param estAccepte booléen représentant la validité de la pioche.
     */
    public void envoyerAcceptationPioche(boolean estAccepte) {
        StringBuilder sb = new StringBuilder();

        if (estAccepte) {
            sb.append(CodeMessage.PIOCHER_OK);
        } else {
            sb.append(CodeMessage.PIOCHER_KO);
        }

        envoyerDonnees(sb.toString());
    }
    
    /**
     * Envoie le résultat de fin de partie (victoire, défaite, égalité).
     * @param resultatFinDePartie une énumération avec pour valeurs possibles : Victoire, Défaite, égalité
     */
    public void envoyerResultatFinDePartie(ResultatFinDePartie resultatFinDePartie) {
        StringBuilder sb = new StringBuilder();

        switch (resultatFinDePartie) {
            case DEFAITE:
                sb.append(CodeMessage.DEFAITE);
                break;
            case VICTOIRE:
                sb.append(CodeMessage.DEFAITE);
                break;
            case EGALITE:
                sb.append(CodeMessage.EGALITE);
                break;
        }

        envoyerDonnees(sb.toString());
    }

    /**
     * Joue la carte indicée par indiceCarte dans la main sur le plateau.
     *
     * @param indiceCarte l'indice de la carte dans la main du joueur.
     */
    public void envoyerCoup(int indiceCarte) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.JOUER);
        sb.append(indiceCarte);
        envoyerDonnees(sb.toString());
    }

    /**
     * Pioche la carte de la pile d'indice indicePile.
     *
     * @param indicePile l'indice de la pile que le joueur a choisi.
     */
    public void envoyerPioche(int indicePile) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.JOUER);
        sb.append(indicePile);
        envoyerDonnees(sb.toString());
    }

    /**
     * Indique que le joueur souhaite abandonner.
     */
    public void envoyerCapituler() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.CAPITULER);
        envoyerDonnees(sb.toString());
    }

    /**
     * Indique le joueur souhaite annuler son coup.
     */
    public void envoyerAnnuler() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.ANNULER);
        envoyerDonnees(sb.toString());
    }

    /**
     * Indique que le joueur souhaite sauvegarder la partie courante.
     */
    public void envoyerSauvegarder() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.SAUVEGARDER);
        envoyerDonnees(sb.toString());
    }


    
    /**
     * Envoie un message dans le chat.
     *
     * @param message le message envoyé par le joueur.
     */
    public void envoyerMessageChat(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.MESSAGE_CHAT);
        sb.append((byte) message.length());
        sb.append(message);
        envoyerDonnees(sb.toString());
    }

    private void envoyerDonnees(String donnees) {
        try {
            System.out.println(donnees);
            fluxSortant.writeChars(donnees);
        } catch (Exception e) {
            throw new java.lang.Error("Erreur d'envoi de données");
        }
    }

    public void recevoirDonnees() throws IOException {
        if(fluxEntrant.available() > 0){
            CodeMessage code = CodeMessage.values()[fluxEntrant.readByte()];
            Message msg;
            switch (code) {
                case PARTIE_JCJ:
                    msg = new Message(code,fluxEntrant);
                case PARTIE_JCFACILE:
                    msg = new Message(code,fluxEntrant);
                case PARTIE_JCINTERMEDIAIRE:
                    msg = new Message(code,fluxEntrant);
                case PARTIE_JCDIFFICILE:
                    msg = new Message(code,fluxEntrant);
                case PARTIE_CHARGER:
                    msg = new Message(code,fluxEntrant);
                case PARTIE_DEMARRER:
                    msg = new Message(code,fluxEntrant);
                case PSEUDO:
                    msg = new MessageString(code,fluxEntrant);
                case MAIN:
                    msg = new MessageCartes(code,fluxEntrant);
                case PILES:
                    msg = new MessageCartes(code,fluxEntrant);
                case TOUR_OK:
                    msg = new Message(code,fluxEntrant);
                case TOUR_KO:
                    msg = new Message(code,fluxEntrant);
                case VICTOIRE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                case EGALITE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                case DEFAITE_MANCHE:
                    msg = new Message(code,fluxEntrant);
                case JOUER:
                    msg = new MessageEntier(code,fluxEntrant);
                case JOUER_OK:
                    msg = new Message(code,fluxEntrant);
                case JOUER_KO:
                    msg = new Message(code,fluxEntrant);
                case PIOCHER:
                    msg = new MessageEntier(code,fluxEntrant);
                case PIOCHER_OK:
                    msg = new Message(code,fluxEntrant);
                case PIOCHER_KO:
                    msg = new Message(code,fluxEntrant);
                case CAPITULER_MANCHE:
                    msg = new Message(code,fluxEntrant);
                case CAPITULER_PARTIE:
                    msg = new Message(code,fluxEntrant);
                case SAUVEGARDER:
                    msg = new Message(code,fluxEntrant);
                case SAUVEGARDER_OK:
                    msg = new Message(code,fluxEntrant);
                case SAUVEGARDER_KO:
                    msg = new Message(code,fluxEntrant);
                case ANNULER:
                    msg = new Message(code,fluxEntrant);
                case ANNULER_OK:
                    msg = new Message(code,fluxEntrant);
                case ANNULER_KO:
                    msg = new Message(code,fluxEntrant);
                case MESSAGE_CHAT:
                    msg = new MessageString(code,fluxEntrant);
                default:
                    msg = null;
            }
            this.buffer.add(msg);
        }
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
