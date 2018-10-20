/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.certificado;

import com.itextpdf.text.DocumentException;
import gerador.certificado.gui.JanelaPrincipal;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Wagner
 */
public class GeradorCertificado {
    
    public static JanelaPrincipal gerador;

    public static void main(String[] args) throws FileNotFoundException, IOException, DocumentException {
        gerador = new JanelaPrincipal();
        gerador.setVisible(true);
    }
}
