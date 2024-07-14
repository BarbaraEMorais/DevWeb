package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Venda;

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
public class VendaDAO {

    public void Inserir(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario)"
                    + " VALUES (?,?,?,?,?,?)");
            sql.setString(1, venda.getQuantidade_venda());
            sql.setString(2, venda.getData_venda());
            sql.setString(3, venda.getValor_venda());
            sql.setString(4, venda.getId_cliente());
            sql.setString(4, venda.getId_produto());
            sql.setString(4, venda.getId_funcionario());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Venda getVenda(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Venda venda = new Venda();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM vendas WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    venda.setId(Integer.parseInt(resultado.getString("ID")));
                    venda.setQuantidade_venda(resultado.getString("quantidade_venda"));
                    venda.setData_venda(resultado.getString("data_venda"));
                    venda.setValor_venda(resultado.getString("valor_venda"));
                    venda.setId_cliente(resultado.getString("id_cliente"));
                    venda.setId_produto(resultado.getString("id_produto"));
                    venda.setId_funcionario(resultado.getString("id_funcionario"));
                }
            }
            return venda;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE vendas SET quantidade_venda = ?, data_venda = ?, valor_venda = ?, id_cliente = ?, id_produto = ?, id_funcionario = ?  WHERE ID = ? ");
            sql.setString(1, venda.getQuantidade_venda());
            sql.setString(2, venda.getData_venda());
            sql.setString(3, venda.getValor_venda());
            sql.setString(4, venda.getId_cliente());
            sql.setString(5, venda.getId_produto());
            sql.setString(6, venda.getId_produto());
            sql.setInt(7, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM vendas WHERE ID = ? ");
            sql.setInt(1, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Venda> ListaDeVendas() {
        ArrayList<Venda> meusVendas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM vendas order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Venda venda = new Venda(resultado.getString("quantidade_venda"),
                            resultado.getString("data_venda"),
                            resultado.getString("valor_venda"),
                            resultado.getString("id_cliente"),
                            resultado.getString("id_produto"),
                            resultado.getString("id_funcionario"));
                    venda.setId(Integer.parseInt(resultado.getString("ID")));
                    meusVendas.add(venda);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeVendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusVendas;
    }

}
