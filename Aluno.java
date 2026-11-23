import java.time.LocalDate;

public class Aluno extends Usuario {
    private LocalDate dataNascimento;
    private String telefone;

    public Aluno(
        int id,
        String nome,
        String cpf,
        String senha,
        LocalDate dataNascimento,
        String telefone
    ) {
        super(id, nome, cpf, senha);
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public void atualizarDados(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String consultarDados() {
        return super.consultarDados()
            + " | Nascimento: " + dataNascimento
            + " | Telefone: " + telefone;
    }
}