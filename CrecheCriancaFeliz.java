import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

// Classe abstrata Pessoa
abstract class Pessoa {
    protected String nome;
    protected String cpf;  // No caso das crianças, será o CPF do responsável
    protected LocalDate dataNascimento;

    // Construtor para inicializar os atributos da Pessoa
    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Método para calcular a idade da pessoa com base na data de nascimento
    public int calcularIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    // Métodos abstratos a serem implementados nas subclasses
    public abstract String getEndereco();
    public abstract String getTelefone();
    public abstract void exibirDetalhes();
}

// Classe Crianca que herda de Pessoa
class Crianca extends Pessoa {
    private String enderecoResponsavel;
    private String telefoneResponsavel;
    private String turma;
    private String horario;

    // Construtor da Crianca, que chama o construtor da superclasse Pessoa
    public Crianca(String nome, String cpf, LocalDate dataNascimento, String enderecoResponsavel, String telefoneResponsavel, String turma, String horario) {
        super(nome, cpf, dataNascimento);
        this.enderecoResponsavel = enderecoResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.turma = turma;
        this.horario = horario;
    }

    @Override
    public String getEndereco() {
        return enderecoResponsavel;
    }

    @Override
    public String getTelefone() {
        return telefoneResponsavel;
    }

    public String getTurma() {
        return turma;
    }

    public String getHorario() {
        return horario;
    }

    // Implementação do método abstrato para exibir os detalhes da criança
    @Override
    public void exibirDetalhes() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Nome da criança         : " + nome);
        System.out.println("Idade                   : " + calcularIdade() + " anos");
        System.out.println("CPF do responsável      : " + cpf);
        System.out.println("Endereço do responsável : " + enderecoResponsavel);
        System.out.println("Telefone do responsável : " + telefoneResponsavel);
        System.out.println("Turma                   : " + turma);
        System.out.println("Horário                 : " + horario);
        System.out.println("------------------------------------------------------------------------------");
    }
}

// Classe Professor que herda de Pessoa
class Professor extends Pessoa {
    private String endereco;
    private String telefone;
    private String disciplina;
    private String[] turmas;

    // Construtor da classe Professor
    public Professor(String nome, String cpf, LocalDate dataNascimento, String endereco, String telefone, String disciplina, String[] turmas) {
        super(nome, cpf, dataNascimento);
        this.endereco = endereco;
        this.telefone = telefone;
        this.disciplina = disciplina;
        this.turmas = turmas;
    }

