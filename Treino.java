import java.time.LocalDate;
import java.util.ArrayList;

public class Treino {
    private int id;
    private int idAluno;
    private int idInstrutor;
    private String objetivo;
    private LocalDate dataCriacao;
    private ArrayList<Exercicio> exercicios = new ArrayList<>();

    public Treino(
        int id,
        int idAluno,
        int idInstrutor,
        String objetivo
    ) {
        this.id = id;
        this.idAluno = idAluno;
        this.idInstrutor = idInstrutor;
        this.objetivo = objetivo;
        this.dataCriacao = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio.getIdTreino() != id) {
            throw new IllegalArgumentException(
                "O exercício pertence a outro treino."
            );
        }

        exercicios.add(exercicio);
    }

    public boolean removerExercicio(Exercicio exercicio) {
        if (exercicios.size() <= 1) {
            return false;
        }

        return exercicios.remove(exercicio);
    }

    public ArrayList<Exercicio> consultarExercicios() {
        return new ArrayList<>(exercicios);
    }

    public String consultarDados() {
        return "Treino " + id
            + " | Aluno: " + idAluno
            + " | Instrutor: " + idInstrutor
            + " | Objetivo: " + objetivo
            + " | Criado em: " + dataCriacao;
    }    
}
