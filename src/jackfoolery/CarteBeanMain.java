/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.TransferHandler;

/**
 * Bean de Carte pour le BeanMain
 *
 * @author Régis
 */
public class CarteBeanMain extends CarteBean implements Transferable {

    private final Joueur joueur;

    /**
     * retourne le joueur propriétaire de la main
     *
     * @return le joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * retourne la position de la carte de référence dans la main du joueur
     *
     * @return la position de la carte
     */
    public int getPos() {
        return pos;
    }
    private final int pos;

    /**
     * Constructeur de classe
     *
     * @param carte carte de référence
     * @param joueur joueur propriétaire de la main
     * @param pos position de la carte dans la main
     */
    public CarteBeanMain(Carte carte, Joueur joueur, int pos) {
        super(carte);
        this.joueur = joueur;
        this.pos = pos;

    }

    /**
     * Active le drag sur l'élément
     */
    public void enableDragging() {
        super.setEnabled(true);
        this.setTransferHandler(new TransferHandlerCarte(this.joueur.getName(), this.pos));
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                CarteBeanMain lab = (CarteBeanMain) e.getSource();
                TransferHandler handle = lab.getTransferHandler();
                handle.exportAsDrag(lab, e, TransferHandler.MOVE);

            }
        });
        super.getDropTarget().setActive(false);
    }

    /**
     * Support DataFlavor TransferableCarte
     */
    protected final static DataFlavor[] supportedFlavors = {new DataFlavor(TransferableCarte.class, "Carte du joueur")};

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(supportedFlavors[0]) || flavor.equals(DataFlavor.stringFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(supportedFlavors[0])) {
            return new TransferableCarte(this.joueur, this.getPos());
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            return this.toString();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
