package teste.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import teste.model.UsuarioSistemaLogin;
import teste.service.UsuarioSistemaLoginService;
@Named
@Data
@SessionScoped
public class UsuarioSistemaLoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioSistemaLogin usuariosistemalogin;
	@Inject
	private UsuarioSistemaLoginService usuariosistemaloginservice;
	private List<UsuarioSistemaLogin> listaUsuarios;

	
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
	
	public void validarUsuario() {
		Boolean control = true;
		listaUsuarios =new ArrayList<UsuarioSistemaLogin>();
		listaUsuarios = usuariosistemaloginservice.pequisarUsuario();
		for(UsuarioSistemaLogin x:listaUsuarios) {
			if(x.getUsuarioNome().equals(usuariosistemalogin.getUsuarioNome())&& x.getUsuarioSenha().equals(usuariosistemalogin.getUsuarioSenha())) {
			System.out.println("Achou");
			FacesMessage msg = new FacesMessage("Bem Vindo!", usuariosistemalogin.getUsuarioNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			control = false;
			break;
			
			}
		}
			if(control) {
				FacesMessage msg = new FacesMessage("Erro!", "Usuário não encontrado!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		
		
	}
		
	
	
	public void limpar() {
		usuariosistemalogin = new UsuarioSistemaLogin();
		
		System.out.println("ok");
	}
	
}
