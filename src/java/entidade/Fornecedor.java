/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author barbara_morais
 */
public class Fornecedor {
    
    private int id;
    private String razao_social;   
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;

    public Fornecedor(String razao_social, String cnpj, String endereco, String bairro, String cidade, String uf, String cep, String telefone, String email) {
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone = telefone;
        this.email = email;
    }

    public Fornecedor(String razao_social, String email) {
        this.razao_social = razao_social;
        this.email = email;
    }

    public Fornecedor() {
        this.id = 0;
        this.razao_social = "";
        this.cnpj = "";
        this.endereco = "";
        this.bairro = "";
        this.cep = "";
        this.cidade = "";
        this.uf = "";
        this.telefone = "";
        this.email = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRazaoSocial() {
        return razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }
    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
    
    public void setRazaoSocial(String razao_social) {
        this.razao_social = razao_social;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
