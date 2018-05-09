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

/**
 *
 * @author Pepefab
 */
public class Communication {

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

    /**
     * Sert à envoyer le pseudo de l'adversaire
     *
     * @param pseudo
     */
    public void envoyerPseudo(String pseudo) {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeMessage.PSEUDO);
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
    

    public void envoyerResultatFinDePartie(ResultatFinDePartie resultatFinDePartie){
        StringBuilder sb = new StringBuilder();

        switch(resultatFinDePartie){
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

    private void envoyerDonnees(String donnees) {
        try {
            fluxSortant.writeChars(donnees);
        } catch (Exception e) {
            throw new java.lang.Error("Erreur d'envoi de données");
        }
    }

    public void recevoirDonnees() throws IOException {
        CodeMessage code = CodeMessage.values()[fluxEntrant.readByte()];
        Message msg;
        switch (code) {
            case PSEUDO:
                msg = new MessageString(code, fluxEntrant);
            case MAIN:
                msg = new MessageEntier(code, fluxEntrant);
            case JOUER:
                msg = new MessageEntier(code, fluxEntrant);
            case JOUER_OK:
                msg = new Message(code, fluxEntrant);
            case JOUER_KO:
                msg = new Message(code, fluxEntrant);
            case PIOCHER:
                msg = new MessageEntier(code, fluxEntrant);
            case PIOCHER_OK:
                msg = new Message(code, fluxEntrant);
            case PIOCHER_KO:
                msg = new Message(code, fluxEntrant);
            case CAPITULER:
                msg = new Message(code, fluxEntrant);
            case ANNULER:
                msg = new Message(code, fluxEntrant);
            case SAUVEGARDER:
                msg = new Message(code, fluxEntrant);
            case CHARGER:
                msg = new Message(code, fluxEntrant);
            case VICTOIRE:
                msg = new Message(code, fluxEntrant);
            case DEFAITE:
                msg = new Message(code, fluxEntrant);
            case EGALITE:
                msg = new Message(code, fluxEntrant);
            case MESSAGE_CHAT:
                msg = new MessageString(code, fluxEntrant);
            default:
                msg = null;
        }
        this.buffer.add(msg);
    }

}
