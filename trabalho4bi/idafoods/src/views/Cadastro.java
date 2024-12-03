package views;
import java.awt.*;
import javax.swing.*;



public class Cadastro extends JFrame {

    private JButton jButton_Todos, jButton_Salvar;
    private JPanel buttonJPanel, radioJPanel, eastJPanel, westJPanel, centralJPanel, generalJPanel;
    private JRadioButton radio1, radio2, radio3;
    private ButtonGroup radioGroup;
    private JLabel nome, saciedade, calorias, sabor;
    private JTextField tnome, tcalorias;
    private JComboBox nota;
    private boolean isEditMode;
   
  
    

    @SuppressWarnings({ "unchecked", "rawtypes" })

  
    
    public Cadastro() {
        super("Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


   

        Container janela = getContentPane();
        janela.setLayout(new BorderLayout());

        // Inicializando JComboBox para notas de sabor
        nota = new JComboBox();
        
            nota.addItem("1");
            nota.addItem("2");
            nota.addItem("3");
            nota.addItem("4");
            nota.addItem("5");
            nota.addItem("6");
            nota.addItem("7");
            nota.addItem("8");
            nota.addItem("9");
            nota.addItem("10");

        // Inicializando JTextFields
        tnome = new JTextField(10);
        tcalorias = new JTextField(10);

        // Inicializando JLabels
        saciedade = new JLabel("Saciedade:");
        nome = new JLabel("Nome do alimento:");
        calorias = new JLabel("Calorias em 100g:");
        sabor = new JLabel("Nota do sabor:");
    

        // Inicializando painel west e adicionando componentes
        westJPanel = new JPanel();
        westJPanel.setLayout(new GridLayout(4, 1));
        westJPanel.add(nome);
        westJPanel.add(tnome);
        westJPanel.add(calorias);
        westJPanel.add(tcalorias);
        
       

        // Inicializando painel de rádios
        radioJPanel = new JPanel();
        radioJPanel.setLayout(new GridLayout(1, 3));
        radio1 = new JRadioButton("Boa");
        radio2 = new JRadioButton("Media", true);
        radio3 = new JRadioButton("Ruim");
        radioJPanel.add(radio1);
        radioJPanel.add(radio2);
        radioJPanel.add(radio3);

        // Agrupando os radio buttons
        radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        radioGroup.add(radio3);

        // Inicializando painel east e adicionando componentes
        eastJPanel = new JPanel();
        eastJPanel.setLayout(new GridLayout(4, 1));
        eastJPanel.add(saciedade);
        eastJPanel.add(radioJPanel); 
        eastJPanel.add(sabor);
        eastJPanel.add(nota);

        // Inicializando botões
        jButton_Todos = new JButton("Todos");
        jButton_Salvar = new JButton("Salvar");

        // Inicializando painel de botões com todos os botões combinados
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonGroup1 = new JPanel(new FlowLayout());
        buttonGroup1.add(jButton_Todos);
        buttonGroup1.add(jButton_Salvar);

        // Inicializando painel central
        centralJPanel = new JPanel();
        centralJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        centralJPanel.add(westJPanel);
        centralJPanel.add(eastJPanel);

        // Inicializando painel geral
        generalJPanel = new JPanel();
        generalJPanel.setLayout(new BorderLayout());
        generalJPanel.add(centralJPanel, BorderLayout.CENTER);
        generalJPanel.add(buttonGroup1, BorderLayout.SOUTH);

        // Adicionando painel geral ao JFrame
        janela.add(generalJPanel, BorderLayout.CENTER);


    }
}
    

   

