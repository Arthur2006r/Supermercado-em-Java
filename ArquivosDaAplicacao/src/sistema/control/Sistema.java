package sistema.control;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import sistema.models.Carrinho;
import sistema.models.Item;
import sistema.models.Produto;
import sistema.models.Venda;
import sistema.models.usuario.Admin;
import sistema.models.usuario.Atendente;
import sistema.view.ViewMenu;

//Trabalho em dupla (T1):
//Arthur Andrade Reis
//Ian Riki Taniguchi

// Classe de fachada
public class Sistema {
	private static Sistema instance;

	private ControladorDeProduto controleDeProduto;
	private ControleDeCarrinho controleDeCarrinho;
	private ControladorDeVenda controleDeVenda;
	private ControladorDeUsuario controleDeUsuario;
	private ControladorDeAtendente controleDeAtendente;

	private ViewMenu viewMenu;

	// ele deve iniciar o vetor quando se instancia o sistema e não deve ser
	// utilizado diretamente, somente a partir do getInstance
	private Sistema() {
		controleDeProduto = new ControladorDeProduto();
		controleDeCarrinho = new ControleDeCarrinho();
		controleDeVenda = new ControladorDeVenda();
		controleDeUsuario = new ControladorDeUsuario();
		controleDeAtendente = new ControladorDeAtendente();

		viewMenu = new ViewMenu();
	}

