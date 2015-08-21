/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Contructeur de deck pour le JackFoolery
 *
 * @author Régis
 */
public class DeckJackFoolery {

    private final ArrayList<Carte> deck = new ArrayList<>();

    /**
     * Contructeur de classe de deck,cartes initialisées
     */
    public DeckJackFoolery() {
        for (int cpt1 = 0; cpt1 < 2; cpt1++) {
            for (int cpt2 = 1; cpt2 <= 13; cpt2++) {
                deck.add(new Carte(cpt2, CouleurCarte.CARREAU));
            }
            for (int cpt2 = 1; cpt2 <= 13; cpt2++) {
                deck.add(new Carte(cpt2, CouleurCarte.PIQUE));
            }
            for (int cpt2 = 1; cpt2 <= 13; cpt2++) {
                deck.add(new Carte(cpt2, CouleurCarte.TREFLE));
            }
            for (int cpt2 = 1; cpt2 <= 13; cpt2++) {
                deck.add(new Carte(cpt2, CouleurCarte.COEUR));
            }
        }

    }

    /**
     * Mélange les cartes
     */
    public void melanger() {
        Collections.shuffle(deck, new Random(System.currentTimeMillis()));
    }

    /**
     * Retourne le nombre de cartes restantes
     *
     * @return le nombre de cartes
     */
    public int carteRestantes() {
        return deck.size();
    }

    /**
     * Tire une carte
     *
     * @return une carte
     */
    public Carte tirerCarte() {
        return deck.remove(0);
    }

}
