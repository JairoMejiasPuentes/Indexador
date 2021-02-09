/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadorv2;

import java.io.File;

/**
 *
 * @author Fran
 */
public class IndexadorV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Escaner e = new Escaner();
        e.indexador(new File("C:\\Users\\Fran\\Downloads"));

    }
    
}
