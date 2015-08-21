package jackfoolery;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Classe plateau pour le jackfoolery
 *
 * @author Régis
 */
public class Plateau extends Observable {

    private ArrayList<ArrayList<Carte>> plateau = new ArrayList<>();
    private boolean full = false;

    /**
     * retourne le nombre de lignes du plateau
     *
     * @return le nombre de lignes
     */
    public int getNbLignes() {
        return plateau.get(0).size();
    }

    /**
     * retourne le nombre de colonnes du plateau
     *
     * @return le nombre de colonnes
     */
    public int getNbColonnes() {
        return plateau.size();
    }

    /**
     * Constructeur par défaut de plateau vide 10x10
     */
    public Plateau() {
        this.full = false;
        setPlateau(10, 10);

    }

    /**
     * Place les cartes sur le plateau pour un jeu de JackFoolery
     */
    public void placerCartesJackFoolery() {
        int val;
        int col1;
        int col2;
        int lig1;
        int lig2;
//place les coeurs
        val = 1;
        col1 = 3;
        lig1 = 5;
        col2 = 6;
        lig2 = 4;
        for (int cpt = 0; cpt < 4; cpt++) {

            for (int cpt2 = 0; cpt2 <= cpt; cpt2++) {
                plateau.get(lig1 + cpt2).set(col1 - cpt, new Carte(val, CouleurCarte.COEUR));
                plateau.get(lig2 - cpt2).set(col2 + cpt, new Carte(val, CouleurCarte.COEUR));
                val++;
            }
        };
        plateau.get(7).set(2, new Carte(12, CouleurCarte.COEUR));
        plateau.get(2).set(7, new Carte(12, CouleurCarte.COEUR));
        plateau.get(1).set(8, new Carte(13, CouleurCarte.COEUR));
        plateau.get(8).set(1, new Carte(13, CouleurCarte.COEUR));
        //place les carreaux
        val = 1;
        col1 = 4;
        lig1 = 3;
        col2 = 5;
        lig2 = 6;
        for (int cpt = 0; cpt < 4; cpt++) {
            for (int cpt2 = 0; cpt2 <= cpt; cpt2++) {
                plateau.get(lig1 - cpt).set(col1 - cpt2, new Carte(val, CouleurCarte.CARREAU));
                plateau.get(lig2 + cpt).set(col2 + cpt2, new Carte(val, CouleurCarte.CARREAU));
                val++;
            }
        }
        plateau.get(3).set(3, new Carte(12, CouleurCarte.CARREAU));
        plateau.get(6).set(6, new Carte(12, CouleurCarte.CARREAU));
        plateau.get(4).set(4, new Carte(13, CouleurCarte.CARREAU));
        plateau.get(5).set(5, new Carte(13, CouleurCarte.CARREAU));
        //place les piques
        val = 1;
        col1 = 5;
        lig1 = 3;
        col2 = 4;
        lig2 = 6;
        for (int cpt = 0; cpt < 4; cpt++) {

            for (int cpt2 = 0; cpt2 <= cpt; cpt2++) {
                plateau.get(lig1 - cpt).set(col1 + cpt2, new Carte(val, CouleurCarte.PIQUE));
                plateau.get(lig2 + cpt).set(col2 - cpt2, new Carte(val, CouleurCarte.PIQUE));
                val++;
            }
        };
        plateau.get(6).set(3, new Carte(12, CouleurCarte.PIQUE));
        plateau.get(3).set(6, new Carte(12, CouleurCarte.PIQUE));
        plateau.get(4).set(5, new Carte(13, CouleurCarte.PIQUE));
        plateau.get(5).set(4, new Carte(13, CouleurCarte.PIQUE));
        //place les trèfles
        val = 1;
        col1 = 3;
        lig1 = 4;
        col2 = 6;
        lig2 = 5;
        for (int cpt = 0; cpt < 4; cpt++) {

            for (int cpt2 = 0; cpt2 <= cpt; cpt2++) {
                plateau.get(lig1 - cpt2).set(col1 - cpt, new Carte(val, CouleurCarte.TREFLE));
                plateau.get(lig2 + cpt2).set(col2 + cpt, new Carte(val, CouleurCarte.TREFLE));
                val++;
            }
        };
        plateau.get(2).set(2, new Carte(12, CouleurCarte.TREFLE));
        plateau.get(7).set(7, new Carte(12, CouleurCarte.TREFLE));
        plateau.get(1).set(1, new Carte(13, CouleurCarte.TREFLE));
        plateau.get(8).set(8, new Carte(13, CouleurCarte.TREFLE));
        //place les jokers
        plateau.get(0).set(0, new Carte(0, CouleurCarte.JOKER));
        plateau.get(9).set(9, new Carte(0, CouleurCarte.JOKER));
        plateau.get(0).set(9, new Carte(0, CouleurCarte.JOKER));
        plateau.get(9).set(0, new Carte(0, CouleurCarte.JOKER));
        setChanged();
        notifyObservers();
    }

