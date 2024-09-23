import java.util.ArrayList;
import java.util.Scanner;

// Classe base para Cliente
abstract class Cliente {
    protected String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public abstract void mostrarDetalhes();
}

// Classe para Cliente Pessoa Física
class ClientePF extends Cliente {
    private String cpf;

    public ClientePF(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Cliente Pessoa Física:");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }
}

// Classe para Cliente Pessoa Jurídica
class ClientePJ extends Cliente {
    private String cnpj;

    public ClientePJ(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Cliente Pessoa Jurídica:");
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
    }
}

// Classe para Advogado
class Advogado {
    private String nome;
    private double honorarios;
    private String areaAtuacao;

    public Advogado(String nome, double honorarios, String areaAtuacao) {
        this.nome = nome;
        this.honorarios = honorarios;
        this.areaAtuacao = areaAtuacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public double getHonorarios() {
        return honorarios;
    }

    public void mostrarDetalhes() {
        System.out.println("Advogado:");
        System.out.println("Nome: " + nome);
        System.out.println("Honorários: R$ " + honorarios);
        System.out.println("Área de Atuação: " + areaAtuacao);
    }
}

// Classe para Processo
class Processo {
    private String id;
    private Cliente cliente;
    private Tribunal tribunal;
    private Juiz juiz;
    private String decisao;

    public Processo(String id, Cliente cliente, Tribunal tribunal, Juiz juiz, String decisao) {
        this.id = id;
        this.cliente = cliente;
        this.tribunal = tribunal;
        this.juiz = juiz;
        this.decisao = decisao;
    }

    public void mostrarDetalhes() {
        System.out.println("Processo ID: " + id);
        cliente.mostrarDetalhes();
        tribunal.mostrarDetalhes();
        juiz.mostrarDetalhes();
        System.out.println("Decisão Final: " + decisao);
    }
}

// Classe para Tribunal
class Tribunal {
    private String nome;

    public Tribunal(String nome) {
        this.nome = nome;
    }

    public void mostrarDetalhes() {
        System.out.println("Tribunal:");
        System.out.println("Nome: " + nome);
    }
}

// Classe para Juiz
class Juiz {
    private String nome;

    public Juiz(String nome) {
        this.nome = nome;
    }

    public void mostrarDetalhes() {
        System.out.println("Juiz:");
        System.out.println("Nome: " + nome);
    }
}

// Classe principal para executar o programa
public class advocaciaSistem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criar listas para armazenar advogados, clientes e processos
        ArrayList<Advogado> advogados = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Processo> processos = new ArrayList<>();
        ArrayList<Tribunal> tribunais = new ArrayList<>();
        ArrayList<Juiz> juizes = new ArrayList<>();

        // Adicionar um tribunal e um juiz para este exemplo
        System.out.println("Cadastro de Tribunal e Juiz");

        System.out.print("Informe o nome do tribunal: ");
        String nomeTribunal = scanner.nextLine();
        Tribunal tribunal = new Tribunal(nomeTribunal);
        tribunais.add(tribunal);

        System.out.print("Informe o nome do juiz: ");
        String nomeJuiz = scanner.nextLine();
        Juiz juiz = new Juiz(nomeJuiz);
        juizes.add(juiz);

        // Adicionar advogados
        System.out.println("Cadastro de Advogados");
        System.out.print("Quantos advogados deseja cadastrar? ");
        int numAdvogados = scanner.nextInt();
        scanner.nextLine(); // consumir o newline

        for (int i = 0; i < numAdvogados; i++) {
            System.out.print("Informe o nome do advogado: ");
            String nomeAdvogado = scanner.nextLine();
            System.out.print("Informe os honorários do advogado: ");
            double honorarios = scanner.nextDouble();
            scanner.nextLine(); // consumir o newline
            System.out.print("Informe a área de atuação do advogado: ");
            String areaAtuacao = scanner.nextLine();

            Advogado advogado = new Advogado(nomeAdvogado, honorarios, areaAtuacao);
            advogados.add(advogado);
        }

        // Adicionar clientes
        System.out.println("Cadastro de Clientes");
        System.out.print("Quantos clientes deseja cadastrar? ");
        int numClientes = scanner.nextInt();
        scanner.nextLine(); // consumir o newline

        for (int i = 0; i < numClientes; i++) {
            System.out.print("É Pessoa Física (1) ou Jurídica (2)? ");
            int tipoCliente = scanner.nextInt();
            scanner.nextLine(); // consumir o newline

            Cliente cliente = null;
            if (tipoCliente == 1) {
                System.out.print("Informe o nome do cliente: ");
                String nomeCliente = scanner.nextLine();
                System.out.print("Informe o CPF do cliente: ");
                String cpf = scanner.nextLine();
                cliente = new ClientePF(nomeCliente, cpf);
            } else if (tipoCliente == 2) {
                System.out.print("Informe o nome da empresa: ");
                String nomeEmpresa = scanner.nextLine();
                System.out.print("Informe o CNPJ da empresa: ");
                String cnpj = scanner.nextLine();
                cliente = new ClientePJ(nomeEmpresa, cnpj);
            }

            clientes.add(cliente);
        }

        // Adicionar processos
        System.out.println("Cadastro de Processos");
        System.out.print("Quantos processos deseja cadastrar? ");
        int numProcessos = scanner.nextInt();
        scanner.nextLine(); // consumir o newline

        for (int i = 0; i < numProcessos; i++) {
            System.out.print("Informe o ID do processo: ");
            String idProcesso = scanner.nextLine();
            System.out.print("Escolha o cliente pelo índice (0 a " + (clientes.size() - 1) + "): ");
            int clienteIndex = scanner.nextInt();
            scanner.nextLine(); // consumir o newline
            Cliente clienteEscolhido = clientes.get(clienteIndex);

            System.out.print("Escolha o tribunal pelo índice (0 a " + (tribunais.size() - 1) + "): ");
            int tribunalIndex = scanner.nextInt();
            scanner.nextLine(); // consumir o newline
            Tribunal tribunalEscolhido = tribunais.get(tribunalIndex);

            System.out.print("Escolha o juiz pelo índice (0 a " + (juizes.size() - 1) + "): ");
            int juizIndex = scanner.nextInt();
            scanner.nextLine(); // consumir o newline
            Juiz juizEscolhido = juizes.get(juizIndex);

            System.out.print("Informe a decisão final do processo: ");
            String decisao = scanner.nextLine();

            Processo processo = new Processo(idProcesso, clienteEscolhido, tribunalEscolhido, juizEscolhido, decisao);
            processos.add(processo);
        }

        // Mostrar detalhes
        System.out.println("\nDetalhes dos Advogados:");
        for (Advogado advogado : advogados) {
            advogado.mostrarDetalhes();
            System.out.println();
        }

        System.out.println("\nDetalhes dos Processos:");
        for (Processo processo : processos) {
            processo.mostrarDetalhes();
            System.out.println();
        }

        scanner.close();
    }
}
