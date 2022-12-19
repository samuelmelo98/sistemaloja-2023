package teste.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import lombok.Data;
import teste.model.Notificacao;
import teste.model.UsuarioSistemaLogin;
import teste.service.UsuarioSistemaLoginService;

@Named
@Data
@SessionScoped
public class UsuarioSistemaLoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioSistemaLogin usuariosistemalogin;
	@Inject
	private UsuarioSistemaLoginService usuariosistemaloginservice;
	private List<UsuarioSistemaLogin> listaUsuarios;
	private boolean verifica = false;

	public void adicionarUsuario() {
		listaUsuarios = new ArrayList<UsuarioSistemaLogin>();
		listaUsuarios.add(usuariosistemalogin);
		// trim() remover espaços em branco no inicio e final
		String nome = usuariosistemalogin.getUsuarioNome().trim();
		String senha = usuariosistemalogin.getUsuarioSenha().trim();
		usuariosistemalogin.setUsuarioNome(nome);
		usuariosistemalogin.setUsuarioNome(senha);

		if (usuariosistemaloginservice.adicionarUsuario(usuariosistemalogin)) {
			FacesMessage msg = new FacesMessage("Sucesso!", "Usuário foi Cadastrado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			limpar();
		} else {
			FacesMessage msg = new FacesMessage("Erro!", "Verifique os Campos!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public String validarUsuario() {
		Boolean control = true;
		listaUsuarios = new ArrayList<UsuarioSistemaLogin>();
		listaUsuarios = usuariosistemaloginservice.pequisarUsuario();
		for (UsuarioSistemaLogin x : listaUsuarios) {
			if (x.getUsuarioNome().equals(usuariosistemalogin.getUsuarioNome())
					&& x.getUsuarioSenha().equals(usuariosistemalogin.getUsuarioSenha())) {
				System.out.println("Achou");
				FacesMessage msg = new FacesMessage("Bem Vindo!", usuariosistemalogin.getUsuarioNome());
				FacesContext.getCurrentInstance().addMessage(null, msg);
				control = false;
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
						.getSession(false);
				session.setAttribute("usuario", usuariosistemalogin);
				break;
				// return "/index?faces-redirect=true";

			}

		}
		if (control) {
			FacesMessage msg = new FacesMessage("Erro!", "Usuário não encontrado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

			// FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		}
		// return "/login/login?faces-redirect=true";
		return null;

		// return null;
	}
	// return "/index?faces-redirect=true";

	public void limpar() {
		usuariosistemalogin = new UsuarioSistemaLogin();

		System.out.println("ok");
	}

	public String passarUsuario() {
		String user = usuariosistemalogin.getUsuarioNome();
		return user;
	}
	
	public String validarUsuario2() {
		
		listaUsuarios = new ArrayList<UsuarioSistemaLogin>();
		listaUsuarios = usuariosistemaloginservice.pequisarUsuario();
		for (UsuarioSistemaLogin x : listaUsuarios) {
			if (x.getUsuarioNome().equals(usuariosistemalogin.getUsuarioNome())
					&& x.getUsuarioSenha().equals(usuariosistemalogin.getUsuarioSenha())) {
				FacesMessage msg = new FacesMessage("Bem Vindo!", usuariosistemalogin.getUsuarioNome());
				FacesContext.getCurrentInstance().addMessage(null, msg);
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				verifica = true;
				break;
			}

		}
		if (verifica) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("usuario", usuariosistemalogin);// atributo para sessão
			verifica = false;
			return "/index?faces-redirect=true";
		} else {
			FacesMessage msg = new FacesMessage("Acesso Negado!", usuariosistemalogin.getUsuarioNome() + "Usuário não encontrado!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			Notificacao.msgAviso("Acesso Negado", "Usuário não encontrado!");
			// HttpSession session = (HttpSession)
			// FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			// session.setAttribute("usuario", usuariosistemalogin);
			return "/login/login?faces-redirect=true";
		}

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login/login?faces-redirect=true";
	}
}
