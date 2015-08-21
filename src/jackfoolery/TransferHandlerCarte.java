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
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.MOVE;

/**
 * le tranferHandler pour le drag and drop de cartebeans
 *
 * @author Régis
 */
class TransferHandlerCarte extends TransferHandler {

    protected final static DataFlavor[] supportedFlavors = {new DataFlavor(TransferableCarte.class, "Carte du joueur")};
    private String joueur;

    public String getJoueur() {
        return joueur;
    }

    public int getPos() {
        return pos;
    }

    public Jeu getJeu() {
        return jeu;
    }
    private int pos;
    private Jeu jeu;

    public TransferHandlerCarte(String joueur, int pos) {
        this.joueur = joueur;
        this.pos = pos;
    }

    public TransferHandlerCarte(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        return info.isDataFlavorSupported(supportedFlavors[0]);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        Transferable data = support.getTransferable();
        TransferableCarte info;

        try {
            info = (TransferableCarte) data.getTransferData(supportedFlavors[0]);
            CarteBeanPlateau carteBean = (CarteBeanPlateau) support.getComponent();

            jeu.placerCarte(carteBean.getLig(), carteBean.getCol(), info.joueur, info.posCarte);

        } catch (UnsupportedFlavorException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return true;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        //Une fois le drop effectué nous effaçons le contenu de notre JLabel
        if (action == MOVE) {

            if (source instanceof CarteBean) {
                if (source.getParent() instanceof MainBean) {
//                        try {
//                   
//                        } catch (ExceptionCarte ex) {
//                            Logger.getLogger(CarteBean.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                }
                if (source.getParent() instanceof PlateauBean) {
                    JOptionPane.showMessageDialog(null, "Erreur:glisser à partir d'un plateau!");
                }
            }

        }
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        //On retourne un nouvel objet implémentant l'interface Transferable
        CarteBeanMain carte = (CarteBeanMain) c;
        return new TransferableCarte(carte.getJoueur(), carte.getPos());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
}
