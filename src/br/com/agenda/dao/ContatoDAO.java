package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void save(Contato contato){
        String sql = "INSERT INTO contatos(nome,idade,datacadastro) VALUES (?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = ConnectionFactory.createConnectionToMySql();
            ps = conn.prepareStatement(sql);
            ps.setString(1,contato.getNome());
            ps.setInt(2,contato.getIdade());
            ps.setDate(3, new Date(contato.getDataCadastro().getTime()));

            ps.executeUpdate();

            System.out.println("contato salvo com sucesso");

        }  catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(ps != null){
                    ps.close();
                }

                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<Contato> getContatos () throws SQLException {

        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setNome((rs.getString("nome")));
                contato.setIdade((rs.getInt("idade")));
                contato.setDataCadastro(rs.getDate("dataCadastro"));

                contatos.add(contato);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(ps != null){
                    ps.close();
                }

                if(rs != null){
                    rs.close();
                }

                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return contatos;
    }

    public void update(Contato contato){
        String sql = "UPDATE contatos SET nome = ? ,idade = ?, datacadastro = ? "+
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            ps = conn.prepareStatement(sql);

            ps.setString(1,contato.getNome());
            ps.setInt(2,contato.getIdade());
            ps.setDate(3, new Date(contato.getDataCadastro().getTime()));
            ps.setInt(4,contato.getId());

            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(ps != null){
                    ps.close();
                }

                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            ps = conn.prepareStatement(sql);

            ps.setInt(1,id);

            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(ps != null){
                    ps.close();
                }

                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
