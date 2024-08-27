//Aluno: Lucas Matheus
//Instituição/curso: Senai - Técnico em desenvolvimento de sistemas
//Prof: Kleber
/******************************** Exercicio ********************************* */
//Programa java que contenha interface, classes abstratas e na
//classe principal seja tratada o funcionamento de
//uma padaria. Sendo que a padaria deve ter em seu sistema de caixa
//os valores de seus produtos e o registro de pedidos de seus clientes.
//para itens especiais como por exemplo bolos de aniversario, pizzas
//e salgados para festas. O seu cod. deve mostrar na saida os valores
//dos produtos adquiridos considerando produtos convencionais como
//por exemplo: pao de sal, de queijo, biscoito doces e salgados,
//totelex, e que cada um tenha seu respectivo valor. também deve constexpros itens
//bebidas, como por exemplo: cafe expresso, com leite, leite quente, sucos em geral
//refrigerantes em geral, e outras bebidas.
/*********************************************************************************** */
import java.io.BufferedReader;//É usado para ler caracteres de qualquer tipo de fluxo de entrada de caracteres (String, Arquivos , etc.)
import java.io.BufferedWriter;//grava texto em um fluxo de saída de caracteres, armazenando caracteres em buffer para permitir a gravação eficiente de caracteres únicos, matrizes e strings.
import java.io.FileReader;//Pode ser usado apenas para ler arquivos
import java.io.FileWriter;//Classe de conveniência para escrever arquivos de caracteres.
import java.io.IOException;//lança o IOException sempre que uma operação de entrada ou saída falha ou é interpretada
import java.util.ArrayList;//é um array redimensionável ele pode receber mais itens enquanto o arrey tem que receber um total de intens.
                        //(ArrayList vai implementar os métodos de List e pode ter métodos mais específicos que List não vai ter, aí você escolhe o que melhor se aplica a sua necessidade.)
import java.util.HashMap;// permite que você associe dados (valores) a identificadores (chaves) de maneira eficiente, permitindo recuperação rápida dos valores com base nas chaves.
import java.util.List;//List é uma interface e ArrayList é uma classe que implementa List. 
import java.util.Map;//objetos “Map” confiam seus dados em um algoritmo hash (hash code). Esse algoritmo transforma uma grande quantidade de dados em uma pequena quantidade de informações
import java.util.Scanner;//Scanner para receber a entrada do usuario


// Interface para definir a estrutura dos produtos da padaria.
interface Produto {
    double getPreco(); // Método para obter o preço do produto
    String getDescricao(); // Método para obter a descrição do produto
}

// Classe abstrata para produtos básicos
abstract class ProdutoBasic implements Produto {
    protected String descricao; // descrição do produto
    protected double preco; // Preço do produto

    // Construtor para inicializar a descrição e o preço
    public ProdutoBasic(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}

// Classe para representar um tipo de pão
class Pao extends ProdutoBasic {
    public Pao(String tipo) {
        super(tipo, 2.50); // preço fixo para todos os pães
    }
}

// Classe para representar um bolo
class Bolo extends ProdutoBasic {
    public Bolo(String sabor) {
        super(sabor, 15.00); // preço fixo para todos os bolos
    }
}

// Classe para representar uma bebida
class Bebida extends ProdutoBasic {
    public Bebida(String tipo) {
        super(tipo, 5.00); // preço fixo para todas as bebidas
    }
}

// Classe para gerenciar um pedido
class Pedido {
    private final List<Produto> produtos; // Lista para armazenar produtos no pedido
    private String cpf; // CPF do cliente
    private String metodoPagamento; // metodo pagamento

    public Pedido() {
        produtos = new ArrayList<>(); // Inicializa a lista de produtos
    }

    // Método para adicionar um produto ao pedido
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Método para remover um produto do pedido
    public void removerProduto(int index) {
        if (index >= 0 && index < produtos.size()) {
            produtos.remove(index);
        } else {
            System.out.println("Índice de produto inválido.");
        }
    }

