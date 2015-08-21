package jackfoolery;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 * Controleur de jeu de JackFoolery
 *
 * @author Régis
 */
public class Jeu extends Observable {

    private final ArrayList<Joueur> joueurs = new ArrayList<>();
    private final Plateau plateau;
    private int nbTours = 0;
    private int joueurCourant = 0;
    private boolean partieTerminee = false;
    private final DeckJackFoolery deck = new DeckJackFoolery();

    /**
     * Constructeur de classe
     */
    public Jeu() {
        plateau = new Plateau();
        plateau.placerCartesJackFoolery();
        deck.melanger();
        joueurs.add(new Joueur("Joueur 1", deck, Color.BLUE, 5));
        joueurs.add(new Joueur("Joueur 2", deck, Color.GREEN, 5));
        joueurCourant = (int) (Math.random() * 2);
        JOptionPane.showMessageDialog(null, "C'est à " + joueurs.get(joueurCourant).getName() + " de commencer à jouer.");
    }

    /**
     * Constructeur recevant les joueurs participants en paramètres
     *
     * @param joueur1 Le nom de joueur1
     * @param joueur2 Le nom de joueur12
     */
    public Jeu(String joueur1, String joueur2) {
        plateau = new Plateau();
        plateau.placerCartesJackFoolery();
        deck.melanger();
        joueurs.add(new Joueur(joueur1, deck, Color.BLUE, 5));
        joueurs.add(new Joueur(joueur2, deck, Color.GREEN, 5));
        joueurCourant = (int) (Math.random() * 2);
        JOptionPane.showMessageDialog(null, "C'est à " + joueurs.get(joueurCourant).getName() + " de commencer à jouer.");
    }

    /**
     * Retourne le joueur courant
     *
     * @return le joueur courant
     */
    public Joueur getJoueurCourant() {
        return joueurs.get(joueurCourant);
    }

    /**
     * Retourne le joueur n° demande
     *
     * @param num le numéro du joueur
     * @return
     */
    public Joueur getJoueur(int num) {
        return joueurs.get(num);
    }