    @Override
    public String getEndereco() {
        return endereco;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String[] getTurmas() {
        return turmas;
    }

    // Implementação do método abstrato para exibir os detalhes do professor
    @Override
    public void exibirDetalhes() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Nome do professor      : " + nome);
        System.out.println("Idade                  : " + calcularIdade() + " anos");
        System.out.println("CPF                    : " + cpf);
        System.out.println("Endereço               : " + endereco);
        System.out.println("Telefone               : " + telefone);
        System.out.println("Disciplina             : " + disciplina);
        System.out.print("Turmas                 : ");
        for (int i = 0; i < turmas.length; i++) {
            System.out.print(turmas[i]);
            if (i < turmas.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n------------------------------------------------------------------------------");
    }
}

// Classe Turma para organizar as turmas da creche
class Turma {
    private String nome;
    private int idadeMinima;
    private int idadeMaxima;
    private List<Professor> professores;
    private List<Crianca> criancas;

    // Construtor da classe Turma
    public Turma(String nome, int idadeMinima, int idadeMaxima) {
        this.nome = nome;
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = idadeMaxima;
        this.professores = new ArrayList<>();
        this.criancas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Crianca> getCriancas() {
        return criancas;
    }

    // Adiciona professores à turma, limitando a dois
    public void adicionarProfessor(Professor professor) {
        if (professores.size() < 2) {
            professores.add(professor);
            System.out.println("Professor " + professor.getNome() + " adicionado à turma " + nome + ".");
        } else {
            System.out.println("Turma " + nome + " já possui dois professores.");
        }
    }

    // Adiciona crianças à turma, verificando se a idade está dentro da faixa etária da turma
    public void adicionarCrianca(Crianca crianca) {
        int idade = crianca.calcularIdade();
        if (idade >= idadeMinima && idade <= idadeMaxima) {
            criancas.add(crianca);
            System.out.println("Criança " + crianca.getNome() + " adicionada à turma " + nome + ".");
        } else {
            System.out.println("A criança " + crianca.getNome() + " não tem idade para a turma " + nome + ".");
        }
    }

    // Exibe os detalhes da turma, incluindo professores e crianças
    public void exibirDetalhes() {
        System.out.println("** Detalhes da Turma: " + nome + " **");
        System.out.println("Faixa Etária: " + idadeMinima + " a " + idadeMaxima + " anos\n");

        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado para esta turma.\n");
        } else {
            System.out.println("Professores:");
            for (Professor professor : professores) {
                System.out.println(" - " + professor.getNome() + " (" + professor.getDisciplina() + ")");
            }
            System.out.println();
        }

        if (criancas.isEmpty()) {
            System.out.println("Nenhuma criança cadastrada para esta turma.\n");
        } else {
            System.out.println("Crianças:");
            for (Crianca crianca : criancas) {
                System.out.println(" - " + crianca.getNome() + " (" + crianca.getHorario() + ")");
            }
            System.out.println();
        }
    }
}

// Classe Creche que gerencia as crianças, professores e turmas
class Creche {
    private List<Crianca> criancas;
    private List<Professor> professores;
    private List<Turma> turmas;

    // Construtor da classe Creche
    public Creche() {
        this.criancas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    // Cadastra uma criança na creche
    public void cadastrarCrianca(Crianca crianca) {
        criancas.add(crianca);
    }

    // Cadastra um professor na creche
    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
    }

    // Cria uma turma na creche
    public void criarTurma(Turma turma) {
        turmas.add(turma);
    }

    // Gera um relatório de crianças por turma
    public void gerarRelatorioCriançasPorTurma() {
        System.out.println("\n================== Relatório de Crianças por Turma ==================\n");
        for (Turma turma : turmas) {
            System.out.println("### Turma: " + turma.getNome() + " ###\n");
            turma.exibirDetalhes();
        }
        System.out.println("=======================================================================\n");
    }

    // Gera um relatório de crianças por horário (integral ou meio período)
    public void gerarRelatorioCriançasPorHorario(String horario) {
        System.out.println("\n================== Relatório de Crianças por Horário ==================\n");
        System.out.println("Horário Selecionado: " + horario + "\n");
        for (Crianca crianca : criancas) {
            if (crianca.getHorario().equalsIgnoreCase(horario)) {
                crianca.exibirDetalhes();
            }
        }
        System.out.println("==========================================================================\n");
    }

    // Gera um relatório de professores por turma
    public void gerarRelatorioProfessoresPorTurma() {
        System.out.println("\n================== Relatório de Professores por Turma ==================\n");
        for (Turma turma : turmas) {
            System.out.println("### Turma: " + turma.getNome() + " ###\n");
            System.out.println("Professores Responsáveis:");
            for (Professor professor : turma.getProfessores()) {
                professor.exibirDetalhes(); // Aqui deve chamar o método correto
            }
            System.out.println();
        }
        System.out.println("==========================================================================\n");
    }
    
}

// Classe Main para rodar o sistema
public class CrecheCriancaFeliz {
    public static void main(String[] args) {
        Creche creche = new Creche();

        // Cadastrando Professores
        Professor professor1 = new Professor("Ana Souza", "12345678900", LocalDate.of(1985, 3, 20), "Rua X, 123", "1234-5678", "Educação Infantil", new String[]{"Maternal"});
        Professor professor2 = new Professor("Carlos Lima", "98765432100", LocalDate.of(1980, 7, 15), "Rua Y, 456", "9876-5432", "Música", new String[]{"Maternal", "Pré-1"});
        Professor professor3 = new Professor("Marcela Oliveira", "11229933345", LocalDate.of(1982, 9, 10), "Rua A, 567","9888-4433", "Artes", new String[]{"Pré-1", "Pré-2"});
        Professor professor4 = new Professor("Anderson Santos", "33344422235", LocalDate.of(1970, 9, 19), "Rua Z, 199", "9874-3121", "Educação Infantil", new String[]{"Pré-1", "Pré-2"});
        Professor professor5 = new Professor("Andressa Gomes", "21236442885", LocalDate.of(1978, 2, 1), "Rua ABC, 49", "9534-3729", "Música", new String[]{"Pré-2"});
        Professor professor6 = new Professor("Adriano Silva", "99276443845", LocalDate.of(1988, 7, 9), "Rua AC, 439", "5584-1729", "Artes", new String[]{"Maternal"});
        
        creche.cadastrarProfessor(professor1); 
        creche.cadastrarProfessor(professor2);
        creche.cadastrarProfessor(professor3);
        creche.cadastrarProfessor(professor4);
        creche.cadastrarProfessor(professor5);
        creche.cadastrarProfessor(professor6);

        // Criando Turmas
        Turma maternal = new Turma("Maternal", 2, 4);
        maternal.adicionarProfessor(professor1);
        maternal.adicionarProfessor(professor2);
        creche.criarTurma(maternal); // Adiciona a turma na lista de turmas da creche

        Turma pre1 = new Turma("Pré-1", 3, 5);
        pre1.adicionarProfessor(professor2);
        pre1.adicionarProfessor(professor3);
        creche.criarTurma(pre1); // Adiciona a turma Pré-1 à creche

        Turma pre2 = new Turma("Pré-2", 4, 8);
        pre2.adicionarProfessor(professor3);
        pre2.adicionarProfessor(professor4);
        creche.criarTurma(pre2); // Adiciona a turma Pré-2 à creche

        // Cadastrando Crianças
        Crianca crianca1 = new Crianca("João Silva", "11122233344", LocalDate.of(2020, 5, 10), "Rua A, 123", "12345-6789", "Maternal", "Integral");
        Crianca crianca2 = new Crianca("Maria Oliveira", "22233344455", LocalDate.of(2021, 11, 2), "Rua B, 456", "98765-4321", "Maternal", "Meio Período");
        Crianca crianca3 = new Crianca("Maria Eduarda", "52527344625", LocalDate.of(2019, 11, 5), "Rua D, 426", "98765-4321", "Pré-1", "Meio Período");
        Crianca crianca4 = new Crianca("Jorge Henrique", "11236346555", LocalDate.of(2016, 11, 9), "Rua C, 656", "98965-7621", "Pré-2", "Integral");
        Crianca crianca5 = new Crianca("Walisson Barbosa", "47233349405", LocalDate.of(2019, 11, 8), "Rua B, 416", "98786-4321", "Pré-1", "Meio Período");
        Crianca crianca6 = new Crianca("Pedro Pedreira", "27588349405", LocalDate.of(2017, 11, 6), "Rua M, 716", "99784-2311", "Pré-2", "Integral");

        creche.cadastrarCrianca(crianca1);
        creche.cadastrarCrianca(crianca2);
        creche.cadastrarCrianca(crianca3);
        creche.cadastrarCrianca(crianca4);
        creche.cadastrarCrianca(crianca5);
        creche.cadastrarCrianca(crianca6);

        maternal.adicionarCrianca(crianca1);
        maternal.adicionarCrianca(crianca2);

        pre1.adicionarCrianca(crianca3);
        pre1.adicionarCrianca(crianca5);

        pre2.adicionarCrianca(crianca4);
        pre2.adicionarCrianca(crianca6);

        // Relatórios
        creche.gerarRelatorioCriançasPorTurma();
        creche.gerarRelatorioCriançasPorHorario("Integral");
        creche.gerarRelatorioCriançasPorHorario("Meio Período");
        creche.gerarRelatorioProfessoresPorTurma();
    }
}
