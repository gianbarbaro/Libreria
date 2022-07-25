/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreriajpa;

/**
 *
 * @author Usuario
 */
public class LibreriaJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Menu menu = new Menu();
            menu.menu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
