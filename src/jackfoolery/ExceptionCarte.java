/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

/**
 * Classe d'erreur pour un jeu de cartes
 *
 * @author g36720
 */
public class ExceptionCarte extends Exception {

    /**
     * Constructeur de classe
     * @param message Le message d'erreur
     */
    public ExceptionCarte(String message) {
        super(message);
    }

}