    /**
     * Retourne le plateau de jeu
     *
     * @return le plateau de jeu
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Place la carte à la position donnée dans la main du joueur à la position
     * donnée Sinon,affiche un message
     *
     * @param ligne une ligne du plateau
     * @param colonne une colonne du plateau
     * @param nomJoueur le nom du joueur
     * @param posCarteMain la position dans la main du joueur
     */
    public void placerCarte(int ligne, int colonne, String nomJoueur, int posCarteMain) {
        //  System.out.println(Integer.toString(ligne) + " " + Integer.toString(colonne) + " " + nomJoueur + " " + Integer.toString(posCarteMain));
        // System.out.println("Joueur courant:"+ Integer.toString(joueurCourant));
        try {
            if (partieTerminee) {
                throw new ExceptionCarte("La partie est terminée");
            }
            if (joueurs.get(joueurCourant).getName().equals(nomJoueur)) {
                if (joueurs.get(joueurCourant).getMain().getCarte(posCarteMain).getVal() == 11) {
                    if (joueurs.get(joueurCourant).getMain().getCarte(posCarteMain).getCouleur() == CouleurCarte.COEUR
                            || joueurs.get(joueurCourant).getMain().getCarte(posCarteMain).getCouleur() == CouleurCarte.CARREAU) {
                        if (plateau.getCarte(ligne, colonne).getCouleurJeton() == null) {
                            plateau.setJeton(ligne, colonne, joueurs.get(joueurCourant).getCouleur());
                            joueurs.get(joueurCourant).getMain().popCarte(posCarteMain);
                            if (verifierVainqueur(ligne, colonne)) {
                                partieTerminee = true;
                                joueurs.get(joueurCourant).setVainqueur(true);
                                JOptionPane.showMessageDialog(null, joueurs.get(joueurCourant).getName() + " a gagné!");
                            }
                        } else {
                            throw new ExceptionCarte("Il y a déjà un jeton sur cette carte!");
                        }
                    } else {
                        if (plateau.getCarte(ligne, colonne).getCouleurJeton() != null) {
                            plateau.setJeton(ligne, colonne, null);
                            joueurs.get(joueurCourant).getMain().popCarte(posCarteMain);
                        } else {
                            throw new ExceptionCarte("Il n'y a pas de jeton sur cette carte!");
                        }
                    }
                    finTour();

                } else if (plateau.getCarte(ligne, colonne).equals(joueurs.get(joueurCourant).getMain().getCarte(posCarteMain))) {
                    //       System.out.println("Main: " + joueurs.get(joueurCourant).getMain());
                    if (plateau.getCarte(ligne, colonne).getCouleurJeton() == null) {
                        plateau.setJeton(ligne, colonne, joueurs.get(joueurCourant).getCouleur());
                        joueurs.get(joueurCourant).getMain().popCarte(posCarteMain);
                        if (verifierVainqueur(ligne, colonne)) {
                            partieTerminee = true;
                            joueurs.get(joueurCourant).setVainqueur(true);
                            JOptionPane.showMessageDialog(null, joueurs.get(joueurCourant).getName() + " a gagné!");
                        } else {
                            finTour();
                        }
                    } else {
                        throw new ExceptionCarte("Il y a déjà un jeton sur cette carte!");
                    }

                } else {
                    throw new ExceptionCarte("La carte ne correspond pas!");
                }

            } else {
                throw new ExceptionCarte("\nPas si vite " + nomJoueur + "\nC'est au tour de " + joueurs.get(joueurCourant).getName() + " de jouer!");
            }
        } catch (ExceptionCarte e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Retourne le nombre de tours que la partie a duré
     *
     * @return le nombre de tours
     */
    public int getNbTours() {
        return nbTours;
    }

    /**
     * Retourne si la partie est terminée
     *
     * @return vrai si terminée
     */
    public boolean estTerminée() {
        return this.partieTerminee;
    }

    private boolean verifierVainqueur(int lig, int col) {
        int cptHor = 1;
        int cptVert = 1;
        int cptDiagBas = 1;
        int cptDiagHaute = 1;
        Color couleur = plateau.getJeton(lig, col);

        int cptLig;
        int cptCol;
        //vérifier ligne
        cptLig = lig;
        cptCol = col - 1;
        while (cptCol >= 0 && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptCol--;
            cptHor++;
        }
        cptCol = col + 1;
        while (cptCol < plateau.getNbColonnes() && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptCol++;
            cptHor++;
        }
        //vérifier colonne
        cptLig = lig - 1;
        cptCol = col;
        while (cptLig >= 0 && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig--;
            cptVert++;
        }
        cptLig = lig + 1;
        while (cptLig < plateau.getNbLignes() && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig++;
            cptVert++;
        }

        //vérifier diagonale basse
        cptLig = lig - 1;
        cptCol = col - 1;
        while (cptLig >= 0 && cptCol >= 0 && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig--;
            cptCol--;
            cptDiagBas++;
        }
        cptLig = lig + 1;
        cptCol = col + 1;
        while (cptLig < plateau.getNbLignes() && cptCol < plateau.getNbColonnes() && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig++;
            cptCol++;
            cptDiagBas++;
        }
        //Vérifier diagonale haute
        cptLig = lig - 1;
        cptCol = col + 1;
        while (cptLig >= 0 && cptCol < plateau.getNbColonnes() && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig--;
            cptCol++;
            cptDiagHaute++;
        }
        cptLig = lig + 1;
        cptCol = col - 1;
        while (cptLig < plateau.getNbLignes() && cptCol >= 0 && couleur.equals(plateau.getJeton(cptLig, cptCol))) {
            cptLig++;
            cptCol--;
            cptDiagHaute++;
        }
        System.out.println("Horizontal: " + Integer.toString(cptHor) + "\nVertical:" + Integer.toString(cptVert)
                + "\nHaute: " + Integer.toString(cptDiagHaute) + "\nBasse:" + Integer.toString(cptDiagBas));
        return cptVert >= 5 || cptHor >= 5 || cptDiagBas >= 5 || cptDiagHaute >= 5;
    }

    private void finTour() {

//joueur courant tire une carte
        if (deck.carteRestantes() > 0) {
            joueurs.get(joueurCourant).getMain().addCarte(deck.tirerCarte());
        }
        if (!plateau.isFull()) {
            nbTours++;

            if (joueurCourant == joueurs.size() - 1) {
                joueurCourant = 0;
            } else {
                joueurCourant++;
            }
        } else {
            partieTerminee = true;
        }
        setChanged();
        notifyObservers();
    }
}