    // Método para calcular o total do pedido
    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco(); // soma os preços dos produtos
        }
        return total;
    }

    // Método para verificar se o pedido está vazio
    public boolean isEmpty() {
        return produtos.isEmpty();
    }

    // Método para salvar o pedido em um arquivo
    public void salvarEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write("Pedido:");
            writer.newLine();
            for (Produto produto : produtos) {
                writer.write(produto.getDescricao() + ": R$ " + produto.getPreco());
                writer.newLine();
            }
            writer.write("Total: R$ " + calcularTotal());
            writer.newLine();
            if (cpf != null && !cpf.isEmpty()) {
                writer.write("CPF: " + cpf);
                writer.newLine();
            }
            writer.write("-----------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o pedido em arquivo: " + e.getMessage());
        }
    }

    // Método para mostrar os detalhes do pedido
    public void mostrarDetalhes() {
        if (isEmpty()) {
            System.out.println("Nenhum item no pedido.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                System.out.println((i + 1) + ". " + produto.getDescricao() + ": R$ " + produto.getPreco());
            }
            System.out.println("Total: R$ " + calcularTotal());
            if (cpf != null && !cpf.isEmpty()) {
                System.out.println("CPF: " + cpf);
            }
            // método para mostrar detalhes do pedido omitido para brevidade
            // adicionar exibição do método de pagamento
            System.out.println("Método de pagamento: " + metodoPagamento);
        }
    }

    // Método para definir o CPF do cliente
    public void definirCpf(String cpf) {
        this.cpf = cpf;
    }
    public void definirMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}

// Classe para representar um usuário do sistema
class Usuario {
    private String nome; // Nome do usuário
    private String senha; // Senha do usuário
    private String tipoAcesso; // Tipo de acesso (Funcionario ou gerente)
    private String cpf; // CPF do usuário

    public Usuario(String nome, String senha, String tipoAcesso, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.tipoAcesso = tipoAcesso;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public String getCpf() {
        return cpf;
    }

    // Método para validar a senha do usuário
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }
}

// Classe para gerenciar o sistema de autenticação
class Sistema {
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public Sistema() {
        // Adiciona alguns usuários ao sistema
        usuarios.put("funcionario1", new Usuario("Funcionario 1", "funcionario123", "funcionario", "12345678900"));
        usuarios.put("funcionario2", new Usuario("Funcionario 2", "funcionario321", "funcionario", "23456789011"));
        usuarios.put("gerente1", new Usuario("Gerente 1", "gerente123", "gerente", "34567890122"));
        usuarios.put("gerente2", new Usuario("Gerente 2", "gerente321", "gerente", "45678901233"));
    }

    // Método para autenticar um usuário
    public Usuario autenticar(String nome, String senha) {
        Usuario usuario = usuarios.get(nome);
        if (usuario != null && usuario.validarSenha(senha)) {
            return usuario; // Retorna o usuário se a senha estiver correta
        }
        return null; // Retorna null se a autenticação falhar
    }
}

// Classe principal para executar o sistema da padaria
public class ExercicioPadariaV2 {
    private static final Sistema sistema = new Sistema(); // Instância do sistema

    // Listas de tipos de produtos
    private static final List<String> tiposPaes = List.of("Pão de Sal", "Pão de Queijo", 
                                                        "Totelex", "Pizzas e Salgados", "Cento de Salgados");
                                                        
    private static final List<String> tiposBebidas = List.of("Café Expresso", "Café com Leite", 
                                                            "Leite Quente", "Suco", "Refrigerante",
                                                            "Água");
                                                            
    private static final List<String> tiposBolos = List.of("Chocolate", "Morango", "Baunilha");
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Usuario usuario = null;

