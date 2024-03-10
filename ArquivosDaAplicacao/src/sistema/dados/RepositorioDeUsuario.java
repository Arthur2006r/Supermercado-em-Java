package sistema.dados;

import java.util.ArrayList;
import java.util.List;

import sistema.models.usuario.Admin;
import sistema.models.usuario.Atendente;
import sistema.models.usuario.Usuario;

public class RepositorioDeUsuario {
	private static int quantidadeUsuarios = 0;

	private List<Usuario> usuarios;

	public RepositorioDeUsuario() {
		this.usuarios = new ArrayList<>();
	}

	public int gerarCodigoDoUsuario() {
		quantidadeUsuarios++;
		return quantidadeUsuarios;
	}

	public void adicionarVendaAtendente(int codigo) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getCodigo() == codigo) {
				Atendente atd = (Atendente) usuario;
				atd.adicionarVenda();
				return;
			}
		}
	}

	public int logarAdmin(String login, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)
					&& usuario instanceof Admin) {
				return usuario.getCodigo();
			}
		}

		return -1;
	}

	public int logarAtendente(String login, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)
					&& usuario instanceof Atendente) {
				return usuario.getCodigo();
			}
		}

		return -1;
	}

	public boolean isUnicoAdmin(int codigo) {
		int contador = 0;
		boolean unico = false;

		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario instanceof Admin) {
				contador++;
				if (usuario.getCodigo() == codigo) {
					unico = true;
				}
			}
		}

		return contador == 1 && unico;
	}

	public boolean excluirUsuario(int codigo) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getCodigo() == codigo) {
				usuarios.remove(usuario);
				return true;
			}
		}
		return false;
	}

	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario != null) {
			usuarios.add(usuario);
			return true;
		}

		return false;
	}

	private boolean validarLoginUsuario(Usuario usuario) {
		if (usuario != null) {
			for (Usuario u : usuarios) {
				if (u != null && u.getLogin().equals(usuario.getLogin())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> usuariosAux = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			if (usuario != null) {
				if (usuario instanceof Atendente) {
					usuariosAux.add(new Atendente((Atendente) usuario));
				} else if (usuario instanceof Admin) {
					usuariosAux.add(new Admin((Admin) usuario));
				}
			}
		}

		return usuariosAux;
	}

	public boolean atualizarDataLogadoAdmin(int codigoAdmin) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getCodigo() == codigoAdmin) {
				Admin admin = (Admin) usuario;
				admin.logar();
				return true;
			}
		}

		return false;
	}

	public Atendente pegarAtendenteCopia(int codigoAtendente) {
		for (Usuario usuario : usuarios) {
			if (usuario != null && usuario.getCodigo() == codigoAtendente) {
				Atendente atd = (Atendente) usuario;
				return new Atendente(atd);
			}
		}
		return null;
	}
}
