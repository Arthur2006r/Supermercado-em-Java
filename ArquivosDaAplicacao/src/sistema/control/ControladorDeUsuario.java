package sistema.control;

import java.util.List;

import sistema.dados.RepositorioDeUsuario;
import sistema.models.usuario.Atendente;
import sistema.models.usuario.Usuario;
import sistema.view.View;
import sistema.view.ViewUsuario;

public class ControladorDeUsuario {
	private View view;
	private ViewUsuario viewUsuario;

	private RepositorioDeUsuario repositorUsuario;

	public ControladorDeUsuario() {
		this.view = new View();
		this.viewUsuario = new ViewUsuario();
		
		this.repositorUsuario = new RepositorioDeUsuario();
	}

	public void loginAdmin() {
		view.print("> LOGIN de Admin");
		String login = viewUsuario.leituraLogin();
		String Senha = viewUsuario.leituraSenha();
		int codigo = repositorUsuario.logarAdmin(login, Senha);

		if (codigo > 0) {
			view.print("O login ocorreu com sucesso!\n...");

			repositorUsuario.atualizarDataLogadoAdmin(codigo);

			Sistema.getInstance().menuAdministrador(codigo);
		} else {
			view.print("Login ou senha inválidos! Tente novamente mais tarde.");
		}
	}

	public void loginAtendente() {
		view.print("> LOGIN de Atendente");
		String login = viewUsuario.leituraLogin();
		String Senha = viewUsuario.leituraSenha();
		int codigo = repositorUsuario.logarAtendente(login, Senha);

		if (codigo > 0) {
			view.print("O login ocorreu com sucesso!\n...");

			Sistema.getInstance().menuAtendente(codigo);
		} else {
			view.print("Login ou senha inválidos! Tente novamente mais tarde.");
		}
	}

	public void excluirUsuario(int codigoAdmin) {
		int codigo = viewUsuario.lerCodigo();

		if (!repositorUsuario.isUnicoAdmin(codigo)) {
			if (repositorUsuario.excluirUsuario(codigo)) {
				if (codigoAdmin == codigo) {
					view.print("Você acabou de excluir sua conta. Você será redirecionado para a tela de login!");
					// return 0;
				} else {
					view.print("Usuário removido com sucesso!");
				}
			} else {
				view.print("Não foi possível remover o usuário! Tente novamente mais tarde");
			}
		} else {
			view.print(
					"Não é possível excluir o único admin cadastrado! Se fazê-lo, ficará incapacitado de usar o sistema de admin.");
		}
	}

	public void cadastrarUsuario() {
		Usuario usuario = viewUsuario.lerUsuario(repositorUsuario);

		if (usuario != null) {
			if (repositorUsuario.cadastrarUsuario(usuario)) {
				view.print("O cadastro ocorreu com sucesso!");
			} else {
				view.print("Não foi possível executar o cadastro!");
			}
		} else {
			view.print("Houve um falha ao ler os dados do usuário, tente novamente mais tarde");
		}
	}

	public void cadastrarUsuario(Usuario usuario) {
		if (usuario != null) {
			repositorUsuario.cadastrarUsuario(usuario);
		}
	}

	public void listarUsuarios() {
		List<Usuario> usuarios = repositorUsuario.listarTodosUsuarios();
		if (usuarios != null) {
			int largura = view.lerLarguraDasColunas();

			viewUsuario.listarUsuarios(usuarios, largura);
		} else {
			view.print("Não há usuários com essa data!");
		}
	}

	public Atendente pegarAtendenteCopia(int codigoAtendente) {
		return repositorUsuario.pegarAtendenteCopia(codigoAtendente);
	}

	public int gerarCodigoDoUsuario() {
		return repositorUsuario.gerarCodigoDoUsuario();
	}
}
