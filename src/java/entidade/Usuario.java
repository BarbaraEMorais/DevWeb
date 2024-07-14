package entidade;

public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String papel;
    private String senha;

    public Usuario(String nome, String cpf, String papel, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.papel = papel;
        this.senha = senha;
    }

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Usuario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.papel = "";
        this.senha = "";;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
