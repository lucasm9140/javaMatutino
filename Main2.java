

// Interface para autenticação de funcionários
interface FuncionarioAutenticavel {
    boolean autenticar(String login, String senha);
}

// Interface para autenticação de clientes
interface ClienteAutenticavel {
    boolean autenticar(String login, String senha);
}

// Superclasse Funcionario
abstract class Funcionario {
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String sexo;
    protected int idade;
    protected String funcao;
    protected String estadoCivil;
    protected double salario;

    public Funcionario(String nome, String endereco, String telefone, String sexo, int idade, String funcao, String estadoCivil, double salario) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.sexo = sexo;
        this.idade = idade;
        this.funcao = funcao;
        this.estadoCivil = estadoCivil;
        this.salario = salario;
    }

    public abstract double calcularBonificação();

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Sexo: " + sexo);
        System.out.println("Idade: " + idade);
        System.out.println("Função: " + funcao);
        System.out.println("Estado Civil: " + estadoCivil);
        System.out.println("Salário: " + salario);
        System.out.println("Bonificação: " + calcularBonificação());
        System.out.println();
    }
}

// Subclasse Diretor
class Diretor extends Funcionario implements FuncionarioAutenticavel {
    public Diretor(String nome, String endereco, String telefone, String sexo, int idade, String funcao, String estadoCivil, double salario) {
        super(nome, endereco, telefone, sexo, idade, funcao, estadoCivil, salario);
    }

    public double calcularBonificação() {
        return salario * 0.50; // Bonificação de 50%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("diretor") && senha.equals("1234");
    }
}

// Subclasse Gerente
class Gerente extends Funcionario implements FuncionarioAutenticavel {
    public Gerente(String nome, String endereco, String telefone, String sexo, int idade, String funcao, String estadoCivil, double salario) {
        super(nome, endereco, telefone, sexo, idade, funcao, estadoCivil, salario);
    }

    public double calcularBonificação() {
        return salario * 0.30; // Bonificação de 30%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("gerente") && senha.equals("abcd");
    }
}

// Subclasse Secretario
class Secretario extends Funcionario implements FuncionarioAutenticavel {
    public Secretario(String nome, String endereco, String telefone, String sexo, int idade, String funcao, String estadoCivil, double salario) {
        super(nome, endereco, telefone, sexo, idade, funcao, estadoCivil, salario);
    }

    public double calcularBonificação() {
        return salario * 0.10; // Bonificação de 10%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("secretario") && senha.equals("1234");
    }
}

// Subclasse Engenheiro
class Engenheiro extends Funcionario implements FuncionarioAutenticavel {
    public Engenheiro(String nome, String endereco, String telefone, String sexo, int idade, String funcao, String estadoCivil, double salario) {
        super(nome, endereco, telefone, sexo, idade, funcao, estadoCivil, salario);
    }

    public double calcularBonificação() {
        return salario * 0.20; // Bonificação de 20%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("engenheiro") && senha.equals("abcd");
    }
}

// Classe Cliente que implementa ClienteAutenticavel
class Cliente implements ClienteAutenticavel {
    private String nome;
    private String telefone;
    private String endereco;
    private String dataAniversario;

    public Cliente(String nome, String telefone, String endereco, String dataAniversario) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataAniversario = dataAniversario;
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("cliente") && senha.equals("cliente123");
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + endereco);
        System.out.println("Data de Aniversário: " + dataAniversario);
        System.out.println();
    }
}

// Classe principal
public class Main2 {
    public static void main(String[] args) {
        // Criando funcionários
        Funcionario diretor = new Diretor("Carlos", "Rua A, 123", "1234-5678", "Masculino", 50, "Diretor", "Casado", 10000);
        Funcionario gerente = new Gerente("Ana", "Rua B, 456", "2345-6789", "Feminino", 40, "Gerente", "Solteira", 8000);
        Funcionario secretario = new Secretario("Paulo", "Rua C, 789", "3456-7890", "Masculino", 35, "Secretário", "Divorciado", 4000);
        Funcionario engenheiro = new Engenheiro("Joao", "Rua D, 101", "4567-8901", "Masculino", 30, "Engenheiro", "Casado", 6000);

        // Criando cliente
        Cliente cliente = new Cliente("Marcos", "5678-1234", "Rua E, 202", "15/08/1985");

        // Exibindo informações dos funcionários
        System.out.println("Informações do Diretor:");
        diretor.exibirInformacoes();
        System.out.println("Informações do Gerente:");
        gerente.exibirInformacoes();
        System.out.println("Informações do Secretário:");
        secretario.exibirInformacoes();
        System.out.println("Informações do Engenheiro:");
        engenheiro.exibirInformacoes();

        // Exibindo informações do cliente
        System.out.println("Informações do Cliente:");
        cliente.exibirInformacoes();

        // Teste de autenticação
        System.out.println("Autenticando Diretor: " + ((Diretor) diretor).autenticar("diretor", "1234"));
        System.out.println("Autenticando Gerente: " + ((Gerente) gerente).autenticar("gerente", "abcd"));
        System.out.println("Autenticando Secretário: " + ((Secretario) secretario).autenticar("secretario", "1234"));
        System.out.println("Autenticando Engenheiro: " + ((Engenheiro) engenheiro).autenticar("engenheiro", "abcd"));
        System.out.println("Autenticando Cliente: " + ((Cliente) cliente).autenticar("cliente", "cliente123"));
    }
}
