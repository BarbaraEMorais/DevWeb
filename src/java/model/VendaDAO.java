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
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Vendas (nome, cpf, papel, senha)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, venda.getNome());
            sql.setString(2, venda.getCpf());
            sql.setString(3, venda.getPapel());
            sql.setString(4, venda.getSenha());
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Vendas WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    venda.setId(Integer.parseInt(resultado.getString("ID")));
                    venda.setNome(resultado.getString("NOME"));
                    venda.setCpf(resultado.getString("CPF"));
                    venda.setPapel(resultado.getString("papel"));
                    venda.setSenha(resultado.getString("SENHA"));
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE vendas SET nome = ?, cpf = ?, papel = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, venda.getNome());
            sql.setString(2, venda.getCpf());
            sql.setString(3, venda.getPapel());
            sql.setString(4, venda.getSenha());
            sql.setInt(5, venda.getId());
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
                    Venda venda = new Usuario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("PAPEL"),
                            resultado.getString("SENHA"));
                    venda.setId(Integer.parseInt(resultado.getString("ID")));
                    meusVendas.add(venda);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeUsuarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusVendas;
    }

}
