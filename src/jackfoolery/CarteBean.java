/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Un bean de carte pour le BeanMain
 *
 * @author g36896
 */
public class CarteBean extends JComponent implements Observer, Serializable {

    Carte carte;

    /**
     * Constructeur de Bean de Carte
     */
    public CarteBean() {
        this.carte = new Carte();
        carte.addObserver(this);
        this.getDropTarget().setActive(true);
        this.setMinimumSize(new Dimension(50, 50));
        this.setEnabled(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent lab = (JComponent) e.getSource();
                TransferHandler handle = lab.getTransferHandler();
                handle.exportAsDrag(lab, e, TransferHandler.MOVE);

            }
        });
        //this.repaint();
    }

    /**
     * Constructeur de bean de carte avec drag ou drop activés
     *
     * @param drag si le drag est activé
     * @param drop si le drop est activé
     */
    public CarteBean(boolean drag, boolean drop) {
        this.carte = null;
        this.setEnabled(true);
        this.setPreferredSize(new Dimension(100, 100));
        if (drag) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JComponent lab = (JComponent) e.getSource();
                    TransferHandler handle = lab.getTransferHandler();
                    handle.exportAsDrag(lab, e, TransferHandler.MOVE);

                }
            });
        }
        if (drop) {
            this.getDropTarget().setActive(true);

        }
        //this.repaint();
    }

    /**
     * Constructeur de Cartebean avec carte de référence
     *
     * @param carte une carte
     */
    public CarteBean(Carte carte) {

        this.carte = carte;
        this.setEnabled(true);
        this.setPreferredSize(new Dimension(100, 100));
        // this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension taille = getSize();
        int largeur = taille.width;
        int longueur = taille.height;
        int c_x = largeur / 2;
        int c_y = longueur / 2;
        Font font = new Font("Courier", Font.CENTER_BASELINE, 16);

        if (carte == null || carte.isRetourne()) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, largeur, longueur);
        } else {
            String stringCarte;
            if (carte.getVal() == 1) {
                stringCarte = "A";
            } else if (carte.getVal() == 11) {
                stringCarte = "V";
            } else if (carte.getVal() == 12) {
                stringCarte = "D";
            } else if (carte.getVal() == 13) {
                stringCarte = "R";
            } else {
                stringCarte = Integer.toString(carte.getVal());
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, largeur - 1, longueur - 1);
            if (carte.getCouleurJeton() != null) {
                g.setColor(carte.getCouleurJeton());
                g.fillOval(0, 0, largeur - 1, longueur - 1);
            }
            if (carte.getCouleur() == CouleurCarte.COEUR) {
                g.setColor(Color.RED);
                stringCarte = stringCarte + (char) '\u2764';
            } else if (carte.getCouleur() == CouleurCarte.CARREAU) {
                g.setColor(Color.RED);
                stringCarte = stringCarte + (char) '\u2666';
            } else if (carte.getCouleur() == CouleurCarte.PIQUE) {
                g.setColor(Color.BLACK);
                stringCarte = stringCarte + (char) '\u2660';
            } else if (carte.getCouleur() == CouleurCarte.TREFLE) {
                g.setColor(Color.BLACK);
                stringCarte = stringCarte + (char) '\u2663';
            } else if (carte.getCouleur() == CouleurCarte.JOKER) {
                g.setColor(Color.BLACK);
                stringCarte = "Jk";
            }

            g.setFont(font);
            g.drawString(stringCarte, c_x - 16, c_y);

        }
    }

    /**
     * Retourne la carte de référence
     *
     * @return la carte de référence
     */
    public Carte getCarte() {
        return this.carte;
    }

    /**
     * Change la carte de référence
     *
     * @param carte une carte
     */
    public void setCarte(Carte carte) {
        this.carte = carte;
        if (carte != null) {
            this.carte.addObserver(this);
        }
        this.repaint();
    }

    /**
     * Change la valeur de la carte de référence
     *
     * @param val la valeur de la carte
     */
    public void setVal(int val) {
        this.carte.setVal(val);
    }

    /**
     * Change la couleur de la carte de référence
     *
     * @param couleur la couleur de la carte
     */
    public void setCouleur(CouleurCarte couleur) {
        this.carte.setCouleur(couleur);
    }

    /**
     * Retourne la carte de référence
     *
     * @param retourne si la carte est retournée
     */
    public void setRetourne(boolean retourne) {
        this.carte.setRetourne(retourne);
    }

    /**
     * Retourne la carte de référence
     */
    public void flip() {
        this.carte.setRetourne(!this.carte.isRetourne());
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Carte)) {
            this.carte.deleteObserver(this);
            carte.addObserver(this);
            this.carte = (Carte) o;
            this.repaint();
        } else {
            throw new UnsupportedOperationException("Wrong class.");
        }
    }

}
