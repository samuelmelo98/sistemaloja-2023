package teste.controle;

import java.io.Serializable;

import javax.inject.Inject;

import teste.model.UsuarioSistemaLogin;

public class UsuarioSistemaLoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioSistemaLogin usuariosistemalogin;

}
