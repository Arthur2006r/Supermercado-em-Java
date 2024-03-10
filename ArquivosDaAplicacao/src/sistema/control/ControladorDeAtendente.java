package sistema.control;

import sistema.dados.RepositorioDeUsuario;

public class ControladorDeAtendente {
	private RepositorioDeUsuario repositorioUsuario;
	
	public ControladorDeAtendente() {
		this.repositorioUsuario = new RepositorioDeUsuario();
	}

	public void procurarAtendenteAdicionarVenda(int codigo) {
		repositorioUsuario.adicionarVendaAtendente(codigo);
	}
}
