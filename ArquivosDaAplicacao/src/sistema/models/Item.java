package sistema.models;

public class Item {
	private Produto produto;
	private double preco;
	private int quantidade;

	// Construtor de item
	private Item(Produto produto, double preco, int quantidade) {
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	// Construtor de copia
	public Item(Item item) {
		this.produto = item.produto;
		this.preco = item.preco;
		this.quantidade = item.quantidade;
	}

	// Método fábrica
	public static Item getInstance(Produto produto, double preco, int quantidade) {
		if (produto != null && preco > 0 && quantidade > 0) {
			return new Item(produto, preco, quantidade);
		} else {
			return null;
		}
	}

	// GETTERS e SETTERS
	public Produto getProduto() {
		return produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if (preco > 0) {
			this.preco = preco;
		}
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade > 0) {
			this.quantidade = quantidade;
		}
	}

	// Método que aglutina item
	public void aglutinarItens(Item item) {
		this.quantidade += item.quantidade;
	}

}
