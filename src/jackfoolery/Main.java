package jackfoolery;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

/**
 * Une main de cartes
 *
 * @author Régis
 */
public class Main extends Observable {

    private LinkedList<Carte> main;

    /**
     *
     */
    public Main() {
        main = new LinkedList<>();

    }

    /**
     * Contructeur de main recevant le nombre de cartes à retirer et le deck
     * d'où tirer les cartes en paramètre
     *
     * @param nbcartes le nombre de cartes à retirer
     * @param deck un deck de cartes avec assez de cartes
     */
    public Main(int nbcartes, DeckJackFoolery deck) {
        main = new LinkedList<>();

        for (int i = 0; i < nbcartes; i++) {
            Carte carte = deck.tirerCarte();
            main.add(new Carte(carte.getVal(), carte.getCouleur()));
        }
    }

    /**
     * Contructeur de main recevant une liste de cartes en paramètres
     *
     * @param main une liste de cartes
     */
    public Main(LinkedList<Carte> main) {
        this.main = main;
    }

    /**
     * retourne la liste des cartes en amin
     *
     * @return une liste de cartes
     */
    public LinkedList<Carte> getMain() {
        return main;
    }

    /**
     * retourne le nombre de cartes en main
     *
     * @return
     */
    public int getNbCartes() {
        return main.size();
    }

    /**
     * ajoute une carte à la main
     *
     * @param carte
     */
    public void addCarte(Carte carte) {
        main.add(new Carte(carte.getVal(), carte.getCouleur()));
        setChanged();
        notifyObservers();
    }

    /**
     * retourne la carte à la position dans la main
     *
     * @param pos la position dans dans la main de la carte
     * @return la carte demandée
     */
    public Carte getCarte(int pos) {
        return main.get(pos);
    }

    /**
     * Retire et retourne la carte à la position dans la main
     *
     * @param pos la position dans la main
     * @return la carte
     */
    public Carte popCarte(int pos) {
        Carte carte = main.remove(pos);
        setChanged();
        notifyObservers();
        return carte;
    }

    /**
     * place la carte à la position dans la main
     *
     * @param pos
     * @param carte
     */
    public void setCarte(int pos, Carte carte) {
        main.set(pos, carte);
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param carte
     * @return
     * @throws ExceptionCarte
     */
    public Carte popCarte(Carte carte) throws ExceptionCarte {
        main.remove(carte);

        setChanged();
        notifyObservers();
        return carte;
    }

    /**
     *
     * @return
     */
    public boolean isVide() {
        return main.isEmpty();
    }

    @Override
    public String toString() {
        String mainT = "";
        for (int i = 0; i < main.size(); i++) {
            mainT += main.get(i);
        }
        return mainT;
    }
}
