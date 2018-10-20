/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerador.certificado.gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Wagner
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
    
    private JLabel titulo;
    private JButton btnAbrirTemplate;
    private JButton btnAbrirListaNomes;
    private JButton btnDestino;
    private JButton btnStart;
    private JPanel painelOpcoes;
    private JPanel painelX;
    private JTextField posicaoX;
    private JPanel painelY;
    private JTextField posicaoY;
    private JPanel painelTamanho;
    private JTextField tamanho;
    private JButton btnCor;
    private JFileChooser caminhoTemplate;
    private JFileChooser caminhoListaNomes;
    private JFileChooser caminhoDestino;
    private String template;
    private String listaNomes;
    private String destino;
    private Color color;
    
    public JanelaPrincipal () {
        initGUI();
    }
    
    private void initGUI () {
        setTitle ("Gerador de Certificados v0.1 - Wagner Loch");
        setSize (500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,0));
        setResizable (false);
        color = Color.BLACK;
        
        titulo = new JLabel ("  Gerador de Certificados");
        titulo.setFont(new Font("Dialog", Font.BOLD, 40));
        
        caminhoTemplate = new JFileChooser ();
        caminhoListaNomes = new JFileChooser ();
        caminhoDestino = new JFileChooser ();
        
        btnAbrirTemplate = new JButton ("Selecionar Template");
        btnAbrirTemplate.addActionListener(this);
        
        btnAbrirListaNomes = new JButton ("Selecionar Lista de Nomes");
        btnAbrirListaNomes.addActionListener(this);
        btnAbrirListaNomes.setEnabled(false);
        
        btnDestino = new JButton ("Selecionar Destino");
        btnDestino.addActionListener(this);
        btnDestino.setEnabled(false);
        
        painelOpcoes = new JPanel ();
        painelOpcoes.setLayout(new GridLayout(1,4));
        
        painelX = new JPanel ();
        TitledBorder borderX = new TitledBorder("Posição X:");
        borderX.setTitleJustification(TitledBorder.CENTER);
        borderX.setTitlePosition(TitledBorder.TOP);
        posicaoX = new JTextField (10);
        posicaoX.setText("0");
        painelX.add(posicaoX);
        painelX.setBorder(borderX);
        painelX.setEnabled(false);
        posicaoX.setEnabled(false);
        
        painelY = new JPanel ();
        TitledBorder borderY = new TitledBorder("Posição Y:");
        borderY.setTitleJustification(TitledBorder.CENTER);
        borderY.setTitlePosition(TitledBorder.TOP);
        posicaoY = new JTextField (10);
        posicaoY.setText("0");
        painelY.add(posicaoY);
        painelY.setBorder(borderY);
        painelY.setEnabled(false);
        posicaoY.setEnabled(false);
        
        painelTamanho = new JPanel ();
        TitledBorder borderTamanho = new TitledBorder("Tamanho Fonte:");
        borderTamanho.setTitleJustification(TitledBorder.CENTER);
        borderTamanho.setTitlePosition(TitledBorder.TOP);
        tamanho = new JTextField (10);
        tamanho.setText("10");
        painelTamanho.add(tamanho);
        painelTamanho.setBorder(borderTamanho);
        painelTamanho.setEnabled(false);
        tamanho.setEnabled(false);
        
        btnCor = new JButton ("Cor");
        btnCor.addActionListener(this);
        btnCor.setEnabled(false);
        
        painelOpcoes.add(painelX);
        painelOpcoes.add(painelY);
        painelOpcoes.add(painelTamanho);
        painelOpcoes.add(btnCor);
        
        btnStart = new JButton ("Iniciar");
        btnStart.addActionListener(this);
        btnStart.setEnabled(false);
        
        add(titulo);
        add(btnAbrirTemplate);
        add(btnAbrirListaNomes);
        add(btnDestino);
        add(painelOpcoes);
        add(btnStart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbrirTemplate) {
            caminhoTemplate.setDialogTitle("Selecionar Template");
            caminhoTemplate.setFileFilter(new FileNameExtensionFilter("*.pdf", "pdf", "PDF", "*.PDF"));
            if (caminhoTemplate.showOpenDialog(btnAbrirTemplate) == JFileChooser.APPROVE_OPTION) {
                template = caminhoTemplate.getSelectedFile().getAbsolutePath();
            }
            if (caminhoTemplate.getSelectedFile().getAbsolutePath() != null) {
                btnAbrirTemplate.setText("Template: "+template);
                btnAbrirListaNomes.setEnabled(true);
            }
        }
        if (e.getSource() == btnAbrirListaNomes) {
            caminhoListaNomes.setDialogTitle("Selecionar Lista de Nomes");
            caminhoListaNomes.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            if (caminhoListaNomes.showOpenDialog(btnAbrirListaNomes) == JFileChooser.APPROVE_OPTION) {
                listaNomes = caminhoListaNomes.getSelectedFile().getAbsolutePath();
            }
            if (caminhoListaNomes.getSelectedFile().getAbsolutePath() != null) {
                btnAbrirListaNomes.setText("Nomes: "+listaNomes);
                btnDestino.setEnabled(true);
            }
        }
        if (e.getSource() == btnDestino) {
            caminhoDestino.setDialogTitle("Selecionar Destino");
            caminhoDestino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (caminhoDestino.showOpenDialog(btnDestino) == JFileChooser.APPROVE_OPTION) {
                destino = caminhoDestino.getSelectedFile().getAbsolutePath();
            }
            if (caminhoDestino.getSelectedFile().getAbsolutePath() != null) {
                btnDestino.setText("Destino: "+destino);
                painelX.setEnabled(true);
                posicaoX.setEnabled(true);
                painelY.setEnabled(true);
                posicaoY.setEnabled(true);
                btnCor.setEnabled(true);
                painelTamanho.setEnabled(true);
                tamanho.setEnabled(true);
                btnStart.setEnabled(true);
            }
        }
        if (e.getSource() == btnCor) {
            color = JColorChooser.showDialog(this, "Escolher a color", color );
            btnCor.setText("R: "+color.getRed()+"  G: "+color.getGreen()+"  B: "+color.getBlue());
        }
        if (e.getSource() == btnStart) {
            try {
                painelX.setEnabled(false);
                posicaoX.setEnabled(false);
                painelY.setEnabled(false);
                posicaoY.setEnabled(false);
                btnCor.setEnabled(false);
                painelTamanho.setEnabled(false);
                tamanho.setEnabled(false);
                btnStart.setEnabled(false);
                btnDestino.setEnabled(false);
                btnAbrirListaNomes.setEnabled(false);
                btnAbrirTemplate.setEnabled(false);
                btnStart.setText("Gerando certificados...");
                gerarCertificados ();
            } catch (IOException | DocumentException ex) {
                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void gerarCertificados () throws FileNotFoundException, IOException, DocumentException {
        int i = 0;
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();
        int X = Integer.parseInt(posicaoX.getText());
        int Y = Integer.parseInt(posicaoY.getText());
        float fontSize = Float.parseFloat(tamanho.getText());
        
        File diretorio = new File(destino);
        diretorio.mkdir();
        ArrayList<String> nomes = new ArrayList<>();
        File arquivoNomes = new File(listaNomes);
        try (BufferedReader in = new BufferedReader (new FileReader(arquivoNomes))) {
            String nomeAtual;
            while (in.ready()) {
                nomeAtual = in.readLine();
                nomes.add(nomeAtual);
            }
        }
        com.itextpdf.text.Font minhaFonte = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        
        while (i < nomes.size()) {
            btnStart.setText(((i*100) / nomes.size()) + " %");
            PdfReader pdfReader = new PdfReader(template);
            PdfStamper stamper = new PdfStamper (pdfReader, new FileOutputStream(destino + "/" + nomes.get(i).toUpperCase()+".pdf"));
            PdfContentByte conteudo = stamper.getOverContent( 1 );
            conteudo.saveState();
            conteudo.beginText();
            conteudo.setRGBColorFill(R, G, B);
            conteudo.setFontAndSize( minhaFonte.getBaseFont(), fontSize );
            conteudo.setTextMatrix( X, Y );
            conteudo.showText( nomes.get(i).toUpperCase() );
            conteudo.endText();
            conteudo.restoreState();
            
            stamper.close();
            pdfReader.close();
            System.out.println(nomes.get(i).toUpperCase());
            i++;
        }
        btnStart.setText("Sucesso! Certificados Gerados: " + nomes.size());
    }
}
