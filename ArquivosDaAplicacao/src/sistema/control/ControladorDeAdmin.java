package sistema.control;

import java.util.Date;

import sistema.models.usuario.Admin;

public class ControladorDeAdmin {
    private Admin admin;

    public ControladorDeAdmin(int codigo, String nome, String login, String senha) {
        this.admin = Admin.getInstance(codigo, nome, login, senha);
    }

    public void logarAdmin() {
        admin.logar();
    }

    public Date getUltimoLogin() {
        return admin.getUltimoLogin();
    }
}
