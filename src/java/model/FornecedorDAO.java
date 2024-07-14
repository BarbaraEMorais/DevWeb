/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Fornecedor;

/**
 *
 * @author barbara_morais
 */
public class FornecedorDAO {
    
    public void Inserir(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Fornecedor getFornecedor(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Fornecedor fornecedor = new Fornecedor();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    fornecedor.setId(Integer.parseInt(resultado.getString("ID")));
                    fornecedor.setRazaoSocial(resultado.getString("razao_social"));
                    fornecedor.setCnpj(resultado.getString("cnpj"));
                    fornecedor.setEndereco(resultado.getString("endereco"));
                    fornecedor.setBairro(resultado.getString("bairro"));
                    fornecedor.setCidade(resultado.getString("cidade"));
                    fornecedor.setUf(resultado.getString("uf"));
                    fornecedor.setCep(resultado.getString("Cep"));
                    fornecedor.setTelefone(resultado.getString("telefone"));
                    fornecedor.setEmail(resultado.getString("email"));                    
                }
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE ID = ? ");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());          
            sql.setInt(10, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Fornecedor> ListaDeFornecedores() {
        ArrayList<Fornecedor> meusFornecedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM fornecedores order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    
                    Fornecedor fornecedor = new Fornecedor(resultado.getString("razao_social"),
                            resultado.getString("cnpj"),
                            resultado.getString("endereco"),
                            resultado.getString("bairro"),
                            resultado.getString("cidade"),
                            resultado.getString("uf"),
                            resultado.getString("cep"),
                            resultado.getString("telefone"),
                            resultado.getString("email"));
                            
                    fornecedor.setId(Integer.parseInt(resultado.getString("id")));
                    meusFornecedores.add(fornecedor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeFornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFornecedores;
    }
    
}