    /**
     * Remets le plateau à 0
     */
    public void resetPlateau() {
        plateau = new ArrayList<>();
        setPlateau(10, 10);
        setChanged();
        notifyObservers();
    }

    private void setPlateau(int lig, int col) {
        for (int cpt = 0; cpt < lig; cpt++) {
            plateau.add(new ArrayList<Carte>());
        }
        for (int cptLig = 0; cptLig < lig; cptLig++) {
            for (int cptCol = 0; cptCol < col; cptCol++) {
                plateau.get(cptLig).add(null);
            }
        }
    }

    /**
     * retoune la carte à l'emplacement ou null
     *
     * @param lg la ligne
     * @param cl la colonne
     * @return la carte à la position ou null
     */
    public Carte getCarte(int lg, int cl) {
        return plateau.get(lg).get(cl);
    }

    /**
     * place une carte à la position
     *
     * @param lg la ligne
     * @param cl la colonne
     * @param carte une carte
     */
    public void setCarte(int lg, int cl, Carte carte) {
        plateau.get(lg).set(cl, carte);
        setChanged();
        notifyObservers();
    }

    /**
     * retourne le jeton sur la carte
     *
     * @param lg la ligne
     * @param cl la colonne
     * @return null s'il n'y a pas de jeton ou une couleur
     */
    public Color getJeton(int lg, int cl) {
        return plateau.get(lg).get(cl).getCouleurJeton();
    }

    /**
     * Mets un jeuton de couleur sur la carte
     *
     * @param lg la ligne
     * @param cl la colonne
     * @param couleur la couleur ou null pour enlever
     */
    public void setJeton(int lg, int cl, Color couleur) {
        plateau.get(lg).get(cl).setCouleurJeton(couleur);
        setChanged();
        notifyObservers();
    }

    /**
     * retire la carte à la position
     *
     * @param lg la ligne
     * @param cl la colonne
     * @return la carte
     */
    public Carte removeCarte(int lg, int cl) {
        Carte carte = getCarte(lg, cl);
        plateau.get(lg).set(cl, null);
        setChanged();
        notifyObservers();
        return carte;
    }

    /**
     * retourne si tous les emplacements de carte sont remplis
     *
     * @return vrai si il y a un jeton sur chaque carte
     */
    public boolean isFull() {
        int cpt = 0;
        boolean plein = true;
        while (cpt < getNbLignes() && plein) {
            int cpt2 = 0;
            while (cpt2 < getNbColonnes() && plein) {
                plein = (plateau.get(cpt).get(cpt2).getCouleurJeton() != null);
            }
        }
        return plein;
    }

    /**
     * retourne le nombre de cartes dans le plateau
     * @return
     */
    public int getNbCarte() {
        int cpt = 0;
        for (ArrayList<Carte> ligne : plateau) {
            for (Carte carte : ligne) {
                if (carte != null) {
                    cpt++;
                }
            }
        }
        return cpt;
    }

//    public int getNbCarte(Color couleur) {
//        int cpt = 0;
//        for (ArrayList<Carte> ligne : plateau) {
//            for (Carte carte : ligne) {
//                if (carte != null && carte.getCouleur() == couleur) {
//                    cpt++;
//                }
//            }
//        }
//        return cpt;
//    }
    /**
     * Retourne si l'emplacement de plateau est vide
     * @param ligne la ligne
     * @param colonne la colonne
     * @return
     */
    public boolean isEmpty(int ligne, int colonne) {
        return plateau.get(ligne).get(colonne).getCouleurJeton() == null;
    }

    @Override
    public String toString() {
        String plaT = "";
        for (ArrayList<Carte> ligne : plateau) {
            for (Carte carte : ligne) {
                plaT += carte;
            }
            plaT += "\n";
        }
        return plaT;

    }
}
