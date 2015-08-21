/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * CarteBean pour le plateau
 *
 * @author Régis
 */
public class CarteBeanPlateau extends CarteBean implements Transferable {

    private int lig;
    private int col;
    private Jeu jeu;

    /**
     * Retourne la position dans le tableau du Bean
     *
     * @return la ligne
     */
    public int getLig() {
        return lig;
    }

    /**
     * Retourne la position dans le tableau du Bean
     *
     * @return la colonne
     */
    public int getCol() {
        return col;
    }

    /**
     * Constructeur de classe
     *
     * @param carte la carte de référence
     * @param lig la position de la carte sur le plateau
     * @param col la position de la carte dans la colonna
     * @param jeu le controleur de jeu
     */
    public CarteBeanPlateau(Carte carte, int lig, int col, Jeu jeu) {
        super(carte);
        this.lig = lig;
        this.col = col;
        this.jeu = jeu;

    }

    /**
     * Active le drop pour l'élément
     */
    public void enableDropTarget() {
        this.setEnabled(true);
        this.setTransferHandler(new TransferHandlerCarte(jeu));
        super.getDropTarget().setActive(true);

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
            return this;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            return this.toString();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

}
