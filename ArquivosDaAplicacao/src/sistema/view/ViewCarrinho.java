package sistema.view;

import java.util.List;
import java.util.Scanner;

import sistema.models.Carrinho;
import sistema.models.Item;

public class ViewCarrinho {
	private Scanner inputNumber;
	private Scanner inputNext;
	private Scanner inputNextLine;

	public ViewCarrinho() {
		this.inputNumber = new Scanner(System.in);
		this.inputNext = new Scanner(System.in);
		this.inputNextLine = new Scanner(System.in);
	}
	
	public double lerPrecoItem() {
		System.out.println("Digite o preço do item:");
		return inputNumber.nextDouble();
	}

	public int lerQuantidadeItem() {
		System.out.println("\nDigite a quantidade desse item:");
		return inputNumber.nextInt();
	}

	public int confirmarMudancaPreco(Carrinho carrinho, Item item) {
		int indice = carrinho.isDentroDoCarrinho(item);
		if (indice > -1) {
			System.out.println("Você deseja alterar o valor do item?");
			System.out.println("1 --> sim");
			System.out.println("Qualquer outro número --> não");
			int opcaoMudancaPreco = inputNumber.nextInt();

			if (opcaoMudancaPreco == 1) {
				return indice;
			}
		}

		return 0;
	}

	// Função para listar os itens do carrinho
	public void listarCarrinho(List<Item> itensDoCarrinho, int largura) {
		System.out.println("> CARRINHO");
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n",
				"CÓDIGO", "NOME", "MARCA", "PREÇO (ITEM)", "QUANTIDADE");

		for (Item item : itensDoCarrinho) {
			if (item != null) {
				String preco = String.format("%.2f", item.getPreco());
				System.out.printf(
						"%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "d\n",
						item.getProduto().getCodigo(), item.getProduto().getNome(), item.getProduto().getMarca(), preco,
						item.getQuantidade());
			}
		}
	}

}
