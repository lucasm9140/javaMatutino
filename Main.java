package Exercicio_Joo;
// Programa java orientado a objeto.


// Interface para autenticacao de funcionarios
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
    protected double salario;

public Funcionario(String nome, double salario) {
    this.nome = nome;
    this.salario = salario;
    }

    public abstract double calcularBonificação();

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }
}
// Subclasse Diretor
class Diretor extends Funcionario implements FuncionarioAutenticavel {
    public Diretor(String nome, double salario) {
        super(nome, salario);
    }

    public double calcularBonificação(){
        return salario * 0.50; //bonificação de 50%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("diretor") && senha.equals("1234");
    }

}
// abcd gerente 30%
// secretario 10% 1234
// eng abcd 20% 
class Gerente extends Funcionario implements FuncionarioAutenticavel {
    public Gerente(String nome, double salario) {
        super(nome, salario);
    }

    public double calcularBonificação(){
        return salario * 0.30; //bonificação de 30%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("gerente") && senha.equals("abcd");
    }

}

class Secretario extends Funcionario implements FuncionarioAutenticavel {
    public Secretario(String nome, double salario) {
        super(nome, salario);
    }

    public double calcularBonificação(){
        return salario * 0.10; //bonificação de 10%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("secretario") && senha.equals("1234");
    }

}

class Engenheiro extends Funcionario implements FuncionarioAutenticavel {
    public Engenheiro(String nome, double salario) {
        super(nome, salario);
    }

    public double calcularBonificação(){
        return salario * 0.20; //bonificação de 20%
    }

    public boolean autenticar(String login, String senha) {
        return login.equals("engenheiro") && senha.equals("abcd");
    }

}
// Classe Cliente que implementa ClienteAutenticavel
class Cliente implements ClienteAutenticavel {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public boolean autenticar(String login, String senha) {
        // Implementar lógica de autenticação para cliente
        return login.equals("cliente") && senha.equals("cliente123");
    }

    public String getNome() {
        return nome;
    }

}
// Classe principal 
public class Main {
    public static void main(String[] args) {
        //Criando funcionários
        Funcionario diretor = new Diretor("Carlos", 10000);
        Funcionario gerente = new Gerente("Ana", 8000);
        Funcionario secretario = new Secretario("Paulo", 4000);
        Funcionario engenheiro = new Engenheiro("Joao", 6000);

        // Criando cliente
        Cliente cliente = new Cliente("Marcos");

        // Teste de autenticação
        System.out.println("Autenticando Diretor: " + ((Diretor) diretor).autenticar("diretor", "1234"));
        System.out.println("Autenticando Gerente: " + ((Gerente) gerente).autenticar("gerente", "abcd"));
        System.out.println("Autenticando Secretario: " + ((Secretario) secretario).autenticar("secretario", "1234"));
        System.out.println("Autenticando Engenheiro: " + ((Engenheiro) engenheiro).autenticar("engenheiro", "abcd"));
        System.out.println("Autenticando Cliente: " + ((Cliente) cliente).autenticar("cliente", "cliente123"));

    }
}
// Refazer o mesmo codigo com as seguintes saídas
// informando os dados dos funcionarios que sao,
// nome, endereco, telefone, sexo, idade, funcao, estado civil.
// e referente o cliente informar nome, telefone, endereco, data de aniversario