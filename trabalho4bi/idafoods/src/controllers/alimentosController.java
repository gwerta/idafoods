package controllers;

//importação das bibliotecas e outros arquivos

import models.Alimentos;
import repository.AlimentosRepository;
import views.AlimentosForm;
import views.AlimentosTableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//classe principal para controlar e iniciar o repository e a tableview
public class AlimentosController {

    private AlimentosRepository repository;
    private AlimentosTableView tableView;


    //método construtor
    public AlimentosController() {
        repository = new AlimentosRepository();
        tableView = new AlimentosTableView();
        inicializar();
    }

    //classe que configura as funções principais da interface
    
    private void inicializar() {
        
        atualizarTabela();
        
        JToolBar toolBar = new JToolBar();
        JButton adicionarButton = new JButton("Adicionar");
        JButton editarButton = new JButton("Editar");
        JButton deletarButton = new JButton("Deletar");
        toolBar.add(adicionarButton);
        toolBar.add(editarButton);
        toolBar.add(deletarButton);

        tableView.add(toolBar, java.awt.BorderLayout.NORTH);


        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                adicionarAlimentos();
            }
            
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                editarAlimentos();
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) { 
                deletarAlimentos();
            }
        });
        
        tableView.setVisible(true);
    }

    //classe que atualiza a tabela depois de um salvamento e mostra todos os dados na tabela

    private void atualizarTabela() {
        List<Alimentos> alimentos = repository.obterTodosAlimentos();
        tableView.atualizarTabela (alimentos);
    }


    //classe para adicionar um novo alimento
    private void adicionarAlimentos() {
        AlimentosForm form = new AlimentosForm(tableView, "Adicionar Alimentos");
        form.setVisible(true);
        Alimentos novoAlimentos = form.getAlimentos();
        if (novoAlimentos != null) {
            repository.adicionarAlimentos(novoAlimentos);
            atualizarTabela();
        }
    }
    //classe para editar um alimento
    private void editarAlimentos() {
        int selectedId = tableView.getSelectedAlimentosId();
        if (selectedId != -1) {
            Alimentos alimentos = repository.obterAlimentoPorId(selectedId);
            if (alimentos != null) {
                AlimentosForm form = new AlimentosForm(tableView, "Editar Alimento", alimentos);
                form.setVisible(true);
                Alimentos alimentosAtualizado = form.getAlimentos();
                if (alimentosAtualizado != null) {
                    alimentosAtualizado = new Alimentos(
                        selectedId,
                        alimentosAtualizado.getNome(),
                        alimentosAtualizado.getCalorias(),
                        alimentosAtualizado.getSabor(),
                        alimentosAtualizado.getSaciedade()   
                    );
                    repository.atualizarAlimentos (alimentosAtualizado);
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(tableView,
                    "Alimento não encontrado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(tableView,
                "Selecione um alimento para editar.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        }    
    }

    //classe para deletar um alimento
    
    private void deletarAlimentos() {
        int selectedId = tableView.getSelectedAlimentosId();
        if (selectedId != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                tableView,
                "Tem certeza que deseja deletar este alimento?",
                 "Confirmar Deleção",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) { 
                repository.deletarAlimentos (selectedId);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(
            tableView,
            "Selecione um alimento para deletar.", 
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        }
    }

    //classe para iniciar o contrutor vazio
        
    public void iniciar() {
        
    }
}