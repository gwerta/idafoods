package views;

//importação das bibliotecas e outros arquivos

import models.Alimentos;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//criação dos elementos do jframe
public class AlimentosForm extends JDialog {
    private JPanel radioJPanel;
    private JTextField tnome, tcalorias;
    @SuppressWarnings("rawtypes")
    private JComboBox nota;
    private JRadioButton radio1, radio2, radio3;
    private ButtonGroup radioGroup;
    private JButton salvarButton;
    private JButton cancelarButton;

    private Alimentos alimentos;
    private boolean isEditMode;


    //classe que configura o form quando não está no modo de edição


    public AlimentosForm (Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    //classe que configura o form quando está no modo de edição

    public AlimentosForm (Frame parent, String title, Alimentos alimentos) {
        super(parent, title, true);
        this.alimentos = alimentos;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }

    //inicialização dos componentes do jframe
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initializeComponents() { 
        tnome = new JTextField(20);
        tcalorias = new JTextField(20);

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

            radioJPanel = new JPanel();
            radioJPanel.setLayout(new FlowLayout());

            radio1 = new JRadioButton("Boa");
            radio2 = new JRadioButton("Media", true);
            radio3 = new JRadioButton("Ruim");

            radioJPanel.add(radio1);
            radioJPanel.add(radio2);
            radioJPanel.add(radio3);

            radioGroup = new ButtonGroup();
            radioGroup.add(radio1);
            radioGroup.add(radio2);
            radioGroup.add(radio3);

        
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        //criação do painel principal com os elementos

        JPanel panel = new JPanel(new GridLayout(5, 2, 0, 10));
        panel.add(new JLabel("Nome:"));
        panel.add(tnome);
        panel.add(new JLabel("Calorias em 100g:"));
        panel.add(tcalorias);
        panel.add(new JLabel("Nota:"));
        panel.add(nota);
        panel.add(new JLabel("Saciedade:"));
        panel.add(radioJPanel);
        panel.add(salvarButton);
        panel.add(cancelarButton);
   
        

        //configuração da ação dos botões dependendo se ele está no modo de edição ou não
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                        atualizarAlimentos();
                    } else {
                        adicionarAlimentos(); 
                    }
                    dispose(); 
                }
            }
        });
        
        cancelarButton.addActionListener(e -> dispose());

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(getParent());
    }


    //ele preenche os campos com os dados do alimento na tabela

    private void preencherCampos () {
        if (alimentos != null) {
            tnome.setText(alimentos.getNome());
            tcalorias.setText(String.valueOf(alimentos.getCalorias()));
            nota.setSelectedItem(String.valueOf(alimentos.getSabor()));
            if (alimentos.getSaciedade().equalsIgnoreCase("Boa")) {
                radio1.setSelected(true);
            } else if (alimentos.getSaciedade().equalsIgnoreCase("Media")) {
                radio2.setSelected(true);
            } else if (alimentos.getSaciedade().equalsIgnoreCase("Ruim")) {
                radio3.setSelected(true);
            }
        }
    }
    

    //validação para dar um aviso nos campos
    private boolean validarCampos() {
        if (tnome.getText().trim().isEmpty() || tcalorias.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,"O nome e as calorias são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                try {
                    Integer.parseInt(tcalorias.getText().trim());
                    Integer.parseInt(nota.getSelectedItem().toString());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Calorias e Nota devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            return false;

        }
        return true;
    }

    //cria um novo alimento na tabela

    private void adicionarAlimentos() {
        alimentos = new Alimentos(
            0,
            tnome.getText().trim(),
            Integer.parseInt(tcalorias.getText().trim()),
            Integer.parseInt(nota.getSelectedItem().toString()),
            radio1.isSelected() ? "Boa" : (radio2.isSelected() ? "Media" : "Ruim")
        );
    }
    //atualiza o alimento na tabela
    private void atualizarAlimentos() {
        if (alimentos != null) {
            alimentos.setNome(tnome.getText().trim());
            alimentos.setCalorias(Integer.parseInt(tcalorias.getText().trim()));
            alimentos.setSabor(Integer.parseInt(nota.getSelectedItem().toString()));
            if (radio1.isSelected()) {
                alimentos.setSaciedade("Boa");
            } else if (radio2.isSelected()) {
                alimentos.setSaciedade("Media");
            } else if (radio3.isSelected()) {
                alimentos.setSaciedade("Ruim");
            }
        }
    }
//permite o retorno de todos os dados do alimento
    public Alimentos getAlimentos() {
        return alimentos;
    }
}