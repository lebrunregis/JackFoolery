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
 * Classe tranfésant les paramètres requis pour placer une carte au tranferhandler
 * @author Régis
 */
public class TransferableCarte implements Transferable {

    /**
     * Supported Flavor TranferableCarte
     */
    protected final static DataFlavor[] supportedFlavors = {new DataFlavor(TransferableCarte.class, "Carte du joueur")};

    /**
     * Le nom du joueur
     */
    public String joueur;

    /**
     * la position de la carte dans la main du joueur
     */
    public int posCarte;

    /**
     * Constructeur de classe
     * @param joueur
     * @param posCarte
     */
    public TransferableCarte(Joueur joueur, int posCarte) {
        this.joueur = joueur.getName();
        this.posCarte = posCarte;
    }

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
