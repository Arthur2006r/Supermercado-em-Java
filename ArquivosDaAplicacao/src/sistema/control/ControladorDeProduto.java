package sistema.control;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sistema.dados.RepositorioProduto;
import sistema.models.Produto;
import sistema.view.View;
import sistema.view.ViewProduto;

public class ControladorDeProduto {
	private View view;
	private ViewProduto viewProduto;
	
	private RepositorioProduto repositorioProduto;

	public ControladorDeProduto() {
		this.view = new View();
		this.viewProduto = new ViewProduto();

		this.repositorioProduto = new RepositorioProduto();
	}

	public void inserir() {
		Produto produto = viewProduto.lerProduto(this);

		if (produto != null) {
			if (!isNomeRepetido(produto)) {
				if (repositorioProduto.inserir(produto)) {
					view.print("Produto inserido com sucesso!");
					return;
				}
			}
		}

		view.print("Não foi possível inserir este produto!");
	}

	public void inserir(Produto produto) {
		if (produto != null) {
			if (!isNomeRepetido(produto)) {
				repositorioProduto.inserir(produto);
			}
		}
	}

	public void excluir() {
		if (!repositorioProduto.isVazio()) {
			int codigo = viewProduto.excluir();

			if (repositorioProduto.excluir(codigo)) {
				view.print("Produto excluido com sucesso!");
			} else {
				view.print("Não foi possível excluir este produto!");
			}
		} else {
			view.print("Não há produtos cadastrados");
		}

	}

	public void alterar() {
		if (!repositorioProduto.isVazio()) {
			int codigo = viewProduto.alterar();
			Produto produtoCopia = repositorioProduto.buscaCod(codigo);

			if (produtoCopia != null) {
				viewProduto.lerAlteracaoProduto(produtoCopia);
				if (validar(produtoCopia)) {
					if (repositorioProduto.alterar(produtoCopia)) {
						view.print("Produto alterado com sucesso!");
						return;
					}
				}
			} else {
				view.print("Produto não encontrado!");
			}
		} else {
			view.print("Não há produtos cadastrados!");
		}

		view.print("Não foi possível alterar este produto!");
	}

	public void listarTodosOrdemCadastro() {
		List<Produto> produtosCopia = repositorioProduto.listarTodos();

		if (produtosCopia != null) {
			int largura = view.lerLarguraDasColunas();

			viewProduto.listarProdutos(ordemCadastro(produtosCopia), largura);
		} else {
			view.print("Não foi possível exibir os produtos!");
		}
	}

	private List<Produto> ordemCadastro(List<Produto> produtosCopia) {
		if (produtosCopia != null) {
			Collections.sort(produtosCopia, new Comparator<Produto>() {
				@Override
				public int compare(Produto produto1, Produto produto2) {
					if (produto1 != null && produto2 != null) {
						return Integer.compare(produto1.getCodigo(), produto2.getCodigo());
					}
					return 0;
				}
			});
		}

		return produtosCopia;
	}

	public void listarTodosProdutosOrdemNome() {
		List<Produto> produtosCopia = repositorioProduto.listarTodos();

		if (produtosCopia != null) {
			int largura = view.lerLarguraDasColunas();

			viewProduto.listarProdutos(ordemNome(produtosCopia), largura);
		} else {
			view.print("Não foi possível exibir os produtos!");
		}
	}

	private List<Produto> ordemNome(List<Produto> produtosCopia) {
		if (produtosCopia != null) {
			Collections.sort(produtosCopia, new Comparator<Produto>() {
				@Override
				public int compare(Produto produto1, Produto produto2) {
					if (produto1 != null && produto2 != null) {
						String nome1 = produto1.getNome() != null ? produto1.getNome().toLowerCase() : "";
						String nome2 = produto2.getNome() != null ? produto2.getNome().toLowerCase() : "";
						return nome1.compareTo(nome2);
					}
					return 0;
				}
			});
		}

		return produtosCopia;
	}

	public void listarProduto(Produto produtoCopia) {
		int largura = view.lerLarguraDasColunas();

		view.print("Produto selecionado: ");
		viewProduto.listarProduto(produtoCopia, largura);
		view.print("...");

	}

	public Produto buscar() {
		int opcao = viewProduto.buscar();

		if (opcao == 1) {
			String nome = viewProduto.buscarNome();
			return repositorioProduto.buscaNome(nome);
		} else if (opcao == 2) {
			int codigo = viewProduto.buscarCod();
			return repositorioProduto.buscaCod(codigo);
		}

		return null;
	}

	// terminar
	public Produto buscaNome(String nomeProduto) {
		return repositorioProduto.buscaNome("n");
	}

	// terminar
	public Produto buscaCod(int codigoProduto) {
		return repositorioProduto.buscaCod(codigoProduto);
	}

	private boolean validar(Produto produtoCopia) {
		if (produtoCopia != null) {
			if (produtoCopia.getPreco() > 0 && produtoCopia.getQuantidade() >= 0 && produtoCopia.getNome() != null
					&& produtoCopia.getMarca() != null) {
				return true;
			}
		}

		return false;
	}

	// Função que passa por todo o vetor de produto para saber se o nome lido já foi
	// utilizado
	public boolean isNomeRepetido(Produto produto) {
		List<Produto> produtos = repositorioProduto.copiaProdutos();

		for (Produto prod : produtos) {
			if (prod != null && prod.getNome().equalsIgnoreCase(produto.getNome())) {
				if (prod.getCodigo() != produto.getCodigo()) {
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

	public boolean isCheioProdutos() {
		List<Produto> produtos = repositorioProduto.copiaProdutos();

		for (Produto prod : produtos) {
			if (prod == null) {
				return false;
			}
		}

		return true;
	}

	public int gerarCodigoDoProduto() {
		return repositorioProduto.gerarCodigoDoProduto();
	}
}
