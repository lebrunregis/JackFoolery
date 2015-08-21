package jackfoolery;

import java.awt.Color;
import java.util.Objects;
import java.util.Observable;

/**
 * La classe carte
 *
 * @author Régis
 */
public class Carte extends Observable {

    private int val;
    private Color couleurJeton = null;

    /**
     * Place un jeton sur la carte
     *
     * @param couleurJeton la couleur du jeton
     */
    public void setCouleurJeton(Color couleurJeton) {
        this.couleurJeton = couleurJeton;
        setChanged();
        notifyObservers();
    }

    /**
     * Retourne la couleur du jeton ou null s'il n'y a pas de jeton sur la carte
     *
     * @return la couleur du jeton ou null
     */
    public Color getCouleurJeton() {
        return couleurJeton;
    }
    private CouleurCarte couleur;
    private boolean retourne;

    /**
     * Constructeur de carte Par défaut un joker
     */
    public Carte() {
        this.val = 0;
        this.couleur = CouleurCarte.JOKER;
        this.retourne = false;
        this.couleurJeton = null;
    }

    /**
     * Constructeur de Carte
     *
     * @param val la valeur de la carte
     * @param couleur La couleur de la carte
     */
    public Carte(int val, CouleurCarte couleur) {
        this.val = val;
        this.couleur = couleur;
        this.retourne = false;
        this.couleurJeton = null;
    }

    /**
     * Constructeur de carte
     *
     * @param val la valeur de la carte
     * @param couleur la couleur de la carte
     * @param couleurJeton la couleur du jeton
     */
    public Carte(int val, CouleurCarte couleur, Color couleurJeton) {
        this.val = val;
        this.couleur = couleur;
        this.retourne = false;
        this.couleurJeton = couleurJeton;
    }

    /**
     * Retourne la valeur du jeton
     *
     * @return la valeur du jeton
     */
    public int getVal() {
        return val;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.val;
        hash = 29 * hash + Objects.hashCode(this.couleur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carte other = (Carte) obj;
        if (this.val != other.val) {
            return false;
        }
        if (!Objects.equals(this.couleur, other.couleur)) {
            return false;
        }
        return true;
    }

    /**
     * Change la valeur de la carte
     *
     * @param val la velur de la carte
     */
    public void setVal(int val) {
        this.val = val;
        setChanged();
        notifyObservers();

    }

    /**
     * Change la couleur de la carte
     *
     * @param couleur la couleur de la carte
     */
    public void setCouleur(CouleurCarte couleur) {
        this.couleur = couleur;
        setChanged();
        notifyObservers();
    }

    /**
     * Range le retournement de la carte
     *
     * @param retourne Si la carte est retournée ou non
     */
    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
        setChanged();
        notifyObservers();
    }

    /**
     * Retourne la couleur de la carte
     *
     * @return la couleur de la carte
     */
    public CouleurCarte getCouleur() {
        return couleur;
    }

    /**
     * Retourne si la carte est de la même couleur
     *
     * @param couleur une couleur de carte
     * @return vrai si la carte est de la même couleur
     */
    public boolean memeCouleur(CouleurCarte couleur) {
        return couleur.equals(this.couleur);
    }

    /**
     * Retourne si deux cartes sont de la même couleur
     *
     * @param carte une carte
     * @return vrai si la carte est de la même couleur
     */
    public boolean memeCouleur(Carte carte) {
        return couleur.equals(carte.getCouleur());
    }

    /**
     * Retourne si la carte est retournée
     *
     * @return true si la carte est face cachée
     */
    public boolean isRetourne() {
        return retourne;
    }

    @Override
    public String toString() {
        String carte = Integer.toString(val);
        carte += " ";
        return carte;

    }
}