	// método fábrica
	// confere se uma instancia já foi criada, se sim, ele retorna a instância que
	// tinha criado a priori
	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}

		return instance;
	}

	// gera 5 produtos aleatórios e insere no vetor
	// gera 2 vendas
	public void init() {
		Atendente atd = Atendente.getInstance(controleDeUsuario.gerarCodigoDoUsuario(), "Arthur", "att", "123");
		controleDeUsuario.cadastrarUsuario(atd);

		Admin admin = Admin.getInstance(controleDeUsuario.gerarCodigoDoUsuario(), "Ian", "admin", "123");
		controleDeUsuario.cadastrarUsuario(admin);

		Produto produto1 = Produto.getInstance(controleDeProduto.gerarCodigoDoProduto(), 50, 19.90, "Arroz",
				"Rei Arthur");
		controleDeProduto.inserir(produto1);

		Produto produto2 = Produto.getInstance(controleDeProduto.gerarCodigoDoProduto(), 80, 9.90, "Feijao",
				"Granjeiro");
		controleDeProduto.inserir(produto2);

		Produto produto3 = Produto.getInstance(controleDeProduto.gerarCodigoDoProduto(), 100, 29.90, "Sorvete",
				"Nestle");
		controleDeProduto.inserir(produto3);

		Produto produto4 = Produto.getInstance(controleDeProduto.gerarCodigoDoProduto(), 190, 49.90, "Carne de porco",
				"Friboi");
		controleDeProduto.inserir(produto4);

		Produto produto5 = Produto.getInstance(controleDeProduto.gerarCodigoDoProduto(), 100, 11.90, "Coca-Cola",
				"Coca-Cola");
		controleDeProduto.inserir(produto5);

		// Itens para a venda
		Item item1 = Item.getInstance(produto1, 12, 10);
		Item item2 = Item.getInstance(produto2, 10, 20);
		Item item3 = Item.getInstance(produto3, 29.9, 15);
		Item item4 = Item.getInstance(produto4, 7, 7);
		Item item5 = Item.getInstance(produto5, 9, 16);

		// Carrinhos para a venda
		Carrinho carrinho1 = new Carrinho();
		carrinho1.addItem(item1);
		carrinho1.addItem(item4);
		carrinho1.addItem(item5);

		Carrinho carrinho2 = new Carrinho();
		carrinho2.addItem(item2);
		carrinho2.addItem(item4);
		carrinho2.addItem(item3);
		carrinho2.addItem(item1);

		// Datas para as vendas
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String dataString1 = "26/09/2006";
		dateFormat.setLenient(false);
		ParsePosition posicao = new ParsePosition(0);
		Date data1 = dateFormat.parse(dataString1, posicao);

		String dataString2 = "11/04/2022";
		dateFormat.setLenient(false);
		posicao = new ParsePosition(0);
		Date data2 = dateFormat.parse(dataString2, posicao);

		// Vendas
		Venda venda1 = Venda.getInstance(carrinho1, controleDeVenda.gerarCodigoDaVenda(), "Arthur", data1, atd);
		controleDeVenda.inserirVenda(venda1);

		Venda venda2 = Venda.getInstance(carrinho2, controleDeVenda.gerarCodigoDaVenda(), "Ian", data2, atd);
		controleDeVenda.inserirVenda(venda2);
	}

	public void iniciar() {
		init();

		int opcao = 0;

		while (opcao != 3) {
			opcao = viewMenu.menuLogin();

			switch (opcao) {
			// Caso o usuário escolha entrar como Administrador
			case 1: {
				loginAdmin();
				break;
			}
			// Caso o usuário escolha entrar como Atendente
			case 2: {
				logarAtendente();
				break;
			}
			case 3: {
				break;
			}
			// Caso o usuário escolha uma opção inváida
			default:
				throw new IllegalArgumentException("opção inválida: " + opcao);
			}
		}
	}

	public void menuAdministrador(int codigoAdmin) {
		int opcaoAdministrador = 0;

		// Laço de repetição que acaba quando o usuário seleciona a opção de sair do
		// programa
		while (true) {
			opcaoAdministrador = viewMenu.menuAdministrador();

			// Switch para haver o controle da escolha de opção do usuário
			switch (opcaoAdministrador) {
			// Caso o usuário escolha inserir um produto
			case 1: {
				inserirProduto();
				break;
			}
			// Caso o usuário escolha excluir um produto
			case 2: {
				excluirProduto();
				break;
			}
			// Caso o usuário escolha alterar um produto
			case 3: {
				alterarProduto();
				break;
			}
			// Caso o usuário escolha gerar listagem de todos os produtos (em ordem de
			// cadastro)
			case 4: {
				listarTodosProdutosOrdemCadastro();
				break;
			}
			// Caso o usuário escolha gerar listagem de todos os produtos (em ordem
			// alfabética)
			case 5: {
				listarTodosProdutosOrdemNome();
				break;
			}
			// Caso o usuário escolha gerar listagem de todas as vendas realizadas
			case 6: {
				listarVendas();
				break;
			}
			// Caso o usuário escolha gerar listagem de todas as vendas realizadas de um
			// determinado dia
			case 7: {
				listarVendasDoDia();
				break;
			}
			// Caso o usuário escolha gerar listagem de uma venda pelo seu código
			case 8: {
				listarVendaCodigo();
				break;
			}
			// Caso o usuário escolha cadastrar um usuário
			case 9: {
				cadastrarUsuario();
				break;
			}
			// Caso o usuário escolha excluir um usuário
			case 10: {
				excluirUsuario(codigoAdmin);
				break;
			}
			// Caso o usuário escolha listar os usuários
			case 11: {
				listarUsuarios();
				break;
			}
			// Caso o usuário escolha voltar
			case 12: {
				return;
			}
			// Caso o usuário escolha uma opção inváida
			default:
				throw new IllegalArgumentException("opção inválida: " + opcaoAdministrador);
			}
		}
	}

	public void menuAtendente(int codigoAtendente) {
		int opcaoAtendente = 0;

		// Laço de repetição que acaba quando o usuário seleciona a opção de sair do
		// programa
		while (true) {
			opcaoAtendente = viewMenu.menuAtendente(controleDeUsuario.pegarAtendenteCopia(codigoAtendente).getNome());

			// Switch para haver o controle da escolha de opção do usuário
			switch (opcaoAtendente) {
			// Caso o usuário escolha abrir um novo carrinho
			case 1: {
				menuCarrinho(codigoAtendente);
				break;
			}
			// Caso o usuário escolha voltar para o menu principal
			case 2: {
				return;
			}
			// Caso o usuário escolha uma opção inváida
			default:
				throw new IllegalArgumentException("opção inválida: " + opcaoAtendente);
			}
		}
	}

	public void menuCarrinho(int codigoAtendente) {
		int opcaoCarrinho = 0;

		// Abre o carrinho para a venda
		Carrinho carrinho = new Carrinho();

		while (true) {
			opcaoCarrinho = viewMenu.menuCarrinho(controleDeUsuario.pegarAtendenteCopia(codigoAtendente).getNome());

			// Switch para haver o controle da escolha de opção do usuário
			switch (opcaoCarrinho) {
			// Caso o usuário escolha inserir um item no carrinho
			case 1: {
				inserirItemCarrinho(carrinho);
				break;
			}
			// Caso o usuário escolha visualizar itens no carrinho
			case 2: {
				listarCarrinho(carrinho);
				break;
			}
			// Caso o usuário escolha finalizar a venda
			case 3: {
				finalizarVenda(carrinho, codigoAtendente);
				return;
			}
			// Caso o usuário escolha cancelar a venda
			case 4: {
				return;
			}
			// Caso o usuário escolha uma opção inváida
			default:
				throw new IllegalArgumentException("opção inválida: " + opcaoCarrinho);
			}
		}

	}

	// PRODUTO //
	public void inserirProduto() {
		controleDeProduto.inserir();
	}

	public void excluirProduto() {
		controleDeProduto.excluir();
	}

	public void alterarProduto() {
		controleDeProduto.alterar();
	}

	public void listarTodosProdutosOrdemCadastro() {
		controleDeProduto.listarTodosOrdemCadastro();
	}

	public void listarTodosProdutosOrdemNome() {
		controleDeProduto.listarTodosProdutosOrdemNome();
	}
	// ... //

	// CARRINHO //
	public void inserirItemCarrinho(Carrinho carrinho) {
		controleDeCarrinho.inserirItem(carrinho, controleDeProduto);
	}

	public void listarCarrinho(Carrinho carrinho) {
		controleDeCarrinho.listarItens(carrinho);
	}
	// ... //

	// VENDA //
	public void finalizarVenda(Carrinho carrinho, int codigoAtendente) {
		controleDeVenda.finalizarVenda(controleDeProduto, controleDeUsuario, controleDeAtendente, carrinho, codigoAtendente);
	}

	public void listarVendas() {
		controleDeVenda.listarVendas();
	}

	public void listarVendasDoDia() {
		controleDeVenda.listarVendasDoDia();
	}

	public void listarVendaCodigo() {
		controleDeVenda.listarVendaCodigo();
	}
	// ... //

	// USUÁRIO e suas subclasses // 
	public void cadastrarUsuario() {
		controleDeUsuario.cadastrarUsuario();
	}

	public void excluirUsuario(int codigoAdmin) {
		controleDeUsuario.excluirUsuario(codigoAdmin);
	}

	public void listarUsuarios() {
		controleDeUsuario.listarUsuarios();
	}
	
	public void loginAdmin() {
		controleDeUsuario.loginAdmin();
	}

	public void logarAtendente() {
		controleDeUsuario.loginAtendente();
	} 
	// ... //
}
