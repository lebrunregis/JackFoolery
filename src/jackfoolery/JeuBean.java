package jackfoolery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Le bean affichant le jeu complet
 *
 * @author Régis
 */
public class JeuBean extends JFrame implements Observer {

    private final JPanel container = new JPanel();
    private final JMenuBar barreMenu = new JMenuBar();
    private final JMenu menu = new JMenu("Menu");
    private final JMenu aide = new JMenu("?");
    private final JMenuItem nouveauJeu = new JMenuItem("Nouveau jeu");
    private final JMenuItem quitter = new JMenuItem("Quitter");
    private Jeu jeu;
    private PlateauBean plateau;
    private JoueurBean joueur1Bean;
    private JoueurBean joueur2Bean;

    /**
     * Constructeur de classe,initialise une partie complète avec comme joueur
     * joueur2 et joueur2
     */
    public JeuBean() {
        this.jeu = new Jeu();
        this.plateau = new PlateauBean(jeu);
        this.plateau.setPreferredSize(new Dimension(400, 400));
        this.joueur1Bean = new JoueurBean(jeu.getJoueur(0), jeu);
        this.joueur1Bean.setPreferredSize(new Dimension(75, 200));
        this.joueur2Bean = new JoueurBean(jeu.getJoueur(1), jeu);
        this.joueur2Bean.setPreferredSize(new Dimension(75, 200));
        jeu.getPlateau().addObserver(plateau);
        jeu.getJoueur(0).addObserver(joueur1Bean);
        jeu.getJoueur(1).addObserver(joueur2Bean);
        this.setTitle("Jack Foolery");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.nouveauJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JDialogJoueurs dial = new JDialogJoueurs(null, true);
                dial.setLocationRelativeTo(null);
                dial.setVisible(true);
                container.remove(plateau);
                container.remove(joueur1Bean);
                container.remove(joueur2Bean);
                jeu = new Jeu(dial.getJoueur1(), dial.getJoueur2());
                plateau = new PlateauBean(jeu);
                plateau.setPreferredSize(new Dimension(400, 400));
                joueur1Bean = new JoueurBean(jeu.getJoueur(0), jeu);
                joueur1Bean.setPreferredSize(new Dimension(75, 200));
                joueur2Bean = new JoueurBean(jeu.getJoueur(1), jeu);
                joueur2Bean.setPreferredSize(new Dimension(75, 200));
                jeu.getPlateau().addObserver(plateau);
                jeu.getJoueur(0).addObserver(joueur1Bean);
                jeu.getJoueur(1).addObserver(joueur2Bean);
                        container.add(plateau, BorderLayout.CENTER);
        container.add(joueur1Bean, BorderLayout.WEST);
        container.add(joueur2Bean, BorderLayout.EAST);
        pack();
        setVisible(true);
            }
        });
        this.menu.add(this.nouveauJeu);
        this.menu.addSeparator();
        this.menu.add(this.quitter);
        this.barreMenu.add(this.menu);
        this.barreMenu.add(this.aide);
        this.setJMenuBar(this.barreMenu);
        container.setLayout(new BorderLayout());
        container.add(plateau, BorderLayout.CENTER);
        container.add(joueur1Bean, BorderLayout.WEST);
        container.add(joueur2Bean, BorderLayout.EAST);
        this.setContentPane(container);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     *
     * @param joueur1
     * @param joueur2
     */
    public JeuBean(String joueur1, String joueur2) {
        this.jeu = new Jeu(joueur1, joueur2);
        this.plateau = new PlateauBean(jeu);
        this.plateau.setPreferredSize(new Dimension(400, 400));
        this.joueur1Bean = new JoueurBean(jeu.getJoueur(0), jeu);
        this.joueur1Bean.setPreferredSize(new Dimension(75, 200));
        this.joueur2Bean = new JoueurBean(jeu.getJoueur(1), jeu);
        this.joueur2Bean.setPreferredSize(new Dimension(75, 200));
        jeu.getPlateau().addObserver(plateau);
        jeu.getJoueur(0).addObserver(joueur1Bean);
        jeu.getJoueur(1).addObserver(joueur2Bean);
        this.setTitle("Jack Foolery");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.nouveauJeu.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
                JDialogJoueurs dial = new JDialogJoueurs(null, true);
                dial.setLocationRelativeTo(null);
                dial.setVisible(true);
                container.remove(plateau);
                container.remove(joueur1Bean);
                container.remove(joueur2Bean);
                jeu = new Jeu(dial.getJoueur1(), dial.getJoueur2());
                plateau = new PlateauBean(jeu);
                plateau.setPreferredSize(new Dimension(400, 400));
                joueur1Bean = new JoueurBean(jeu.getJoueur(0), jeu);
                joueur1Bean.setPreferredSize(new Dimension(75, 200));
                joueur2Bean = new JoueurBean(jeu.getJoueur(1), jeu);
                joueur2Bean.setPreferredSize(new Dimension(75, 200));
                jeu.getPlateau().addObserver(plateau);
                jeu.getJoueur(0).addObserver(joueur1Bean);
                jeu.getJoueur(1).addObserver(joueur2Bean);
                        container.add(plateau, BorderLayout.CENTER);
        container.add(joueur1Bean, BorderLayout.WEST);
        container.add(joueur2Bean, BorderLayout.EAST);
        pack();
        setVisible(true);
            }
        });
        this.menu.add(this.nouveauJeu);
        this.menu.addSeparator();
        this.menu.add(this.quitter);
        this.barreMenu.add(this.menu);
        this.barreMenu.add(this.aide);
        this.setJMenuBar(this.barreMenu);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(plateau, BorderLayout.CENTER);
        container.add(joueur1Bean, BorderLayout.WEST);
        container.add(joueur2Bean, BorderLayout.EAST);
        this.setContentPane(container);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Jeu)) {
            this.jeu = (Jeu) o;
            this.plateau = new PlateauBean(jeu);
            //this.plateau=new PlateauBean();
            this.plateau.setPreferredSize(new Dimension(400, 400));
            this.joueur1Bean = new JoueurBean(jeu.getJoueur(0), jeu);
            this.joueur1Bean.setPreferredSize(new Dimension(75, 200));
            this.joueur2Bean = new JoueurBean(jeu.getJoueur(1), jeu);
            this.joueur2Bean.setPreferredSize(new Dimension(75, 200));
            jeu.getPlateau().addObserver(plateau);
            jeu.getJoueur(0).addObserver(joueur1Bean);
            jeu.getJoueur(1).addObserver(joueur2Bean);
            this.repaint();
        } else {
            throw new UnsupportedOperationException("Wrong class.");
        }
    }

    /**
     * Retourne le jeu de référence
     *
     * @return l'instance de jeu
     */
    public Jeu getJeu() {
        return jeu;
    }
}
