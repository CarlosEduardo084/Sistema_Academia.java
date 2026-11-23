import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SistemaAcademia {
    private Scanner entrada = new Scanner(System.in);

    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Instrutor> instrutores = new ArrayList<>();
    private ArrayList<Plano> planos = new ArrayList<>();
    private ArrayList<Matricula> matriculas = new ArrayList<>();
    private ArrayList<Treino> treinos = new ArrayList<>();

    private int proximoUsuario = 1;
    private int proximoPlano = 1;
    private int proximaMatricula = 1;
    private int proximoTreino = 1;
    private int proximoExercicio = 1;

    public void iniciar() {
        try {
            System.out.println(
                "SISTEMA DA ACADEMIA - dados apenas em memória"
            );

            System.out.println(
                "Cadastre o primeiro instrutor administrador."
            );

            cadastrarPrimeiroAdministrador();

            String opcao = "";

            while (!opcao.equals("0")) {
                System.out.println("\n1 - Login como aluno");
                System.out.println("2 - Login como instrutor");
                System.out.println("0 - Sair");

                opcao = texto("Opção: ");

                if (opcao.equals("1")) {
                    Aluno aluno = loginAluno();

                    if (aluno != null) {
                        menuAluno(aluno);
                    }
                } else if (opcao.equals("2")) {
                    Instrutor instrutor = loginInstrutor();

                    if (instrutor != null) {
                        menuInstrutor(instrutor);
                    }
                } else if (!opcao.equals("0")) {
                    System.out.println("Opção inválida.");
                }
            }

            System.out.println(
                "Sistema encerrado. Os dados desta sessão não foram salvos."
            );
        } catch (NoSuchElementException e) {
            System.out.println(
                "\nEntrada encerrada. Os dados desta sessão não foram salvos."
            );
        }
    }

    private void cadastrarPrimeiroAdministrador() {
        String nome = texto("Nome: ");
        String cpf = novoCpf();
        String senha = texto("Senha: ");
        String turno = texto("Turno: ");

        Instrutor administrador = new Instrutor(
            proximoUsuario++,
            nome,
            cpf,
            senha,
            turno,
            true
        );

        instrutores.add(administrador);

        System.out.println(
            "Administrador cadastrado. Use seu CPF e senha para entrar."
        );
    }

    private Aluno loginAluno() {
        String cpf = texto("CPF: ");
        String senha = texto("Senha: ");

        for (Aluno aluno : alunos) {
            if (aluno.autenticar(cpf, senha)) {
                return aluno;
            }
        }

        System.out.println("CPF ou senha incorretos para este perfil.");
        return null;
    }

    private Instrutor loginInstrutor() {
        String cpf = texto("CPF: ");
        String senha = texto("Senha: ");

        for (Instrutor instrutor : instrutores) {
            if (instrutor.autenticar(cpf, senha)) {
                return instrutor;
            }
        }

        System.out.println("CPF ou senha incorretos para este perfil.");
        return null;
    }

    private void menuAluno(Aluno aluno) {
        String opcao = "";

        while (!opcao.equals("0")) {
            System.out.println("\nALUNO: " + aluno.getNome());
            System.out.println("1 - Meus dados");
            System.out.println("2 - Atualizar telefone");
            System.out.println("3 - Minhas matrículas");
            System.out.println("4 - Meus treinos");
            System.out.println("0 - Encerrar sessão");

            opcao = texto("Opção: ");

            switch (opcao) {
                case "1":
                    System.out.println(aluno.consultarDados());
                    break;

                case "2":
                    aluno.atualizarDados(texto("Novo telefone: "));
                    System.out.println("Telefone atualizado.");
                    break;

                case "3":
                    listarMatriculas(aluno.getId());
                    break;

                case "4":
                    listarTreinosAluno(aluno.getId());
                    break;

                case "0":
                    System.out.println("Sessão encerrada.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuInstrutor(Instrutor instrutor) {
        String opcao = "";

        while (!opcao.equals("0")) {
            System.out.println("\nINSTRUTOR: " + instrutor.getNome());
            System.out.println("1 - Meus dados");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Criar treino");
            System.out.println("4 - Consultar treinos");
            System.out.println("5 - Adicionar exercício");
            System.out.println("6 - Remover exercício");
            System.out.println("7 - Atualizar carga");

            if (instrutor.isAdministrador()) {
                System.out.println("8 - Cadastrar aluno");
                System.out.println("9 - Atualizar telefone de aluno");
                System.out.println("10 - Cadastrar instrutor");
                System.out.println("11 - Listar instrutores");
                System.out.println("12 - Cadastrar plano");
                System.out.println("13 - Consultar planos");
                System.out.println("14 - Atualizar plano");
                System.out.println("15 - Fazer matrícula");
                System.out.println("16 - Consultar matrículas");
                System.out.println("17 - Cancelar matrícula");
                System.out.println("18 - Ativar matrícula");
            }

            System.out.println("0 - Encerrar sessão");
            opcao = texto("Opção: ");

            try {
                switch (opcao) {
                    case "1":
                        System.out.println(instrutor.consultarDados());
                        break;

                    case "2":
                        listarAlunos();
                        break;

                    case "3":
                        criarTreino(instrutor);
                        break;

                    case "4":
                        listarTreinosInstrutor(instrutor);
                        break;

                    case "5":
                        adicionarExercicio(instrutor);
                        break;

                    case "6":
                        removerExercicio(instrutor);
                        break;

                    case "7":
                        atualizarCarga(instrutor);
                        break;

                    case "8":
                        cadastrarAluno(instrutor);
                        break;

                    case "9":
                        atualizarAluno(instrutor);
                        break;

                    case "10":
                        cadastrarInstrutor(instrutor);
                        break;

                    case "11":
                        exigirAdministrador(instrutor);

                        for (Instrutor item : instrutores) {
                            System.out.println(item.consultarDados());
                        }
                        break;

                    case "12":
                        cadastrarPlano(instrutor);
                        break;

                    case "13":
                        exigirAdministrador(instrutor);
                        listarPlanos();
                        break;

                    case "14":
                        atualizarPlano(instrutor);
                        break;

                    case "15":
                        matricular(instrutor);
                        break;

                    case "16":
                        exigirAdministrador(instrutor);
                        listarMatriculas(0);
                        break;

                    case "17":
                        alterarMatricula(instrutor, false);
                        break;

                    case "18":
                        alterarMatricula(instrutor, true);
                        break;

                    case "0":
                        System.out.println("Sessão encerrada.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void exigirAdministrador(Instrutor instrutor) {
        if (!instrutor.isAdministrador()) {
            throw new IllegalArgumentException(
                "Acesso permitido apenas ao administrador."
            );
        }
    }

    private void cadastrarAluno(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        String nome = texto("Nome: ");
        String cpf = novoCpf();
        String senha = texto("Senha: ");
        LocalDate nascimento = data("Nascimento (AAAA-MM-DD): ");

        while (nascimento.isAfter(LocalDate.now())) {
            System.out.println("O nascimento não pode estar no futuro.");
            nascimento = data("Nascimento (AAAA-MM-DD): ");
        }

        String telefone = texto("Telefone: ");

        Aluno aluno = new Aluno(
            proximoUsuario++,
            nome,
            cpf,
            senha,
            nascimento,
            telefone
        );

        alunos.add(aluno);
        System.out.println("Aluno cadastrado. ID: " + aluno.getId());
    }

    private void atualizarAluno(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        Aluno aluno = buscarAluno(inteiro("ID do aluno: "));
        aluno.atualizarDados(texto("Novo telefone: "));

        System.out.println("Telefone atualizado.");
    }

    private void cadastrarInstrutor(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        String nome = texto("Nome: ");
        String cpf = novoCpf();
        String senha = texto("Senha: ");
        String turno = texto("Turno: ");

        Instrutor novo = new Instrutor(
            proximoUsuario++,
            nome,
            cpf,
            senha,
            turno,
            false
        );

        instrutores.add(novo);
        System.out.println("Instrutor comum cadastrado.");
    }

    private void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        }

        for (Aluno aluno : alunos) {
            System.out.println(aluno.consultarDados());
        }
    }

    private void cadastrarPlano(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        String nome = texto("Nome do plano: ");
        int meses = inteiro("Duração em meses: ");
        double valor = decimal("Valor total do plano (R$): ");

        Plano plano = new Plano(
            proximoPlano++,
            nome,
            meses,
            valor
        );

        planos.add(plano);
        System.out.println("Plano cadastrado. ID: " + plano.getId());
    }

    private void listarPlanos() {
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
        }

        for (Plano plano : planos) {
            System.out.println(plano.consultarDados());
        }
    }

    private void atualizarPlano(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        Plano plano = buscarPlano(inteiro("ID do plano: "));
        String nome = texto("Novo nome: ");
        int meses = inteiro("Nova duração em meses: ");
        double valor = decimal("Novo valor total (R$): ");

        plano.atualizarPlano(nome, meses, valor);

        System.out.println(
            "Plano atualizado. As datas das matrículas existentes foram preservadas."
        );
    }

    private void matricular(Instrutor instrutor) {
        exigirAdministrador(instrutor);

        Aluno aluno = buscarAluno(inteiro("ID do aluno: "));
        Plano plano = buscarPlano(inteiro("ID do plano: "));
        LocalDate inicio = data("Início (AAAA-MM-DD): ");

        Matricula matricula = new Matricula(
            proximaMatricula,
            aluno.getId(),
            plano.getId(),
            inicio,
            plano.getDuracaoMeses()
        );

        matriculas.add(matricula);
        proximaMatricula++;

        System.out.println(matricula.consultarDados());
    }

    private void listarMatriculas(int idAluno) {
        boolean encontrou = false;

        for (Matricula matricula : matriculas) {
            if (idAluno == 0 || matricula.getIdAluno() == idAluno) {
                System.out.println(matricula.consultarDados());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma matrícula encontrada.");
        }
    }

    private void alterarMatricula(
        Instrutor instrutor,
        boolean ativar
    ) {
        exigirAdministrador(instrutor);

        int id = inteiro("ID da matrícula: ");

        for (Matricula matricula : matriculas) {
            if (matricula.getId() == id) {
                if (ativar) {
                    matricula.ativar();
                } else {
                    matricula.cancelar();
                }

                System.out.println(matricula.consultarDados());
                return;
            }
        }

        throw new IllegalArgumentException("Matrícula não encontrada.");
    }

    private void criarTreino(Instrutor instrutor) {
        Aluno aluno = buscarAluno(inteiro("ID do aluno: "));
        String objetivo = texto("Objetivo: ");

        Treino treino = instrutor.criarTreino(
            proximoTreino,
            aluno,
            objetivo
        );

        System.out.println(
            "Cadastre o primeiro exercício. Todo treino precisa de pelo menos um."
        );

        Exercicio exercicio = lerExercicio(treino.getId());

        treino.adicionarExercicio(exercicio);
        treinos.add(treino);
        proximoTreino++;

        System.out.println("Treino cadastrado. ID: " + treino.getId());
    }

    private Exercicio lerExercicio(int idTreino) {
        String nome = texto("Nome do exercício: ");
        String grupo = texto("Grupo muscular: ");
        int series = inteiro("Séries: ");
        int repeticoes = inteiro("Repetições: ");

        double carga = decimal(
            "Carga sugerida em kg (0 para sem carga): "
        );

        return new Exercicio(
            proximoExercicio++,
            idTreino,
            nome,
            grupo,
            series,
            repeticoes,
            carga
        );
    }

    private void mostrarTreino(Treino treino) {
        System.out.println(treino.consultarDados());

        for (Exercicio exercicio : treino.consultarExercicios()) {
            System.out.println("  " + exercicio.consultarExercicio());
        }
    }

    private void listarTreinosAluno(int idAluno) {
        boolean encontrou = false;

        for (Treino treino : treinos) {
            if (treino.getIdAluno() == idAluno) {
                mostrarTreino(treino);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum treino encontrado.");
        }
    }

    private void listarTreinosInstrutor(Instrutor instrutor) {
        boolean encontrou = false;

        for (Treino treino : treinos) {
            if (
                instrutor.isAdministrador()
                || treino.getIdInstrutor() == instrutor.getId()
            ) {
                mostrarTreino(treino);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum treino encontrado.");
        }
    }

    private Treino selecionarTreino(Instrutor instrutor) {
        int id = inteiro("ID do treino: ");

        for (Treino treino : treinos) {
            if (treino.getId() == id) {
                if (
                    !instrutor.isAdministrador()
                    && treino.getIdInstrutor() != instrutor.getId()
                ) {
                    throw new IllegalArgumentException(
                        "Você só pode alterar treinos que criou."
                    );
                }

                return treino;
            }
        }

        throw new IllegalArgumentException("Treino não encontrado.");
    }

    private Exercicio selecionarExercicio(Treino treino) {
        int id = inteiro("ID do exercício: ");

        for (Exercicio exercicio : treino.consultarExercicios()) {
            if (exercicio.getId() == id) {
                return exercicio;
            }
        }

        throw new IllegalArgumentException(
            "Exercício não encontrado neste treino."
        );
    }

    private void adicionarExercicio(Instrutor instrutor) {
        Treino treino = selecionarTreino(instrutor);

        treino.adicionarExercicio(
            lerExercicio(treino.getId())
        );

        System.out.println("Exercício adicionado.");
    }

    private void removerExercicio(Instrutor instrutor) {
        Treino treino = selecionarTreino(instrutor);
        Exercicio exercicio = selecionarExercicio(treino);

        if (treino.removerExercicio(exercicio)) {
            System.out.println("Exercício removido.");
        } else {
            System.out.println(
                "Não é possível remover o último exercício do treino."
            );
        }
    }

    private void atualizarCarga(Instrutor instrutor) {
        Treino treino = selecionarTreino(instrutor);
        Exercicio exercicio = selecionarExercicio(treino);

        exercicio.atualizarCarga(
            decimal("Nova carga em kg: ")
        );

        System.out.println("Carga atualizada.");
    }

    private Aluno buscarAluno(int id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }

        throw new IllegalArgumentException("Aluno não encontrado.");
    }

    private Plano buscarPlano(int id) {
        for (Plano plano : planos) {
            if (plano.getId() == id) {
                return plano;
            }
        }

        throw new IllegalArgumentException("Plano não encontrado.");
    }

    private boolean cpfCadastrado(String cpf) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }

        for (Instrutor instrutor : instrutores) {
            if (instrutor.getCpf().equals(cpf)) {
                return true;
            }
        }

        return false;
    }

    private String novoCpf() {
        while (true) {
            String cpf = texto(
                "CPF (11 números, sem pontuação): "
            );

            if (!cpf.matches("[0-9]{11}")) {
                System.out.println("Informe exatamente 11 números.");
            } else if (cpfCadastrado(cpf)) {
                System.out.println("CPF já cadastrado.");
            } else {
                return cpf;
            }
        }
    }

    private String texto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = entrada.nextLine().trim();

            if (!valor.isEmpty()) {
                return valor;
            }

            System.out.println("O campo não pode ficar vazio.");
        }
    }

    private int inteiro(String mensagem) {
        while (true) {
            try {
                int valor = Integer.parseInt(texto(mensagem));

                if (valor > 0) {
                    return valor;
                }

                System.out.println(
                    "Informe um número inteiro maior que zero."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                    "Entrada inválida. Digite um número inteiro."
                );
            }
        }
    }

    private double decimal(String mensagem) {
        while (true) {
            try {
                String digitado = texto(mensagem).replace(',', '.');
                double valor = Double.parseDouble(digitado);

                if (Double.isFinite(valor) && valor >= 0) {
                    return valor;
                }

                System.out.println(
                    "Informe um valor válido igual ou maior que zero."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                    "Entrada inválida. Exemplo: 89,90."
                );
            }
        }
    }

    private LocalDate data(String mensagem) {
        while (true) {
            try {
                LocalDate valor = LocalDate.parse(texto(mensagem));

                if (
                    valor.getYear() >= 1900
                    && valor.getYear() <= 9999
                ) {
                    return valor;
                }

                System.out.println(
                    "Use um ano entre 1900 e 9999."
                );
            } catch (DateTimeParseException e) {
                System.out.println(
                    "Data inválida. Use AAAA-MM-DD, por exemplo 2000-05-20."
                );
            }
        }
    }}
