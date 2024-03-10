package sistema.dados;

import java.util.ArrayList;
import java.util.List;

import sistema.models.Produto;

public class RepositorioProduto {
	private static int quantidadeProdutos = 0;

	private List<Produto> produtos;

	public RepositorioProduto() {
		this.produtos = new ArrayList<>();
	}

	public int gerarCodigoDoProduto() {
		quantidadeProdutos++;
		return quantidadeProdutos;
	}

	public boolean inserir(Produto produto) {
		if (produto != null) {
			produtos.add(produto);
			return true;
		}
		return false;
	}

	public boolean alterar(Produto produto) {
		if (produto != null && !isNomeRepetido(produto)) {
			for (Produto p : produtos) {
				if (p != null && p.getCodigo() == produto.getCodigo()) {
					p.setNome(produto.getNome());
					p.setMarca(produto.getMarca());
					p.setPreco(produto.getPreco());
					p.setQuantidade(produto.getQuantidade());
					return true;
				}
			}
		}
		return false;
	}

	private boolean isNomeRepetido(Produto produto) {
		if (produto != null) {
			for (Produto p : produtos) {
				if (p != null && p.getNome().equals(produto.getNome())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean excluir(int codigo) {
		for (Produto p : produtos) {
			if (p != null && p.getCodigo() == codigo) {
				if (!p.getStatusVendido()) {
					produtos.remove(p);
					return true;
				}
			}
		}
		return false;
	}

	// Cria um vetor com todos os produtos cadastrados
	public List<Produto> listarTodos() {
		List<Produto> produtosAux = new ArrayList<>();

		for (Produto p : produtos) {
			if (p != null && !p.getStatusExcluido()) {
				produtosAux.add(new Produto(p));
			}
		}

		return produtosAux;
	}

	public List<Produto> copiaProdutos() {
		List<Produto> produtosAux = new ArrayList<>();

		for (Produto p : produtos) {
			if (p != null) {
				produtosAux.add(new Produto(p));
			}
		}

		return produtosAux;
	}

	public Produto buscaCod(int codigoProduto) {
		for (Produto p : produtos) {
			if (p != null && !p.getStatusExcluido() && p.getCodigo() == codigoProduto) {
				return new Produto(p);
			}
		}
		return null;
	}

	public Produto buscaNome(String nomeProduto) {
		for (Produto p : produtos) {
			if (p != null && !p.getStatusExcluido() && p.getNome().equals(nomeProduto)) {
				return new Produto(p);
			}
		}
		return null;
	}

	public boolean isVazio() {
		return produtos.isEmpty();
	}
}
