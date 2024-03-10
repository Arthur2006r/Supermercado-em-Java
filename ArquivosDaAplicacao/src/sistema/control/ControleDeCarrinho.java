package sistema.control;

import java.util.List;

import sistema.models.Carrinho;
import sistema.models.Item;
import sistema.models.Produto;
import sistema.view.View;
import sistema.view.ViewCarrinho;

public class ControleDeCarrinho {
	private View view;
	private ViewCarrinho viewCarrinho;

	public ControleDeCarrinho() {
		this.view = new View();
		this.viewCarrinho = new ViewCarrinho();
	}

	public void inserirItem(Carrinho carrinho, ControladorDeProduto controleDeProduto) {
		Produto produtoCopia = controleDeProduto.buscar();
		if (produtoCopia != null) {
			if (produtoCopia.getQuantidade() > 0) {
				controleDeProduto.listarProduto(produtoCopia);

				double preco = viewCarrinho.lerPrecoItem();

				int quantidade = viewCarrinho.lerQuantidadeItem();

				Item item = Item.getInstance(produtoCopia, preco, quantidade);

				if (item != null) {
					confirmarMudancaPreco(carrinho, item);
					carrinho.addItem(item);
					view.print("\nItem adicionado ao carrinho com sucesso!");
				} else {
					view.print("\nDesculpe, ocorreu um erro na digitação dos dados do item! Tente novamente.");
				}
			} else {
				view.print("Não há deste produto no estoque! Tente novamente mais tarde.");
			}
		} else {
			view.print("Produto não encontrado!");
		}
	}

	public void listarItens(Carrinho carrinho) {
		List<Item> itensDoCarrinho = carrinho.listarItensCarrinho();

		if (!itensDoCarrinho.isEmpty()) {
			int largura = view.lerLarguraDasColunas();

			viewCarrinho.listarCarrinho(itensDoCarrinho, largura);
		} else {
			view.print("Não há itens no carrinho!");
		}
	}

	public void confirmarMudancaPreco(Carrinho carrinho, Item item) {
		int indice = viewCarrinho.confirmarMudancaPreco(carrinho, item);

		if (indice >= 0 && indice < carrinho.getItens().size()) {
			carrinho.getItens().get(indice).setPreco(item.getPreco());
			view.print("Preço modificado com sucesso!\n");
		}
	}

}
