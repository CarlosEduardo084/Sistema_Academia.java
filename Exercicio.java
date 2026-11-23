public class Exercicio {
    private int id;
    private int idTreino;
    private String nome;
    private String grupoMuscular;
    private int series;
    private int repeticoes;
    private double cargaSugerida;

    public Exercicio(
        int id,
        int idTreino,
        String nome,
        String grupoMuscular,
        int series,
        int repeticoes,
        double cargaSugerida
    ) {
        this.id = id;
        this.idTreino = idTreino;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.series = series;
        this.repeticoes = repeticoes;
        this.cargaSugerida = cargaSugerida;
    }

    public int getId() {
        return id;
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void atualizarCarga(double cargaSugerida) {
        this.cargaSugerida = cargaSugerida;
    }

    public String consultarExercicio() {
        return "Exercício " + id
            + " | " + nome
            + " | Grupo: " + grupoMuscular
            + " | Séries: " + series
            + " | Repetições: " + repeticoes
            + " | Carga sugerida (kg): " + cargaSugerida;
    }
}
