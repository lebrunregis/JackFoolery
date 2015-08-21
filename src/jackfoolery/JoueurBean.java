/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.X_AXIS;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe Bean pour le Joueur
 *
 * @author Régis
 */
public class JoueurBean extends JPanel implements Observer, Serializable {

    private BoxLayout layout;
    private Joueur joueur = null;
    private Jeu jeu = null;

    private MainBean mainBean;
    private JLabel nom = new JLabel();

    /**
     * Constructeur de classe par défaut appelant le contructeur par défaut de
     * joueur
     */
    public JoueurBean() {
        joueur = new Joueur();
        this.setEnabled(true);
        this.layout = new BoxLayout(this, X_AXIS);
        this.nom.setText(joueur.getName());
        nom.setForeground(joueur.getCouleur());
        this.joueur = new Joueur();
        mainBean = new MainBean(joueur.getMain(), joueur, jeu);
        joueur.getMain().addObserver(mainBean);
        this.setLayout(layout);
        this.add(nom);
        this.add(mainBean);
    }

    /**
     * Constructeur avec le joueur de référence et le contrôleur de jeu en
     * paramètres
     *
     * @param joueur le joueur de référence
     * @param jeu le contrôleur de jeu
     */
    public JoueurBean(Joueur joueur, Jeu jeu) {
        this.jeu = jeu;
        this.setEnabled(true);
        this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.nom = new JLabel(joueur.getName());
        nom.setForeground(joueur.getCouleur());
        this.joueur = joueur;
        mainBean = new MainBean(joueur.getMain(), joueur, jeu);
        this.setLayout(layout);
        this.add(nom);
        this.add(mainBean);
        joueur.getMain().addObserver(mainBean);

    }

    /**
     * retourne le joueur de référence
     *
     * @return
     */
    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Joueur)) {
            this.joueur = (Joueur) o;
            //new layout
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setLayout(layout);
            this.add(nom);
            this.add(mainBean);
            this.nom.setText(joueur.getName());
            nom.setForeground(joueur.getCouleur());
            this.repaint();
        } else {
            throw new UnsupportedOperationException("Wrong class.");
        }
    }

}
