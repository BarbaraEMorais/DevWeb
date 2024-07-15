/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author barbara_morais
 */
public class Compra {
    
    private int id;
    private int quantidade_compra;      
    private String data_compra;
    private float valor_compra;
    private int id_fornecedor;
    private int id_produto;
    private int id_funcionario;


    public Compra(int quantidade_compra,String data_compra, float valor_compra, int id_fornecedor, int id_produto, int id_funcionario) {
        
        this.quantidade_compra = quantidade_compra;
        this.data_compra = data_compra;
        this.valor_compra = valor_compra; 
        this.id_fornecedor = id_fornecedor;
        this.id_produto = id_produto;
        this.id_funcionario = id_funcionario;
    }

    public Compra() {
        
        this.id = 0;
        this.quantidade_compra = 0;
        this.data_compra = "";
        this.valor_compra = 0; 
        this.id_fornecedor = 0;
        this.id_produto = 0;
        this.id_funcionario = 0;
    }
    
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade_compra() {
        return quantidade_compra;
    }

    public void setQuantidade_compra(int quantidade_compra) {
        this.quantidade_compra = quantidade_compra;
    }

    public String getData_compra() {
        return data_compra;
    }
    

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }
    
    
    public float getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(float valor_compra) {
        this.valor_compra = valor_compra;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    
}
