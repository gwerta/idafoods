package views;

import models.Alimentos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlimentosForm extends JDialog {
    private JPanel radioJPanel;
    private JTextField tnome, tcalorias;
    private JComboBox nota;
    private JRadioButton radio1, radio2, radio3;
    private ButtonGroup radioGroup;
    private JButton salvarButton;
    private JButton cancelarButton;

    private Alimentos alimentos;
    private boolean isEditMode;


    public AlimentosForm (Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initializeComponents();
    }

    public AlimentosForm (Frame parent, String title, Alimentos alimentos) {
        super(parent, title, true);
        this.alimentos = alimentos;
        this.isEditMode = true;
        initializeComponents();
        preencherCampos();
    }
    
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

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
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
   
        // Adicionando uma margem de 10 pixels nas bordas laterais e verticais panel.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));
        salvarButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed (ActionEvent e) {
                if (validarCampos()) {
                    if (isEditMode) {
                    } else {
                        atualizarAlimentos();
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

    private void preencherCampos () {
        if (alimentos != null) {
            tnome.setText(alimentos.getNome());
            tcalorias.setText(String.valueOf(alimentos.getCalorias()));
            nota.setSelectedItem(alimentos.getSaciedade());
            switch (alimentos.getSaciedade()) {
                case 1 -> radio1.setSelected(true);
                case 2 -> radio2.setSelected(true);
                case 3 -> radio3.setSelected(true);
            }
        }
    }
    
    private boolean validarCampos() {
        if (tnome.getText().trim().isEmpty() || tcalorias.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,"O nome e as calorias são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void adicionarAlimentos() {
        alimentos = new Alimentos(
            0,
            tnome.getText().trim(),
            Integer.parseInt(tcalorias.getText().trim()),
            Integer.parseInt(nota.getSelectedItem().toString()), 
            radio1.isSelected() ? 1 : (radio2.isSelected() ? 2 : 3) 
        );
    }
    
    private void atualizarAlimentos() {
        if (alimentos != null) {
            alimentos.setNome(tnome.getText().trim());
            alimentos.setCalorias(Integer.parseInt(tcalorias.getText().trim()));
            alimentos.setSabor(Integer.parseInt(nota.getSelectedItem().toString()));
            alimentos.setSaciedade(radio1.isSelected() ? 1 : (radio2.isSelected() ? 2 : 3));
        }
    }

    public Alimentos getAlimentos() {
        return alimentos;
    }
}