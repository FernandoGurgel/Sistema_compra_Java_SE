package Tela;

import Dao.ProdutoDao;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Produtos;

/**
 * Base de consulta
 * 
*
 */
public class SystemVenda extends JPanel {

    private static final int N = 32;
    private JButton sair;
    private JFrame f;
    private JButton limpa;
    private JButton finaliza;
    private JButton consulta;
    private JTextField subtotal;
    private JTextField quantidade;
    private JTextField codigo;
    private JTextField descricao;
    private JTextField vlun;
    private double qtd;
    private double valor;
    private double total;
    private javax.swing.JScrollPane jScrollPane1;
    private JTable Table;
    private double lixo = 0;
    private JTextPane terminar;
    private JTextField txtSubtotal;

    public SystemVenda() {

    }

    public void display() {

        f = new JFrame("Loja Tomorrowland");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 600);

        //f.pack();
        //f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        // Tela cupom 
        JPanel cupom = new JPanel();
        cupom.setSize(400, 540);
        cupom.setLocation(480, 10);
        cupom.setBackground(new Color(250, 255, 174));
        cupom.setLayout(null);

        JTextPane cbc = new JTextPane();
        //  cbc = new JLabel();
        cbc.setText("\tVenda direta Tomorrowland Brasil\n"
                + "CNPJ.: 17.359.009/0001-96\t IE.:00.000.000-0 \n"
                + "Rod. Vergonha de ser flamengista, 2, Divisao\n"
                + "Manaus - AM\n"
                + "_____________________________________________________________________________________________________");

        cbc.setBackground(new Color(250, 255, 174));
        cbc.setBounds(10, 100, 380, 90);
        cbc.setEditable(false);

        terminar = new JTextPane();

        terminar.setBackground(new Color(250, 255, 174));
        terminar.setBounds(10, 400, 380, 20);
        terminar.setText("--------------------------------------------------------------------------------------------");

        //falta coloca evento
        JLabel JSubtotal = new JLabel("Total Compra");
        JSubtotal.setBounds(210, 425, 100, 20);

        txtSubtotal = new JTextField();
        txtSubtotal.setBounds(315, 425, 70, 20);
        txtSubtotal.setEditable(false);
        txtSubtotal.setBackground(new Color(250, 255, 174));

        //fim dos evento que falta
        JLabel logo = new JLabel();
        logo.setSize(380, 70);
        logo.setIcon(new ImageIcon("/home/fernando/Imagens/LogoSimple.png"));
        logo.setLocation(20, 15);

        // add tabela
        Table = new JTable();
        
        Table.setModel(new DefaultTableModel(
                new Object[][]{},
                //TITULOS DAS SUAS COLUNAS  
                new String[]{"Qtd. ", "Produto", "Vl. Venda"}) {
                    public boolean isCellEditable(int r, int c) {
                        return false;
                    }
                });

        //ALINHAMENTO DO TEXTO
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        //DEFINE O TAMANHO DA LARGURA DA CULUNA  
        Table.getColumnModel().getColumn(0).setPreferredWidth(50);
        //NÃO PERMITE REDIMENCIONAMENTO  
        Table.getColumnModel().getColumn(0).setResizable(false);
        //CENTRALIZA O TEXTO  
        Table.getColumnModel().getColumn(0).setCellRenderer(center);

        Table.getColumnModel().getColumn(1).setPreferredWidth(250);
        Table.getColumnModel().getColumn(1).setResizable(false);
        Table.getColumnModel().getColumn(1).setCellRenderer(center);
        Table.getColumnModel().getColumn(2).setPreferredWidth(77);
        Table.getColumnModel().getColumn(2).setResizable(false);
        Table.getColumnModel().getColumn(2).setCellRenderer(center);

        //DEFINE A FONTE  
        Table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        //NÃO PERMITE A REORDENAÇÃO DAS COLUNAS  
        Table.getTableHeader().setReorderingAllowed(false);
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table.setSelectionMode(0);
       

        JScrollPane Scroll = new JScrollPane();
        Scroll.setViewportView(Table);
        Scroll.setBounds(10, 195, 380, 200);
        cupom.add(Scroll);

        //Cupom componentes
        cupom.add(JSubtotal);
        cupom.add(txtSubtotal);
        cupom.add(cbc);
        cupom.add(logo);
        cupom.add(terminar);

        //Imagem 
        JLabel imagem = new JLabel();
        imagem.setSize(400, 200);

        imagem.setIcon(new ImageIcon("/home/fernando/Imagens/logo.png"));
        imagem.setLocation(30, 10);

        // Painel Campos
        JPanel arusuario = new JPanel();
        arusuario.setSize(400, 320);
        arusuario.setLocation(40, 260);
        arusuario.setLayout(null);

        // Nome Quantidade
        JLabel qtd = new JLabel("Qtd.");
        qtd.setBounds(30, 25, 80, 25);
        qtd.setBackground(Color.BLACK);

