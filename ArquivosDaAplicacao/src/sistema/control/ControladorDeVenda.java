package sistema.control;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sistema.dados.RepositorioDeVenda;
import sistema.models.Carrinho;
import sistema.models.Item;
import sistema.models.Venda;
import sistema.models.usuario.Atendente;
import sistema.view.View;
import sistema.view.ViewVenda;

public class ControladorDeVenda {
	private RepositorioDeVenda repositorioVenda;

	private View view;
	private ViewVenda vendaView;

	public ControladorDeVenda() {
		this.repositorioVenda = new RepositorioDeVenda();

		this.view = new View();
		this.vendaView = new ViewVenda();
	}

	public void listarVendas() {
		List<Venda> vendas = repositorioVenda.listarTodasVendas();
		if (vendas != null) {
			int largura = view.lerLarguraDasColunas();

			vendaView.listarVendas(vendas, largura);
		} else {
			view.print("Não há vendas cadastradas!");
		}
	}

	public void finalizarVenda(ControladorDeProduto controleDeProduto, ControladorDeUsuario controleDeUsuario,
			ControladorDeAtendente controleDeAtendente, Carrinho carrinho, int codigoAtendente) {
		if (!repositorioVenda.isVazio()) {
			Date data = view.lerData();

			if (data != null) {
				String nome = vendaView.lerNomeComprador();

				int codigoVenda = repositorioVenda.gerarCodigoDaVenda();

				Atendente atendente = controleDeUsuario.pegarAtendenteCopia(codigoAtendente);

				Venda venda = Venda.getInstance(carrinho, codigoVenda, nome, data, atendente);

				if (venda != null) {
					if (repositorioVenda.inserirVenda(venda)) {
						controleDeAtendente.procurarAtendenteAdicionarVenda(codigoAtendente);
						view.print("A venda foi efetuada com sucesso!");
						return;
					}
				} else {
					int opcao = vendaView.consertarErroVenda();

					if (opcao == 1) {
						int largura = view.lerLarguraDasColunas();

						vendaView.consertarCarrinho(controleDeProduto, carrinho, largura);

						Venda venda2 = Venda.getInstance(carrinho, codigoVenda, nome, data, atendente);

						if (venda2 != null) {
							if (repositorioVenda.inserirVenda(venda2)) {
								controleDeAtendente.procurarAtendenteAdicionarVenda(codigoAtendente);
								view.print("A venda foi efetuada com sucesso!");
							}
						}
					} else {
						view.print("A venda não foi efetuada! - A venda foi cancelada");
					}
				}
			} else {
				view.print("A data digitada é inválida! - A venda foi cancelada");
			}
		} else {
			view.print("Desculpe, ainda não há itens no carrinho! - A venda foi cancelada\n");
		}
	}

	public void listarVendasDoDia() {
		Date data = view.lerData();
		if (data != null) {
			List<Venda> vendas = repositorioVenda.listarTodasVendasDoDia(data);
			if (vendas != null) {
				int largura = view.lerLarguraDasColunas();

				vendaView.listarVendas(vendas, largura);
			} else {
				view.print("Não há vendas com essa data!");
			}
		} else {
			view.print("Data inválida! Tente novamente mais tarde.");
		}
	}

	public void listarVendaCodigo() {
		int codigo = vendaView.lerCodigoVenda();
		Venda venda = repositorioVenda.buscarVenda(codigo);
		if (venda != null) {
			int largura = view.lerLarguraDasColunas();

			vendaView.listarVenda(venda, largura);
		} else {
			view.print("Venda não encontrada!");
		}
	}

	public void inserirVenda(Venda venda) {
		if (venda != null) {
			repositorioVenda.inserirVenda(venda);
		}
	}

	public int gerarCodigoDaVenda() {
		return repositorioVenda.gerarCodigoDaVenda();
	}
}
