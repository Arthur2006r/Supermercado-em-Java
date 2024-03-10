package sistema.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sistema.models.usuario.Atendente;

public class Venda {
	private String nomeCliente;
	private int codigo;
	private Date data;
	private List<Item> itensVendidos;
	private Atendente atd;

	private Venda(Carrinho carrinho, int codigo, String nomeCliente, Date data, Atendente atd) {
		this.codigo = codigo;
		this.nomeCliente = nomeCliente;
		this.data = data;
		this.atd = atd;
		this.itensVendidos = carrinho.getItens();
	}

	public Venda(Venda venda) {
		this.codigo = venda.codigo;
		this.nomeCliente = venda.nomeCliente;
		this.data = venda.data;
		this.itensVendidos = new ArrayList<>(venda.itensVendidos);
		this.atd = venda.atd;
	}

	public static Venda getInstance(Carrinho carrinho, int codigo, String nomeCliente, Date data, Atendente atd) {
		if (carrinho.isCarrinhoValido() && nomeCliente != null && atd != null) {
			return new Venda(carrinho, codigo, nomeCliente, data, atd);
		} else {
			return null;
		}
	}

	// GETTERS e SETTERS
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		if (nomeCliente != null) {
			this.nomeCliente = nomeCliente;
		}
	}

	public int getCodigo() {
		return codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		if (data != null) {
			this.data = data;
		}
	}

	public List<Item> getItensVendidos() {
		return new ArrayList<>(itensVendidos);
	}

	public Atendente getAtd() {
		return atd;
	}

	public int quantidadeDeItens() {
		int quantidade = 0;

		for (Item item : itensVendidos) {
			if (item != null) {
				quantidade += item.getQuantidade();
			}
		}

		return quantidade;
	}
}
