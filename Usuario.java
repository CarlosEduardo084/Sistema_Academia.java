public abstract class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String senha;

    public Usuario(int id, String nome, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean autenticar(String cpf, String senha) {
        return this.cpf.equals(cpf) && this.senha.equals(senha);
    }

    public String consultarDados() {
        return "ID: " + id
            + " | Nome: " + nome
            + " | CPF: " + cpf;
    }
}