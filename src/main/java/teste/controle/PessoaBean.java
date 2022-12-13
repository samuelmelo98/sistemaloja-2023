package teste.controle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import teste.model.Pessoa;

@Named("bean")
@SessionScoped
public class PessoaBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Pessoa            pessoa;
    
    private List<Pessoa>      pessoas          = new ArrayList<Pessoa>();

    public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void adicionar()
    {

       System.out.println("Adicionar Pessoa!"+pessoa.getId()+":"+pessoa.getNome());
       pessoas.add(pessoa);
       
       if(pessoas.size() > 5) {
    	   limparLista();
       }
      
        limpar();

    }

    public void limpar()
    {
        pessoa = new Pessoa();
       
    }
    
    public void limparLista() {
    	pessoas.clear();
    	//FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("sam"));    
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    

   

}
