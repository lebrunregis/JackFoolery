package jackfoolery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * un bean de plateau
 * @author Régis
 */
public class PlateauBean extends JPanel implements Observer, Serializable {

    private Plateau plateau;
    private final GridLayout layout;
    private ArrayList<ArrayList<CarteBean>> beanCartes = new ArrayList<>();
    private Jeu jeu = null;

    /**
     * Contructeur par défaut de plateau 10x10
     */
    public PlateauBean() {

        this.setEnabled(true);

        this.plateau = new Plateau();
        plateau.placerCartesJackFoolery();
        this.layout = new GridLayout(10, 10, 5, 5);

        this.setLayout(layout);
        for (int cpt = 0; cpt < 10; cpt++) {
            beanCartes.add(new ArrayList<CarteBean>());
        }
        for (int cptLig = 0; cptLig < 10; cptLig++) {
            for (int cptCol = 0; cptCol < 10; cptCol++) {
                CarteBeanPlateau temp = new CarteBeanPlateau(new Carte(), cptLig, cptCol, jeu);

                beanCartes.get(cptLig).add(temp);
                temp.enableDropTarget();
            }
        }
        for (int largeur = 0; largeur < 10; largeur++) {
            for (int longueur = 0; longueur < 10; longueur++) {
                this.add(beanCartes.get(largeur).get(longueur));
            }
        }
        this.reread();
    }

    /**
     * Constructeur de plateau avec le jeu de référence
     * @param jeu
     */
    public PlateauBean(Jeu jeu) {
        //this.setMinimumSize(new Dimension(50, 50));
        this.setEnabled(true);
        this.plateau = jeu.getPlateau();
        //System.out.println(this.plateau);
        //new layout
        this.layout = new GridLayout(plateau.getNbLignes(), plateau.getNbColonnes(), 2, 2);
        this.setLayout(layout);

        for (int cpt = 0; cpt < plateau.getNbLignes(); cpt++) {
            beanCartes.add(new ArrayList<CarteBean>());
        }

        //Ajouts dans observateurs dans le tableau interne et dans l'ui
        for (int cptLig = 0; cptLig < plateau.getNbLignes(); cptLig++) {
            for (int cptCol = 0; cptCol < plateau.getNbColonnes(); cptCol++) {
                CarteBeanPlateau temp = new CarteBeanPlateau(plateau.getCarte(cptLig, cptCol), cptLig, cptCol, jeu);
                beanCartes.get(cptLig).add(temp);
                temp.enableDropTarget();
                this.add(temp);
            }
        }
    }

    /**
     * Retourne le plateau
     * @return le plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     *
     */
    public void reread() {
        for (int cptLig = 0; cptLig < plateau.getNbLignes(); cptLig++) {
            for (int cptCol = 0; cptCol < plateau.getNbColonnes(); cptCol++) {
                beanCartes.get(cptLig).get(cptCol).setCarte(plateau.getCarte(cptLig, cptCol));
            }
        }
        System.out.println(this.plateau);
        //this.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Plateau)) {
            this.plateau = (Plateau) o;
            this.repaint();
        } else {
            throw new UnsupportedOperationException("Wrong class.");
        }
    }

    /**
     * Place une carte dont le nom du joueur est fourni,
     * la position sur le tableau est fournie et 
     * la position de la carte à placer dans la main du joueur est fournie
     * @param ligne le no de ligne du plateau
     * @param colonne le no de colonne du plateau
     * @param nomJoueur le nom du joueur
     * @param posCarte la position de la carte à placer dans la main du joueur
     * @throws ExceptionCarte
     */
    public void placerCarte(int ligne, int colonne, String nomJoueur, int posCarte) throws ExceptionCarte {
        jeu.placerCarte(ligne, colonne, nomJoueur, posCarte);

    }
}
