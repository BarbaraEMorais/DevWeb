package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Compra;
import java.sql.Date;

/*
-- Estrutura da tabela `usuarios`

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class CompraDAO {

    public void Inserir(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario)"
                    + " VALUES (?,?,?,?,?,?)");
            sql.setInt(1, compra.getQuantidade_compra());
            sql.setString(2, compra.getData_compra());
            sql.setFloat(3,compra.getValor_compra());
            sql.setInt(4, compra.getId_fornecedor());
            sql.setInt(5, compra.getId_produto());
            sql.setInt(6, compra.getId_funcionario());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Compra getCompra(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Compra compra = new Compra();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    compra.setId(Integer.parseInt(resultado.getString("ID")));
                    compra.setQuantidade_compra(resultado.getInt("quantidade_compra"));
                    compra.setData_compra(resultado.getString("data_compra"));
                    compra.setValor_compra(resultado.getFloat("valor_compra"));
                    compra.setId_fornecedor(resultado.getInt("id_fornecedor"));
                    compra.setId_produto(resultado.getInt("id_produto"));
                    compra.setId_funcionario(resultado.getInt("id_funcionario"));
                }
            }
            return compra;

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE compras SET quantidade_Compra = ?, data_Compra = ?, valor_Compra = ?, id_fornecedor = ?, id_produto = ?, id_funcionario = ?  WHERE ID = ? ");
            sql.setInt(1, compra.getQuantidade_compra());
            sql.setString(2, compra.getData_compra());
            sql.setFloat(3, compra.getValor_compra());
            sql.setInt(4, compra.getId_fornecedor());
            sql.setInt(5, compra.getId_produto());
            sql.setInt(6, compra.getId_produto());
            sql.setInt(7, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM compras WHERE ID = ? ");
            sql.setInt(1, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Compra> ListaDeCompras() {
        ArrayList<Compra> meusCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM compras order by data_compra";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Compra compra = new Compra(resultado.getInt("quantidade_compra"),
                            resultado.getString("data_compra"),
                            resultado.getFloat("valor_compra"),
                            resultado.getInt("id_fornecedor"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_funcionario"));
                    compra.setId(Integer.parseInt(resultado.getString("ID")));
                    meusCompras.add(compra);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Query de select (ListaDeCompras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCompras;
    }

}
