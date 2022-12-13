package teste.controle;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import lombok.Getter;
import lombok.Setter;
import teste.model.Cliente;
import teste.service.ClienteService;

@Named
@SessionScoped
@Getter
@Setter
public class ClienteBean implements Serializable {
  private static final long serialVersionUID = 1L;
	

	@Inject
	private Cliente cliente;
	@Inject
	private ClienteService clienteService;

	private List<Cliente> clientes;

	private boolean skip;
/*
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean getSkip() {
		return skip;

	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<Cliente> getListaClientes() {
		return clientes;
	}
*/
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public void teste() {
		if (skip) {
			setSkip(false);
		} else {
			setSkip(true);
		}
		System.out.println("AQUI");
	}

	public Boolean adicionarCliente() {
		List<Cliente> verificaExistencia = clienteService.pesquisar();

		for (Cliente x : verificaExistencia) {
			if (cliente.getCpfCliente().equals(x.getCpfCliente())) {
				System.out.println("cpf já cadastrado!");
				FacesMessage msg = new FacesMessage("Erro", "cpf já cadastrado!!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				break;
			}
		}

		System.out.println("ok");
		Boolean status = clienteService.adicionarCliente(cliente);
		limpar();
		pesquisarClientes();
		System.out.println(status);
		if (status == true) {
			FacesMessage msg = new FacesMessage("Sucesso", "Cliente Adicionado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			pesquisarClientes();
			return true;

		} else {

			return false;
		}
		// Iniciar.main(null);

		// RequestContext.getCurrentInstance().update("mod"); prime faces 6.1
		// PrimeFaces.current().ajax().update("cliente2:nome");

	}

	public void pesquisarClientePorId(Cliente cliente) {
		this.cliente = clienteService.buscarClientePorId(cliente);
	}

	public void removerBase(Cliente cliente) {

		System.out.println(clienteService.remove(cliente));
		pesquisarClientes();

	}

	public void limpar() {
		cliente = new Cliente();
	}

	public void pesquisarClientes() {

		// this.clientes = new ArrayList<Cliente>() ;
		this.clientes = clienteService.pesquisar();

		for (Cliente x : clientes) {
			System.out.println(x.getNomeCliente());
		}

	}

	public void save() {
		FacesMessage msg = new FacesMessage("Successful", "Welcome :" + cliente.getSobreNomeCliente());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
