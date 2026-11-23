public class Plano {
    private int id;
    private String nome;
    private int duracaoMeses;
    private double valor;

    public Plano(
        int id,
        String nome,
        int duracaoMeses,
        double valor
    ) {
        this.id = id;
        atualizarPlano(nome, duracaoMeses, valor);
    }

    public int getId() {
        return id;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void atualizarPlano(
        String nome,
        int duracaoMeses,
        double valor
    ) {
        this.nome = nome;
        this.duracaoMeses = duracaoMeses;
        this.valor = valor;
    }

    public String consultarDados() {
        return "Plano " + id
            + " | " + nome
            + " | Duração: " + duracaoMeses + " mês(es)"
            + " | Valor total: R$ " + String.format("%.2f", valor);
    }
}