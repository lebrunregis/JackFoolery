package jackfoolery;

/**
 * La fenêtre principale
 *
 * @author Régis
 */
public class JackFoolery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JDialogLogin dial = new JDialogLogin(null, true);
        dial.setLocationRelativeTo(null);
        dial.setVisible(true);

        JeuBean obs = new JeuBean();

        obs.setVisible(true);
    }

}
