package jackfoolery;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Le bean représentant une main de cartes
 * @author Régis
 */
public class MainBean extends JPanel implements Observer, Serializable {

    private Main main;
    private Joueur joueur;
    private BoxLayout layout;
    private LinkedList<Carte> cartes = new LinkedList<>();
    private LinkedList<CarteBeanMain> carteBeans = new LinkedList<>();

    /**
     * Le Bean représentant la main d'un joueur
     */
    public MainBean() {
        this.setEnabled(true);
        cartes = new LinkedList<>();
        cartes.add(new Carte());
        this.main = new Main(cartes);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    /**
     * retourne la main de référence
     *
     * @return
     */
    public Main getMain() {
        return main;
    }

    /**
     * change la main de référence
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Constructeur de classe recevant la main de référence,le joueur possédant
     * la main et le controleur de jeu
     *
     * @param main la main du joueur
     * @param joueur le joueur
     * @param jeu le controleur de jeu
     */
    public MainBean(Main main, Joueur joueur, Jeu jeu) {
        this.setEnabled(true);
        this.main = main;
        this.joueur = joueur;
        //new layout
        this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        for (int noCarte = 0; noCarte < main.getNbCartes(); noCarte++) {
            CarteBeanMain carte = new CarteBeanMain(main.getCarte(noCarte), joueur, noCarte);
            carteBeans.add(carte);
            carte.enableDragging();
            this.add(carte);

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Main)) {
            this.main = (Main) o;
            //new layout
            this.removeAll();
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            for (int noCarte = 0; noCarte < main.getNbCartes(); noCarte++) {
                CarteBeanMain carte = new CarteBeanMain(main.getCarte(noCarte), joueur, noCarte);
                carte.enableDragging();
                this.add(carte);
            }
            this.validate();
            this.repaint();
        } else {
            throw new UnsupportedOperationException("Wrong class.");
        }
    }
}
