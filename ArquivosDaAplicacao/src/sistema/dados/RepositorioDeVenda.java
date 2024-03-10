package sistema.dados;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sistema.models.Item;
import sistema.models.Produto;
import sistema.models.Venda;

public class RepositorioDeVenda {
	private static int quantidadeVendas = 0;

	private List<Venda> vendas;

	public RepositorioDeVenda() {
		this.vendas = new ArrayList<>();
	}

	// gera o código da venda
	// Função que passa por todo o vetor de vendas para saber se o código gerado já
	// foi utilizado
	public int gerarCodigoDaVenda() {
		quantidadeVendas++;
		return quantidadeVendas;
	}

	public boolean inserirVenda(Venda venda) {
		if (baixaNosProdutos(venda)) {
			vendas.add(venda);
			return true;
		}
		return false;
	}

	private boolean baixaNosProdutos(Venda venda) {
		for (Item item : venda.getItensVendidos()) {
			if (item != null) {
				Produto produto = item.getProduto();

				if (produto != null) {
					produto.setStatusVendido(true);

					if (produto.getQuantidade() >= item.getQuantidade()) {
						produto.baixaProduto(item.getQuantidade());
					}
				}
			}
		}

		return true;
	}

	// Cria um vetor com todos os produtos cadastrados
	public List<Venda> listarTodasVendas() {
		List<Venda> vendasAux = new ArrayList<>();

		for (Venda venda : vendas) {
			vendasAux.add(new Venda(venda));
		}

		return vendasAux;
	}

	public List<Venda> listarTodasVendasDoDia(Date data) {
		List<Venda> vendasAux = new ArrayList<>();

		for (Venda venda : vendas) {
			if (conferirDataIgual(venda, data)) {
				vendasAux.add(new Venda(venda));
			}
		}

		return vendasAux;
	}

	public Venda buscarVenda(int codigo) {
		for (Venda venda : vendas) {
			if (venda.getCodigo() == codigo) {
				return new Venda(venda);
			}
		}
		return null;
	}

	public boolean conferirDataIgual(Venda venda, Date data) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatadaDaVenda = dateFormat.format(venda.getData());

		String dataFormatadaDesejada = dateFormat.format(data);

		if (dataFormatadaDaVenda.equals(dataFormatadaDesejada)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isVazio() {
		return vendas.isEmpty();
	}
}
