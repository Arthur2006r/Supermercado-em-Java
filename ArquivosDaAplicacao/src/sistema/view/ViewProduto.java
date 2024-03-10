package sistema.view;

import java.util.List;
import java.util.Scanner;

import sistema.control.ControladorDeProduto;
import sistema.models.Produto;

public class ViewProduto {
	private View view;

	private static Scanner inputNumber;
	private static Scanner inputNext;
	private static Scanner inputNextLine;

	public ViewProduto() {
		this.inputNumber = new Scanner(System.in);
		this.inputNext = new Scanner(System.in);
		this.inputNextLine = new Scanner(System.in);
	}

	// Função que lê o produto e chama outra função para cadastrar o produto lido no
	// vetor de produtos
	public Produto lerProduto(ControladorDeProduto controleDeProduto) {
		System.out.println("\n> Leitura de produto");

		int codigo = controleDeProduto.gerarCodigoDoProduto();

		System.out.print("Digite o nome do produto: ");
		String nome = inputNextLine.nextLine();

		System.out.print("Digite a marca do produto: ");
		String marca = inputNextLine.nextLine();

		System.out.print("Digite o preço do produto: ");
		double preco = inputNumber.nextDouble();

		System.out.print("Digite a quantidade em estoque do produto: ");
		int quantidade = inputNumber.nextInt();

		return Produto.getInstance(codigo, quantidade, preco, nome, marca);
	}

	public int excluir() {
		view.print("Digite o código do produto (para haver a exclusão): ");
		return inputNumber.nextInt();
	}

	public int alterar() {
		Scanner inpt = new Scanner(System.in);
		System.out.print("Para haver a alteração, ");
		System.out.println("Insira o código do produto:");
		int codigo = inpt.nextInt();

		return codigo;
	}

	public void lerAlteracaoProduto(Produto produto) {

		System.out.println("\n> Leitura da alteração do produto");

		System.out.print("Digite o nome do produto: ");
		produto.setNome(inputNextLine.nextLine());

		System.out.print("Digite a marca do produto: ");
		produto.setMarca(inputNextLine.nextLine());

		System.out.print("Digite o preço do produto: ");
		produto.setPreco(inputNumber.nextDouble());
		while (produto.getPreco() <= 0) {
			System.out.print("Digite um valor positivo para o preço!: ");
			produto.setPreco(inputNumber.nextDouble());
		}

		System.out.print("Digite a quantidade em estoque do produto: ");
		produto.setQuantidade(inputNumber.nextInt());
		while (produto.getQuantidade() <= 0) {
			System.out.print("Digite um valor positivo para a quantidade em estoque do produto!: ");
			produto.setQuantidade(inputNumber.nextInt());
		}
	}

	// Busca o produto desejado
	public int buscar() {
		int opcao = 0;
		while (opcao != 1 && opcao != 2) {
			System.out.print("Insira 1 para nome, 2 para codigo: ");
			opcao = inputNumber.nextInt();

			if (opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida! Digite novamente.");
			}
		}

		return opcao;
	}

	public String buscarNome() {
		System.out.println("Insira o nome do produto:");
		return inputNext.next();
	}

	public int buscarCod() {
		System.out.println("Insira o código do produto:");
		return inputNumber.nextInt();
	}

	// Função para listar um único produto
	public void listarProduto(Produto produto, int largura) {
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n",
				"CÓDIGO", "NOME", "MARCA", "PREÇO (PRODUTO)", "ESTOQUE");
		String preco = String.format("%.2f", produto.getPreco());
		System.out.printf(
				"%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "d\n",
				produto.getCodigo(), produto.getNome(), produto.getMarca(), preco, produto.getQuantidade());
	}

	// Função para listar uma lista de produtos
	public void listarProdutos(List<Produto> produtos, int largura) {
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n",
				"CÓDIGO", "NOME", "MARCA", "PREÇO (PRODUTO)", "ESTOQUE");

		for (Produto produto : produtos) {
			if (produto != null) {
				String preco = String.format("%.2f", produto.getPreco());
				System.out.printf(
						"%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "d\n",
						produto.getCodigo(), produto.getNome(), produto.getMarca(), preco, produto.getQuantidade());
			}
		}
	}

}
