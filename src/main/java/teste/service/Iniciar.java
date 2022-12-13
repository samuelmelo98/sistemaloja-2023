package teste.service;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.inject.Inject;

import config.Config;
import teste.controle.ClienteBean;
import teste.model.Cliente;

public class Iniciar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ClienteBean obj;
	
	static Cliente cli = new Cliente(1000, "sam", "sobreNomeCliente", "emailCliente", "telefoneCliente", "cidadeCliente", "enderecoCliente", "cpfCliente");
	 static Cliente cli2 =new Cliente();
	
	public static void main(String[] args) {
		//List<Cliente> lista = new ArrayList<Cliente>();
		System.out.println("main funcionando");
		//ClienteService serv = new ClienteService();
		//lista =serv.pesquisar();
		
		//for(Cliente c : lista) {
			
		//	System.out.println(c.getCpfCliente());
		//}
		 
		
		//System.out.println(serv.adicionarCliente(null));
		
		
		Config config = new Config();
		Properties props = config.loadProperties();
		String var = props.getProperty("variavel2");
		System.out.println(var);
		
				
		
		
	     cli.gravarAlgo();
	      
	     
	      cli2 = cli2.lerClienteSalvo(1000);
	      System.out.println(cli2.getIdCliente());
	}
	
	public String gravarAlgo() {
		String ret = "Sucesso!";
		try {
			FileOutputStream file = new FileOutputStream("d:/inicio/"+ cli.getIdCliente());
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(this);
			stream.flush();
		} catch (Exception e) {
			ret ="erro"+ e.toString();
		}

		return ret;
	}





}
