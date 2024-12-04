    package views;



    //importação das bibliotecas e outros arquivos
    import models.Alimentos;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.util.List;


    //cria a interface da tableview
    public class AlimentosTableView extends JFrame { 
        private JTable table;
        private DefaultTableModel tableModel;


        //construtor que configura a tableview
        public AlimentosTableView() {
            super("Gerenciamento de Alimentos");
            initializeComponents();
        }


        //define os componentes da tableview
        private void initializeComponents() {
            String[] columnNames = {"ID", "Nome", "Calorias", "Sabor", "Saciedade"};
            tableModel = new DefaultTableModel (columnNames, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane (table);

            scrollPane.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

            this.setLayout(new BorderLayout());
            this.add(scrollPane, BorderLayout.CENTER);

            this.setSize(600, 400);
            this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
        }


        //atualiza a tabela com todos os dados 
        public void atualizarTabela (List<Alimentos> Alimentos) {
            tableModel.setRowCount(0); // Limpa a tabela
            for (Alimentos alimentos: Alimentos) {
                Object[] row = {
                    alimentos.getAlimentosId(),
                    alimentos.getNome(),
                    alimentos.getCalorias(),
                    alimentos.getSabor(),
                    alimentos.getSaciedade()
                };
                tableModel.addRow(row);
            }
        }


        //permite a seleção de um alimento de acordo com o id dele atraves de um click na tabela
        public int getSelectedAlimentosId() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                return (int) tableModel.getValueAt(selectedRow, 0);
            }

            return -1;
        }
    }