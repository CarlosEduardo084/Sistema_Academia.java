import java.time.LocalDate;

public class Matricula {
    private int id;
    private int idAluno;
    private int idPlano;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean statusAtiva;

    public Matricula(
        int id,
        int idAluno,
        int idPlano,
        LocalDate dataInicio,
        int duracaoMeses
    ) {
        this.id = id;
        this.idAluno = idAluno;
        this.idPlano = idPlano;
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusMonths(duracaoMeses).minusDays(1);
        ativar();
    }

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void ativar() {
        statusAtiva = true;
    }

    public void cancelar() {
        statusAtiva = false;
    }

    public boolean consultarSituacao() {
        LocalDate hoje = LocalDate.now();

        return statusAtiva
            && !hoje.isBefore(dataInicio)
            && !hoje.isAfter(dataFim);
    }

    public String consultarDados() {
        String situacao;

        if (!statusAtiva) {
            situacao = "Cancelada";
        } else if (LocalDate.now().isBefore(dataInicio)) {
            situacao = "Agendada";
        } else if (consultarSituacao()) {
            situacao = "Ativa";
        } else {
            situacao = "Vencida";
        }

        return "Matrícula " + id
            + " | Aluno: " + idAluno
            + " | Plano: " + idPlano
            + " | Início: " + dataInicio
            + " | Fim: " + dataFim
            + " | Situação: " + situacao;
    }
}