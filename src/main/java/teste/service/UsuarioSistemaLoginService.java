package teste.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import teste.model.Cliente;
import teste.model.UsuarioSistemaLogin;

public class UsuarioSistemaLoginService implements Serializable {

	private static final long serialVersionUID = 1L;

	public Boolean adicionarUsuario(UsuarioSistemaLogin usuario) {
		if (!(usuario.getUsuarioNome().isEmpty() || usuario.getUsuarioSenha().isEmpty())) {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(usuario);
				em.getTransaction().commit();
				return true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				em.close();
				emf.close();
			}
		} else {
			return false;
		}

	}

	public Boolean remover(UsuarioSistemaLogin usuario) {
		
		// isEmpty -- verifica se a string é vazio. --> se vazio retorna true
		if (!(usuario.getUsuarioNome().isEmpty() || usuario.getUsuarioSenha().isEmpty())) {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
			EntityManager em = emf.createEntityManager();
			try {
				usuario = em.find(UsuarioSistemaLogin.class, usuario.getUsuarioId());
				em.getTransaction().begin();// entidade monitorada
				em.remove(usuario);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				em.close();
				emf.close();
			}
		} else {
			return false;
		}
	}
	
	public List<UsuarioSistemaLogin> pequisarUsuario() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			List<UsuarioSistemaLogin> result = em.createQuery("from UsuarioSistemaLogin", UsuarioSistemaLogin.class).getResultList();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			em.getTransaction().commit();
			em.close();

		}
		
	}

}
