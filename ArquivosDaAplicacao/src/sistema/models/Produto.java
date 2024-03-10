package sistema.models;


//Trabalho em dupla (T1):
//Arthur Andrade Reis
//Ian Riki Taniguchi

public class Produto {
	private boolean statusVendido;
	private boolean statusExcluido;
	
	private int codigo;
	private int quantidade;
	private double preco;
	private String nome;
	private String marca;

	private Produto(int codigo, int quantidade, double preco, String nome, String marca) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.preco = preco;
		this.nome = nome;
		this.marca = marca;
		
		this.statusExcluido = false;
		this.statusVendido = false;
	}

	// Construtor de copia de produto
	public Produto(Produto produto) {
		this.codigo = produto.codigo;
		this.quantidade = produto.quantidade;
		this.preco = produto.preco;
		this.nome = produto.nome;
		this.marca = produto.marca;
	}

	// método fábrica
	public static Produto getInstance(int codigo, int quantidade, double preco, String nome, String marca) {
		if (preco > 0 && quantidade >= 0 && nome != null && marca != null) {
			return new Produto(codigo, quantidade, preco, nome, marca);
		} else {
			return null;
		}
	}

	// GETTERS e SETTERS
	public boolean getStatusVendido() {
		return statusVendido;
	}

	public void setStatusVendido(boolean statusVendido) {
		this.statusVendido = statusVendido;
	}
	
	public boolean getStatusExcluido() {
		return statusExcluido;
	}

	public void setStatusExcluido(boolean statusExcluido) {
		this.statusExcluido = statusExcluido;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade > 0) {
			this.quantidade = quantidade;
		}
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if (preco > 0) {
			this.preco = preco;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome;
		}
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if (marca != null) {
			this.marca = marca;
		}
	}

	public void baixaProduto(int quantidade) {
		this.quantidade -= quantidade;
	}

}
