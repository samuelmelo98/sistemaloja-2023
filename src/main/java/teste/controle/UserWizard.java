package teste.controle;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import teste.model.User;
@Named
@SessionScoped
public class UserWizard implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private User user; //= new User();

	    private boolean skip;

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public void save() {
	        FacesMessage msg = new FacesMessage("Adicionado com Sucesso!", "Bem vindo :" + user.getNomeCliente());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        System.out.println(user.getNomeCliente());
	        user = new User();
	        System.out.println(user.getNomeCliente());
	        try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../../index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public boolean isSkip() {
	        return skip;
	    }

	    public void setSkip(boolean skip) {
	        this.skip = skip;
	    }

	    public String onFlowProcess(FlowEvent event) {
	        if (skip) {
	            skip = false; //reset in case user goes back
	            return "confirm";
	        }
	        else {
	            return event.getNewStep();
	        }
	    }

}
