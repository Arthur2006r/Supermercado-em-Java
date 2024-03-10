package sistema.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import sistema.control.ControladorDeProduto;
import sistema.models.Carrinho;
import sistema.models.Item;
import sistema.models.Produto;
import sistema.models.Venda;

public class ViewVenda {
	private Scanner inputNumber;
	private Scanner inputNext;

	public ViewVenda() {
		this.inputNumber = new Scanner(System.in);
		this.inputNext = new Scanner(System.in);
	}

	// Imprime, em forma de tabela, o vetor recebido
	public void listarVendas(List<Venda> vendas, int largura) {
	    System.out.println("Vendas:\n");
	    System.out.printf("%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n", "CÓDIGO",
	            "NOME DO ATENDENTE", "DATA", "VALOR DA VENDA");

	    for (Venda venda : vendas) {
	        if (venda != null) {
	            double valorVenda = calcularValorVenda(venda);
	            String valorVendaFinal = String.format("%.2f", valorVenda);

	            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String dataFormatada = dateFormat.format(venda.getData());

	            System.out.printf("%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s\n",
	                    venda.getCodigo(), venda.getAtd().getNome(), dataFormatada, valorVendaFinal);
	        }
	    }
	}


	// função lista o produto desejado para haver a listagem
	public void listarVenda(Venda venda, int largura) {
		System.out.println("Venda:\n");
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura
						+ "s\n",
				"CÓDIGO", "DATA", "NOME DO ATENDENTE", "NOME DO COMPRADOR", "QUANTIDADE", "VALOR TOTAL");

		double valorVenda = calcularValorVenda(venda);
		String valorVendaFinal = String.format("%.2f", valorVenda);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(venda.getData());

		System.out.printf(
				"%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura
						+ "s\n",
				venda.getCodigo(), dataFormatada, venda.getAtd().getNome(), venda.getNomeCliente(),
				venda.quantidadeDeItens(), valorVendaFinal);

		System.out.println(".....");
		System.out.println("> Relação dos produtos");
		gerarRelacaoDeProdutos(venda, largura);
	}

	static void gerarRelacaoDeProdutos(Venda venda, int largura) {
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n", "ITEM",
				"CÓDIGO PROD.", "NOME PRODUTO", "QUANTIDADE CMPR.", "PREÇO UNIDADE");
		int itemNumero = 1;
		for (Item item : venda.getItensVendidos()) {
			if (item != null) {
				Produto produto = item.getProduto();
				double preco = item.getPreco();
				String precoString = String.format("%.2f", preco);

				System.out.printf(
						"%-" + largura + "d%-" + largura + "d%-" + largura + "s%-" + largura + "d%-" + largura + "s\n",
						itemNumero, produto.getCodigo(), produto.getNome(), item.getQuantidade(), precoString);
				itemNumero++;
			}
		}
	}

	public void mostrarDetalhesVenda(Venda venda) {
		System.out.println("Detalhes da Venda:");
		System.out.println("Código da Venda: " + venda.getCodigo());
		System.out.println("Nome do Cliente: " + venda.getNomeCliente());
		System.out.println("Data da Venda: " + venda.getData());
		System.out.println("Itens Vendidos: ");
		for (Item item : venda.getItensVendidos()) {
			if (item != null) {
				System.out.println("Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade());
			}
		}
		System.out.println("Nome do Atendente: " + venda.getAtd().getNome());
	}

	public String lerNomeComprador() {
		System.out.println();
		System.out.print("Digite o nome do comprador: ");
		return inputNext.next();
	}

	public int consertarErroVenda() {
		System.out.println();
		System.out
				.println("Não foi possível realizar a venda, você ultrapassou a quantidade de um determiando produto!");
		System.out.println(
				"!Atenção, se você digitar com a quantidade novamente na hora de concertar a venda haverá o cacelamento da mesma!");
		System.out.println("Deseja consertar a quantidade ou cancelar a venda?");
		System.out.println("1 --> Consertar quantidade");
		System.out.println("Qualquer outro número --> Cancelar venda");

		return inputNumber.nextInt();
	}

	public void consertarCarrinho(ControladorDeProduto controleDeProduto, Carrinho carrinho, int larguraDasColunas) {
		System.out.println("CONSERTANDO CARRINHO...");
		System.out.println();

		for (Item item : carrinho.getItens()) {
			if (item != null) {
				Produto produto = item.getProduto();

				if (produto.getQuantidade() < item.getQuantidade()) {
					System.out.println("> Conserte");
					int quantidadeMaxima = produto.getQuantidade();
					controleDeProduto.listarProduto(produto);
					System.out.println("A quantidade máxima do item deve ser " + quantidadeMaxima);
					System.out.print("Digite a nova quantidade: ");
					int quantidade = inputNumber.nextInt();

					if (quantidade <= quantidadeMaxima) {
						item.setQuantidade(quantidade);
					} else {
						System.out.println("Quantidade inválida. A quantidade máxima é " + quantidadeMaxima);
					}
				}
			}
		}
	}

	public int lerCodigoVenda() {
		System.out.println("Digite o código da venda desejada:");
		return inputNumber.nextInt();
	}

	public double calcularValorVenda(Venda venda) {
		double valorVenda = 0;

		if (venda != null) {
			for (Item item : venda.getItensVendidos()) {
				if (item != null) {
					valorVenda += item.getPreco() * item.getQuantidade();
				}
			}
		}

		return valorVenda;
	}
}
