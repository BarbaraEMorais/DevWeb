/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Produto;

/**
 *
 * @author barbara_morais
 */
public class ProdutoDAO {
    
    public void Inserir(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, id_categoria)"
                    + " VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, produto.getNome_produto());
            sql.setString(2, produto.getDescricao());
            sql.setFloat(3, produto.getPreco_compra());
            sql.setFloat(4, produto.getPreco_venda());
            sql.setInt(5, produto.getQuantidade_disponivel());
            sql.setString(6, produto.getLiberado_venda());
            sql.setInt(7, produto.getId_categoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Produto getProduto(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Produto produto = new Produto();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM produtos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    produto.setId(Integer.parseInt(resultado.getString("ID")));
                    produto.setNome_produto(resultado.getString("nome_produto"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setPreco_compra(resultado.getFloat("preco_compra"));
                    produto.setPreco_venda(resultado.getFloat("preco_venda"));
                    produto.setQuantidade_disponivel(resultado.getInt("quantidade_disponivel"));
                    produto.setLiberado_venda(resultado.getString("liberado_venda"));
                    produto.setId_categoria(resultado.getInt("id_categoria"));
                                      
                }
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE produtos SET nome_produto = ?, descricao = ?, preco_compra = ?, preco_venda = ?, quantidade_disponivel = ?, liberado_venda = ?, id_categoria = ? WHERE ID = ? ");
            sql.setString(1, produto.getNome_produto());
            sql.setString(2, produto.getDescricao());
            sql.setFloat(3, produto.getPreco_compra());
            sql.setFloat(4, produto.getPreco_venda());
            sql.setInt(5, produto.getQuantidade_disponivel());
            sql.setString(6, produto.getLiberado_venda());
            sql.setInt(7, produto.getId_categoria());                     
            sql.setInt(10, produto.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM produtos WHERE ID = ? ");
            sql.setInt(1, produto.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Produto> ListaDeProdutos() {
        ArrayList<Produto> meusProdutos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM produtos order by nome_produto";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    
                    Produto produto = new Produto(resultado.getString("nome_produto"),
                            resultado.getString("descricao"),
                            resultado.getFloat("preco_compra"),
                            resultado.getFloat("preco_venda"),
                            resultado.getInt("quantidade_disponivel"),
                            resultado.getString("liberado_venda"),
                            resultado.getInt("id_categoria"));
       
                    produto.setId(Integer.parseInt(resultado.getString("id")));
                    meusProdutos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Query de select (ListaDeProdutos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusProdutos;
    }
    
}
