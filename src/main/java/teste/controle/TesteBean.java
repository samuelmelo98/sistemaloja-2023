package teste.controle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ToggleEvent;

import teste.model.Teste;

@Named("testeBean")
@SessionScoped
public class TesteBean implements Serializable{
    private static final long serialVersionUID = 1L;
    @Inject
    private Teste teste;
    private List<Teste> testes = new ArrayList<>();
    
    public String navegar() {
        
        System.out.println("Chamou");
        return "primefaces/test/Sucesso";
        
    }
public void navegarVoltar() {
        
        System.out.println("Chamou voltar");
       // return "teste/index.xhtml";
        try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
public void addPessoa() {
	
	try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("primefaces/CadastroPessoa.xhtml");
		//FacesContext.getCurrentInstance().getExternalContext().redirect("primefaces/TextArea.xhtml");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
	}
}
public void mostrar(ToggleEvent event) {
	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso!","Visibilidade"+event.getVisibility());
	FacesContext.getCurrentInstance().addMessage(null, msg);
	System.out.println("mostar()");
}

}
