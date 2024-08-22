//programa java que contenha interface, classes abstratas e na
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

// Importa apenas as classes necessárias da biblioteca java.util
            
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Scanner;
            
    // Interface para definir a estrutura dos produtos da padaria
    interface Produto {
    double getPreco(); // Método para obter o preço do produto
    String getDescricao(); // Método para obter a descrição do produto
    }
            
    // Classe abstrata para produtos básicos
    abstract class ProdutoBase implements Produto {
    protected String descricao; // Descrição do produto
    protected double preco; // Preço do produto
            
    // Construtor para inicializar a descrição e o preço
    public ProdutoBase(String descricao, double preco) {
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
            class Pao extends ProdutoBase {
                public Pao(String tipo) {
                    super(tipo, 2.50); // Preço fixo para todos os pães
                }
            }
            
            // Classe para representar um bolo
            class Bolo extends ProdutoBase {
                public Bolo(String sabor) {
                    super("Bolo de " + sabor, 20.00); // Preço fixo para todos os bolos
                }
            }
            
            // Classe para representar uma bebida
            class Bebida extends ProdutoBase {
                public Bebida(String tipo) {
                    super(tipo, 3.00); // Preço fixo para todas as bebidas
                }
            }
            
            // Classe para gerenciar um pedido
            class Pedido {
                private final List<Produto> produtos; // Lista para armazenar produtos no pedido
            
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
                        total += produto.getPreco(); // Soma os preços dos produtos
                    }
                    return total;
                }
            
                // Método para mostrar os detalhes do pedido
                public void mostrarDetalhes() {
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto no pedido.");
                        return;
                    }
                    for (int i = 0; i < produtos.size(); i++) {
                        Produto produto = produtos.get(i);
                        System.out.println((i + 1) + ". " + produto.getDescricao() + ": R$ " + produto.getPreco());
                    }
                    System.out.println("Total: R$ " + calcularTotal()); // Mostra o total
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
                        writer.write("------------------------------");
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar o pedido em arquivo: " + e.getMessage());
                    }
                }
            }
            
            // Classe para representar um usuário do sistema
            class Usuario {
                private final String nome; // Nome do usuário
                private final String senha; // Senha do usuário
                private final String tipoAcesso; // Tipo de acesso ("funcionario" ou "gerente")
            
                public Usuario(String nome, String senha, String tipoAcesso) {
                    this.nome = nome;
                    this.senha = senha;
                    this.tipoAcesso = tipoAcesso;
                }
            
                public String getNome() {
                    return nome;
                }
            
                public String getTipoAcesso() {
                    return tipoAcesso;
                }
            
                // Método para validar a senha do usuário
                public boolean validarSenha(String senha) {
                    return this.senha.equals(senha);
                }
            }
            
            // Classe para gerenciar o sistema de autenticação
            class Sistema {
                private final Map<String, Usuario> usuarios; // Mapa para armazenar usuários
            
                public Sistema() {
                    usuarios = new HashMap<>();
                    // Adiciona alguns usuários ao sistema
                    usuarios.put("funcionario1", new Usuario("Funcionario 1", "senha123", "funcionario"));
                    usuarios.put("funcionario2", new Usuario("Funcionario 2", "senha456", "funcionario"));
                    usuarios.put("gerente1", new Usuario("Gerente 1", "senha789", "gerente"));
                    usuarios.put("gerente2", new Usuario("Gerente 2", "senha000", "gerente"));
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
            public class Padaria{
                private static final Sistema sistema = new Sistema(); // Instância do sistema
            
            public static void main(String[] args) {
                try (Scanner scanner = new Scanner(System.in)) {
                    Usuario usuario = null;
                        
                    // Loop para tentar autenticar o usuário até que a autenticação seja bem-sucedida
                    while (usuario == null) {
                        System.out.println("Bem-vindo ao Sistema da Padaria");
                        System.out.print("Digite seu nome de usuário: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite sua senha: ");
                        String senha = scanner.nextLine();
                            
                        // Tenta autenticar o usuário
                        usuario = sistema.autenticar(nome, senha);
                        if (usuario == null) {
                            System.out.println("Autenticação falhou! Tente novamente.");
                        } else {
                            System.out.println("Bem-vindo, " + usuario.getNome() + "!");
                            // Mostra o menu apropriado baseado no tipo de acesso do usuário
                            if ("gerente".equals(usuario.getTipoAcesso())) {
                                menuGerente();
                            } else {
                            menuFuncionario();
                            }
                        }
                    }
                }
            }
            
            // Menu para funcionários
            private static void menuFuncionario() {
                try (Scanner scanner = new Scanner(System.in)) {
                    Pedido pedido = new Pedido(); // Cria um novo pedido
                    OUTER:            
                    while (true) {
                        System.out.println("Menu do Funcionário:");
                        System.out.println("1. Registrar Pedido");
                        System.out.println("2. Sair");
                        System.out.print("Escolha uma opção: ");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcao) {
                            case 1 -> {
                            OUTER_1:
                            while (true) {
                                System.out.println("Escolha um produto para adicionar ao pedido:");
                                System.out.println("1. Pão");
                                System.out.println("2. Bolo");
                                System.out.println("3. Bebida");
                                System.out.println("4. Cancelar Item");
                                System.out.println("5. Finalizar pedido");
                                System.out.print("Escolha uma opção: ");
                                int escolhaProduto = scanner.nextInt();
                                scanner.nextLine();
                                switch (escolhaProduto) {
                                    case 1 -> {
                                        System.out.print("Digite o tipo de pão (por exemplo, 'Pão de Sal'): ");
                                        String tipoPao = scanner.nextLine();
                                        pedido.adicionarProduto(new Pao(tipoPao));
                                    }
                                    case 2 -> {
                                        System.out.print("Digite o sabor do bolo (por exemplo, 'Chocolate'): ");
                                        String saborBolo = scanner.nextLine();
                                        pedido.adicionarProduto(new Bolo(saborBolo));
                                    }
                                    case 3 -> {
                                        System.out.print("Digite o tipo de bebida (por exemplo, 'Café Expresso'): ");
                                        String tipoBebida = scanner.nextLine();
                                        pedido.adicionarProduto(new Bebida(tipoBebida));
                                    }
                                    case 4 -> {
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
                                    }
                                    case 5 -> {
                                        if (pedido.isEmpty()) {
                                            System.out.println("Nenhum produto no pedido para finalizar.");
                                        } else {
                                            System.out.println("Pedido registrado!");
                                            pedido.mostrarDetalhes(); // Mostra os detalhes do pedido
                                            pedido.salvarEmArquivo("pedidos.txt"); // Salva o pedido em um arquivo
                                            break OUTER_1; // Sai do loop e encerra o menu
                                        }
                                    }
                                    default -> System.out.println("Opção inválida. Tente novamente."); // Informa ao usuário que a opção é inválida
                                }
                            }
                                }
                            case 2 -> {
                                System.out.println("Saindo...");
                            break OUTER; // Sai do loop e encerra o menu
                            }
                            default -> System.out.println("Opção inválida. Tente novamente."); // Informa ao usuário que a opção é inválida
                        }
                    }
                }
            }
            
            // Menu para gerentes
            private static void menuGerente() {
                try (Scanner scanner = new Scanner(System.in)) {
                    OUTER:
                    while (true) {
                        System.out.println("Menu do Gerente:");
                        System.out.println("1. Registrar Pedido");
                        System.out.println("2. Ver Relatórios");
                        System.out.println("3. Sair");
                        System.out.print("Escolha uma opção: ");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcao) {
                            case 1 -> {
                            Pedido pedido = new Pedido(); // Cria um novo pedido
                            OUTER_1:
                            while (true) {
                                System.out.println("Escolha um produto para adicionar ao pedido:");
                                System.out.println("1. Pão");
                                System.out.println("2. Bolo");
                                System.out.println("3. Bebida");
                                System.out.println("4. Cancelar Item");
                                System.out.println("5. Finalizar pedido");
                                System.out.print("Escolha uma opção: ");
                                int escolhaProduto = scanner.nextInt();
                                scanner.nextLine();
                                switch (escolhaProduto) {
                                    case 1 -> {
                                        System.out.print("Digite o tipo de pão (por exemplo, 'Pão de Sal'): ");
                                        String tipoPao = scanner.nextLine();
                                        pedido.adicionarProduto(new Pao(tipoPao));
                                        }
                                    case 2 -> {
                                        System.out.print("Digite o sabor do bolo (por exemplo, 'Chocolate'): ");
                                        String saborBolo = scanner.nextLine();
                                        pedido.adicionarProduto(new Bolo(saborBolo));
                                        }
                                    case 3 -> {
                                        System.out.print("Digite o tipo de bebida (por exemplo, 'Café Expresso'): ");
                                        String tipoBebida = scanner.nextLine();
                                        pedido.adicionarProduto(new Bebida(tipoBebida));
                                        }
                                    case 4 -> {
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
                                    }
                                    case 5 -> {
                                        if (pedido.isEmpty()) {
                                            System.out.println("Nenhum produto no pedido para finalizar.");
                                        } else {
                                            System.out.println("Pedido registrado!");
                                            pedido.mostrarDetalhes(); // Mostra os detalhes do pedido
                                            pedido.salvarEmArquivo("pedidos.txt"); // Salva o pedido em um arquivo
                                            break OUTER_1; // Sai do loop e encerra o menu
                                        }
                                    }
                                    default -> System.out.println("Opção inválida. Tente novamente."); // Informa ao usuário que a opção é inválida
                                }
                            }
                            }
                            case 2 -> {
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
                            }
                            case 3 -> {
                                System.out.println("Saindo...");
                                break OUTER; // Sai do loop e encerra o menu
                            }
                            default -> System.out.println("Opção inválida. Tente novamente."); // Informa ao usuário que a opção é inválida
                        }
                    }
 }
    }
}
            
            

