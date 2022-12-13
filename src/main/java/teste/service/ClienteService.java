package teste.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import teste.model.Cliente;


public class ClienteService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
	//EntityManager em = emf.createEntityManager();
	
	public Boolean adicionarCliente(Cliente cliente) {
		if(cliente != null) {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(cliente);
				em.getTransaction().commit();
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally {
				em.close();
				emf.close();
			}
		}else {
			return false;
		}
		
	}

	public Cliente buscarClientePorId(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
		EntityManager em = emf.createEntityManager();
		try {
			return cliente = em.find(Cliente.class, cliente.getIdCliente());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public List<Cliente> pesquisar() {
		

/*
	em.getTransaction();
		List<Cliente> result = em.createQuery("from Cliente").getResultList();
		for (Cliente cliente : (List<Cliente>) result) {
			System.out.println("Cliente (" + cliente.getIdCliente() + ") : " + cliente.getNomeCliente());
		}
		em.getTransaction().commit();
		em.close();*/
///
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
		EntityManager em = emf.createEntityManager();
		
	
		try {
			
			
			
			em.getTransaction().begin();
			List<Cliente> result = em.createQuery("from Cliente", Cliente.class).getResultList();
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
		
		/*
		*****ublic List<Cliente> verificaExistenciaDeCliente() {
			try {
				return (List<Cliente>) em.createQuery("select cpfCliente from cliente");
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				em.close();
				emf.close();
			}
	}
	*/
	
	public Boolean remove(Cliente cliente) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
		EntityManager em = emf.createEntityManager();
		try {
			cliente = em.find(Cliente.class,cliente.getIdCliente());
			em.getTransaction().begin();//entidade monitorada
			em.remove(cliente);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			em.close();
			emf.close();
		}
	}

}
