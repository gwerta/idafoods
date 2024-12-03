package repository;

//importação das bibliotecas e outros arquivos

import models.Alimentos;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentosRepository {

 
    public void adicionarAlimentos(Alimentos alimentos) {
       
        String sql = "INSERT INTO alimentos (Nome, Calorias, Sabor, Saciedade) VALUES (?, ?, ?, ?)";

       
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

 
            stmt.setString(1, alimentos.getNome());
            stmt.setInt(2, alimentos.getCalorias());
            stmt.setInt(3, alimentos.getSabor());
            stmt.setString(4, alimentos.getSaciedade());

        
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Alimento adicionado com sucesso!");
            }

        } catch (SQLException e) {
      
            System.out.println("Erro ao adicionar alimento!");
            e.printStackTrace();
        }
    }

 
    public List<Alimentos> obterTodosAlimentos() {
        List<Alimentos> alimentos = new ArrayList<>(); 
        String sql = "SELECT * FROM alimentos"; 

  
        try (Connection conn = DbConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

        
            while (rs.next()) {
                Alimentos alimento = new Alimentos(
                    rs.getInt("alimentos_id"), 
                    rs.getString("nome"), 
                    rs.getInt("calorias"), 
                    rs.getInt("sabor"),
                    rs.getString("saciedade")
                );
                alimentos.add(alimento); 
            }

        } catch (SQLException e) {
          
            System.out.println("Erro ao obter alimentos.");
            e.printStackTrace();
        }

        return alimentos; 
    }

    public Alimentos obterAlimentoPorId(int alimentos_id) {
        String sql = "SELECT * FROM alimentos WHERE alimentos_id = ?"; 
        Alimentos alimento = null; 

        
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setInt(1, alimentos_id);
            ResultSet rs = stmt.executeQuery(); 

          
            if (rs.next()) {
                alimento = new Alimentos(
                    rs.getInt("alimentos_id"),
                    rs.getString("nome"),
                    rs.getInt("calorias"),
                    rs.getInt("sabor"),
                    rs.getString("saciedade")
                );
            }

        } catch (SQLException e) {
         
            System.out.println("Erro ao obter contato por ID");
            e.printStackTrace();
        }

        return alimento; 
    }

    
    public void atualizarAlimentos(Alimentos alimento) {
     
        String sql = "UPDATE alimentos SET nome = ?, calorias = ?, sabor = ?, saciedade = ? WHERE alimentos_id = ?";

    
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

        
            stmt.setString(1, alimento.getNome());
            stmt.setInt(2, alimento.getCalorias());
            stmt.setInt(3, alimento.getSabor());
            stmt.setString(4, alimento.getSaciedade());
            stmt.setInt(5, alimento.getAlimentosId()); 

           
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Alimento atualizado com sucesso!");
            } else {
                System.out.println("Alimento não encontrado.");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar alimento.");
            e.printStackTrace();
        }
    }

    
    public void deletarAlimentos(int alimentos_id) {
        String sql = "DELETE FROM alimentos WHERE alimentos_id = ?"; 

       
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

          
            stmt.setInt(1, alimentos_id);

           
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Alimento deletado com sucesso!");
            } else {
                System.out.println("Alimento não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar alimento.");
            e.printStackTrace();
        }
    }



}