            // Loop para tentar autenticar o usuário até que a autenticação seja bem-sucedida
            while (usuario == null) {
                System.out.println("Bem-Vindo a Padaria Pague Menos");
                System.out.print("Digite o seu nome de usuário: ");
                String nome = scanner.nextLine();
                System.out.print("Digite a sua senha: ");
                String senha = scanner.nextLine();

                // Tenta autenticar o usuário
                usuario = sistema.autenticar(nome, senha);
                if (usuario != null) {
                    if ("gerente".equals(usuario.getTipoAcesso())) {
                        menuGerente();
                    } else if ("funcionario".equals(usuario.getTipoAcesso())) {
                        menuFuncionario();
                    }
                } else {
                    System.out.println("Nome de usuário ou senha inválidos. Tente novamente.");
                }
            }
        }
    }

    // Menu para funcionários
    private static void menuFuncionario() {
        try (Scanner scanner = new Scanner(System.in)) {
            Pedido pedido = new Pedido(); // Cria um novo pedido
            outer:
            while (true) {
                System.out.println("Menu do Funcionário:");
                System.out.println("1. Registrar Pedido");
                System.out.println("2. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha após a escolha
                switch (opcao) {
                    case 1:
                        while (true) {
                            System.out.println("Escolha um produto para adicionar ao pedido:");
                            System.out.println("1. Pão");
                            System.out.println("2. Bolo");
                            System.out.println("3. Bebida");
                            System.out.println("4. Cancelar Item");
                            System.out.println("5. Finalizar pedido");
                            System.out.print("Escolha uma opção: ");
                            int escolhaProduto = scanner.nextInt();
                            scanner.nextLine(); // Consome a nova linha após a escolha
                            switch (escolhaProduto) {
                                case 1:
                                    System.out.println("Tipos de pães disponíveis:");
                                    for (int i = 0; i < tiposPaes.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposPaes.get(i));
                                    }
                                    System.out.print("Escolha o tipo de pão: ");
                                    int tipoPaoEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoPaoEscolhido >= 0 && tipoPaoEscolhido < tiposPaes.size()) {
                                        String tipoPao = tiposPaes.get(tipoPaoEscolhido);
                                        pedido.adicionarProduto(new Pao(tipoPao));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Tipos de bolos disponíveis:");
                                    for (int i = 0; i < tiposBolos.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposBolos.get(i));
                                    }
                                    System.out.print("Escolha o tipo do bolo: ");
                                    int tipoBoloEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoBoloEscolhido >= 0 && tipoBoloEscolhido < tiposBolos.size()) {
                                        String tipoBolo = tiposBolos.get(tipoBoloEscolhido);
                                        pedido.adicionarProduto(new Bolo(tipoBolo));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Tipos de bebidas disponíveis:");
                                    for (int i = 0; i < tiposBebidas.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposBebidas.get(i));
                                    }
                                    System.out.print("Escolha o tipo de bebida: ");
                                    int tipoBebidaEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoBebidaEscolhido >= 0 && tipoBebidaEscolhido < tiposBebidas.size()) {
                                        String tipoBebida = tiposBebidas.get(tipoBebidaEscolhido);
                                        pedido.adicionarProduto(new Bebida(tipoBebida));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 4:
                                    // Cancelar item
                                    if (pedido.isEmpty()) {
                                        System.out.println("Não há itens no pedido para cancelar.");
                                    } else {
                                        System.out.println("Escolha o número do item a ser cancelado:");
                                        pedido.mostrarDetalhes();
                                        System.out.print("Digite o número do item: ");
                                        int index = scanner.nextInt() - 1;
                                        scanner.nextLine(); // Consome a nova linha após a escolha
                                        pedido.removerProduto(index);
                                        System.out.println("Item cancelado.");
                                    }
                                    break;
                                case 5:
                                    if (pedido.isEmpty()) {
                                        System.out.println("Nenhum produto no pedido para finalizar.");
                                    } else {
                                        System.out.println("Deseja adicionar um CPF ao pedido? (s/n): ");
                                        String resposta = scanner.nextLine();
                                        if ("s".equalsIgnoreCase(resposta)) {
                                            System.out.print("Digite o CPF: ");
                                            String cpf = scanner.nextLine();
                                            pedido.definirCpf(cpf);
                                        }
                                    // Adicionar a escolha do metodo de pagamento
                                    System.out.println("Escolha o metodo de pagamento:");
                                    System.out.println("1. Dinheiro!");
                                    System.out.println("2. Cartao de credito!");
                                    System.out.println("3. Cartao de debito!");
                                    System.out.println("4. Pix!");
                                    System.out.println("Escolha uma opção: "); 
                                    int opcaoPagamento = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (opcaoPagamento) {
                                        case 1: 
                                            pedido.definirMetodoPagamento("Dinheiro!");
                                        break;
                                        case 2: 
                                            pedido.definirMetodoPagamento("Cartao de credito!");
                                        break;
                                        case 3: 
                                            pedido.definirMetodoPagamento("Cartao de debito!");
                                        break;
                                        case 4: 
                                            pedido.definirMetodoPagamento("Pix!");
                                        break;
                                    }

                                        System.out.println("Pedido registrado!");
                                        pedido.mostrarDetalhes(); // Mostra os detalhes do pedido
                                        pedido.salvarEmArquivo("pedidos.txt"); // Salva o pedido em um arquivo .txt
                                        break outer; // Sai do loop e encerra o menu
                                    }
                                    break;
                                default:
                                    System.out.println("Opção inválida. Tente novamente.");
                                    break;
                            }
                        }
                        
                    case 2:
                        System.out.println("Saindo...");
                        break outer; // Sai do loop e encerra o menu
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }
    }

    // Menu para gerentes
    private static void menuGerente() {
        try (Scanner scanner = new Scanner(System.in)) {
            outer:
            while (true) {
                System.out.println("Menu do Gerente:");
                System.out.println("1. Registrar Pedido");
                System.out.println("2. Ver Relatórios");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha após a escolha
                switch (opcao) {
                    case 1:
                        Pedido pedido = new Pedido(); // Cria um novo pedido
                        while (true) {
                            System.out.println("Escolha um produto para adicionar ao pedido:");
                            System.out.println("1. Pão");
                            System.out.println("2. Bolo");
                            System.out.println("3. Bebida");
                            System.out.println("4. Cancelar Item");
                            System.out.println("5. Finalizar pedido");
                            System.out.print("Escolha uma opção: ");
                            int escolhaProduto = scanner.nextInt();
                            scanner.nextLine(); // Consome a nova linha após a escolha
                            switch (escolhaProduto) {
                                case 1:
                                    System.out.println("Tipos de pães disponíveis:");
                                    for (int i = 0; i < tiposPaes.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposPaes.get(i));
                                    }
                                    System.out.print("Escolha o tipo de pão: ");
                                    int tipoPaoEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoPaoEscolhido >= 0 && tipoPaoEscolhido < tiposPaes.size()) {
                                        String tipoPao = tiposPaes.get(tipoPaoEscolhido);
                                        pedido.adicionarProduto(new Pao(tipoPao));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Tipos de bolos disponíveis:");
                                    for (int i = 0; i < tiposBolos.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposBolos.get(i));
                                    }
                                    System.out.print("Escolha o tipo do bolo: ");
                                    int tipoBoloEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoBoloEscolhido >= 0 && tipoBoloEscolhido < tiposBolos.size()) {
                                        String tipoBolo = tiposBolos.get(tipoBoloEscolhido);
                                        pedido.adicionarProduto(new Bolo(tipoBolo));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Tipos de bebidas disponíveis:");
                                    for (int i = 0; i < tiposBebidas.size(); i++) {
                                        System.out.println((i + 1) + ". " + tiposBebidas.get(i));
                                    }
                                    System.out.print("Escolha o tipo de bebida: ");
                                    int tipoBebidaEscolhido = scanner.nextInt() - 1;
                                    scanner.nextLine(); // Consome a nova linha após a escolha
                                    if (tipoBebidaEscolhido >= 0 && tipoBebidaEscolhido < tiposBebidas.size()) {
                                        String tipoBebida = tiposBebidas.get(tipoBebidaEscolhido);
                                        pedido.adicionarProduto(new Bebida(tipoBebida));
                                    } else {
                                        System.out.println("Opção inválida.");
                                    }
                                    break;
                                case 4:
                                    // Cancelar item
                                    if (pedido.isEmpty()) {
                                        System.out.println("Não há itens no pedido para cancelar.");
                                    } else {
                                        System.out.println("Escolha o número do item a ser cancelado:");
                                        pedido.mostrarDetalhes();
                                        System.out.print("Digite o número do item: ");
                                        int index = scanner.nextInt() - 1;
                                        scanner.nextLine(); // Consome a nova linha após a escolha
                                        pedido.removerProduto(index);
                                        System.out.println("Item cancelado.");
                                    }
                                    break;
                                case 5:
                                    if (pedido.isEmpty()) {
                                        System.out.println("Nenhum produto no pedido para finalizar.");
                                    } else {
                                        System.out.println("Deseja adicionar um CPF ao pedido? (s/n): ");
                                        String resposta = scanner.nextLine();
                                        if ("s".equalsIgnoreCase(resposta)) {
                                            System.out.print("Digite o CPF: ");
                                            String cpf = scanner.nextLine();
                                            pedido.definirCpf(cpf);
                                        }
                                    // Adicionar a escolha do metodo de pagamento menu gerente
                                    System.out.println("Escolha o metodo de pagamento:");
                                    System.out.println("1. Dinheiro!");
                                    System.out.println("2. Cartao de credito!");
                                    System.out.println("3. Cartao de debito!");
                                    System.out.println("4. Pix!");
                                    System.out.println("Escolha uma opção: "); 
                                    int opcaoPagamento = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (opcaoPagamento) {
                                        case 1: 
                                            pedido.definirMetodoPagamento("Dinheiro!");
                                        break;
                                        case 2: 
                                            pedido.definirMetodoPagamento("Cartao de credito!");
                                        break;
                                        case 3: 
                                            pedido.definirMetodoPagamento("Cartao de debito!");
                                        break;
                                        case 4: 
                                            pedido.definirMetodoPagamento("Pix!");
                                        break;
                                    }

                                        System.out.println("Pedido registrado!");
                                        pedido.mostrarDetalhes(); // Mostra os detalhes do pedido
                                        pedido.salvarEmArquivo("pedidos.txt"); // Salva o pedido em um arquivo
                                        break outer; // Sai do loop e encerra o menu
                                    }
                                    break;
                                default:
                                    System.out.println("Opção inválida. Tente novamente.");
                                    break;
                            }
                        }
                        
                    case 2:
                        // Exibir relatórios (pedidos registrados)
                        System.out.println("Relatórios:");
                        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
                            String linha;
                            while ((linha = reader.readLine()) != null) {
                                System.out.println(linha);
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao ler o arquivo de pedidos: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break outer; // Sai do loop e encerra o menu
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }
    }
}