        // Campo Qtd
        quantidade = new JTextField();
        quantidade.setBounds(25, 50, 55, 30);
        quantidade.setText(String.valueOf(1));
        // Nome Codigo
        JLabel cod = new JLabel("Codigo");
        cod.setBounds(100, 25, 50, 25);
        cod.setForeground(Color.BLACK);

        //Campo Codigo
        codigo = new JTextField();
        codigo.setBounds(100, 50, 275, 30);

        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acaoTeclado(evt);
            }
        });

        //Nome Descricao
        JLabel desc = new JLabel("Descricao");
        desc.setBounds(25, 75, 80, 30);
        desc.setBackground(Color.BLACK);

        //Campo Descricao
        descricao = new JTextField();
        descricao.setBounds(25, 105, 350, 30);

        //Nome Vl Unitario
        JLabel vl = new JLabel("Vl. Unit.");
        vl.setBounds(25, 135, 80, 30);
        vl.setBackground(Color.BLACK);

        //Campo Vl Unit.
        vlun = new JTextField();
        vlun.setBounds(25, 165, 100, 30);
        vlun.setEditable(false);

        //Nome Subtotal
        JLabel sub = new JLabel("SubTotal");
        sub.setBounds(150, 135, 100, 30);

        //Campo Subtotal
        subtotal = new JTextField();
        subtotal.setBounds(150, 165, 120, 30);
        subtotal.setEditable(false);

        //Botao Finaliza
        finaliza = new JButton("Finaliza ");
        finaliza.setBounds(10, 220, 120, 30);
        finaliza.addActionListener(new acao_Butao());

        //Botao Consulta
        consulta = new JButton("Consulta ");
        consulta.setBounds(135, 220, 130, 30);
        consulta.addActionListener(new acao_Butao());
        consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acaoTeclado(evt);
            }
        });

        //Botao Limpa
        limpa = new JButton("Limpa ");
        limpa.setBounds(270, 220, 120, 30);
        limpa.addActionListener(new acao_Butao());

        // Botao Sair
        sair = new JButton("Sair ");
        sair.setBounds(10, 260, 380, 30);
        sair.addActionListener(new acao_Butao());

        //Chama arrgumento da tela 
        arusuario.add(qtd);
        arusuario.add(sub);

        arusuario.add(vl);
        arusuario.add(vlun);
        arusuario.add(subtotal);
        arusuario.add(finaliza);
        arusuario.add(consulta);
        arusuario.add(limpa);
        arusuario.add(sair);
        arusuario.add(cod);
        arusuario.add(quantidade);
        arusuario.add(descricao);
        arusuario.add(desc);
        arusuario.add(this);
        arusuario.add(codigo);
        //--- fim ---

        // Chama componente da tela
        f.add(cupom);
        f.add(arusuario);
        f.add(imagem);
        f.add(this);

    }

    private void acaoTeclado(KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ProdutoDao ctProduto = new ProdutoDao();

            Produtos produtos = ctProduto.trazerCod(Integer.parseInt(codigo.getText()));

            descricao.setText(produtos.getNome());
            vlun.setText(Double.toString(produtos.getValor()));

            qtd = Double.parseDouble(quantidade.getText());
            valor = Double.parseDouble(vlun.getText());
            total = qtd * valor;

            subtotal.setText(String.valueOf(total));

            DefaultTableModel modelo = (DefaultTableModel) Table.getModel();

            modelo.addRow(new Object[]{qtd, descricao.getText(), subtotal.getText()});

            lixo += Double.parseDouble(subtotal.getText());

            txtSubtotal.setText(String.valueOf(lixo));

        }
    }

    class acao_Butao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {
            //funcao sair
            if (evento.getSource() == sair) {
                System.exit(0);

                //cahamdno tela consulta
            } else if (evento.getSource() == consulta) {
                Consulta consulta = new Consulta();
                consulta.setVisible(true);
                

                //limpando tela
            } else if (evento.getSource() == limpa) {

                codigo.setText("");
                descricao.setText("");
                vlun.setText("");
                subtotal.setText("");
                DefaultTableModel modelo = (DefaultTableModel) Table.getModel();
                modelo.setNumRows(0);
                lixo = 0;
                txtSubtotal.setText(String.valueOf(lixo));

            } else if (evento.getSource() == finaliza) {

                if (lixo != 0) {
                    JOptionPane.showMessageDialog(null, "Muito Obrigado pela compra\n Valor da compra: R$ " + lixo);
                    lixo = 0;
                    codigo.setText("");
                    descricao.setText("");
                    vlun.setText("");
                    subtotal.setText("");
                    DefaultTableModel modelo = (DefaultTableModel) Table.getModel();
                    modelo.setNumRows(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Atencao nao tem nenhum produto");
                }

            }

        }

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SystemVenda().display();

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(240, 190, 120);
        Color color2 = new Color(41, 24, 15);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(
                10, 50, color1, 10, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
