package sistema.models;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private List<Item> itens;

	public Carrinho() {
		this.itens = new ArrayList<>();
	}

	// Retorne uma cópia da lista de itens

	public List<Item> getItens() {
		return new ArrayList<>(itens);
	}

	// Método de adicionar item no carrinho
	public boolean addItem(Item item) {
		int indice = isDentroDoCarrinho(item);

		if (indice > -1) {
			itens.get(indice).aglutinarItens(item);
		} else {
			itens.add(item);
			return true;
		}

		return false;
	}

	public int isDentroDoCarrinho(Item item) {
		for (int indice = 0; indice < itens.size(); indice++) {
			if (itens.get(indice) != null
					&& itens.get(indice).getProduto().getCodigo() == item.getProduto().getCodigo()) {
				return indice;
			}
		}
		return -1;
	}

	public List<Item> listarItensCarrinho() {
		return new ArrayList<>(itens);
	}

	public boolean isCarrinhoValido() {
		for (Item item : itens) {
			if (item != null && item.getQuantidade() > item.getProduto().getQuantidade()) {
				return false;
			}
		}
		return true;
	}
}
