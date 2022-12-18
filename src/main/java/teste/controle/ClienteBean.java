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
import teste.model.Notificacao;
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
	private Boolean controleParaClienteExistente = true;

	/*
	 * public Cliente getCliente() { return cliente; }
	 * 
	 * public void setCliente(Cliente cliente) { this.cliente = cliente; }
	 * 
	 * public boolean getSkip() { return skip;
	 * 
	 * }
	 * 
	 * public void setSkip(boolean skip) { this.skip = skip; }
	 * 
	 * public List<Cliente> getListaClientes() { return clientes; }
	 */
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "personal";
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

	public String adicionarCliente() {
		List<Cliente> verificaExistencia = clienteService.pesquisar();
		for (Cliente x : verificaExistencia) {
			if (cliente.getCpfCliente().equals(x.getCpfCliente())) {
				
				Notificacao.msgErro("Erro!","Cliente já existe na base de dados!");
				//FacesMessage msg = new FacesMessage("Erro", "cpf já cadastrado!!");
				//FacesContext.getCurrentInstance().addMessage(null, msg);
				controleParaClienteExistente = false;
				limpar();
				System.out.println(x.getCpfCliente());
				break;
				
			}
		}
		// status = clienteService.adicionarCliente(cliente);
	
		if (controleParaClienteExistente == true) {
			if (clienteService.adicionarCliente(cliente)) {
				Notificacao.msgInformacao("Sucesso!", "Cliente Adicionado com Sucesso!");
				// FacesMessage msg = new FacesMessage("Sucesso", "Cliente Adicionado com
				// Sucesso!");
				// FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			pesquisarClientes();
			limpar();
			controleParaClienteExistente = true;
			// FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			return "index.jsf";
		} else {

			return "index.jsf";
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
		//FacesMessage msg = new FacesMessage("Sucesso!", "Cliente Removido!");
		//FacesContext.getCurrentInstance().addMessage(null, msg);
		Notificacao.msgAviso("Sucesso!", "Cliente removido com sucesso!");
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
