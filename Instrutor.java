public class Instrutor extends Usuario {
    private String turno;
    private boolean administrador;

    public Instrutor(
        int id,
        String nome,
        String cpf,
        String senha,
        String turno,
        boolean administrador
    ) {
        super(id, nome, cpf, senha);
        this.turno = turno;
        this.administrador = administrador;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public Treino criarTreino(int id, Aluno aluno, String objetivo) {
        return new Treino(id, aluno.getId(), getId(), objetivo);
    }

    @Override
    public String consultarDados() {
        return super.consultarDados()
            + " | Turno: " + turno
            + " | Administrador: "
            + (administrador ? "Sim" : "Não");
    }
}