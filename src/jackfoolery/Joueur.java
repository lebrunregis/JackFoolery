package jackfoolery;

import java.awt.Color;
import java.util.Observable;

/**
 * Un joueur avec un nom,une couleur et une main de cartes
 * @author Régis
 */
public class Joueur extends Observable {

    private final String name;
    private final Color couleur;
    private Main main;
    private boolean Vainqueur;

    /**
     * Constructeur de classe avec un nom par défaut
     */
    public Joueur() {
        this.name = "Joueur 1";
        this.couleur = Color.blue;
        this.main = new Main();
        this.Vainqueur = false;
    }

    /**
     * Constructeur de classe avec un nom,une couleur et distribue un nombre de
     * cartes à tirer d'un deck
     *
     * @param name le nom du joueur
     * @param deck un deck avec assez de cartes
     * @param couleur la couleur du joueur
     * @param nbCarte le nombre de cartes à tirer du deck
     */
    public Joueur(String name, DeckJackFoolery deck, Color couleur, int nbCarte) {
        this.name = name;
        this.couleur = couleur;
        this.main = new Main(nbCarte, deck);
        this.Vainqueur = false;
    }

    /**
     * retourne la nom du joueur
     *
     * @return le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * retourne la couleur du joueur
     *
     * @return une couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * retourne la main de cartes du joueur
     *
     * @return
     */
    public Main getMain() {
        return main;
    }

    /**
     * retourne si le joueur a gagné
     *
     * @return vrai si vainqueur
     */
    public boolean isVainqueur() {
        return Vainqueur;
    }

    /**
     * Mets le joueur comme vaiqueur ou non de la partie
     *
     * @param Vainqueur vrai si gagnant
     */
    public void setVainqueur(boolean Vainqueur) {
        this.Vainqueur = Vainqueur;
    }

    /**
     * Change la main du joueur
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }
}
