package teste.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.jboss.weld.bootstrap.spi.EEModuleDescriptor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String nomeCliente;
	private String sobreNomeCliente;
	@Column(unique = true, nullable = false)
	private String cpfCliente;
	private String emailCliente;
	private String telefoneCliente;
	private String cidadeCliente;
	private String enderecoCliente;

	public Cliente() {
	}

	public Cliente(Integer idCliente, String nomeCliente, String sobreNomeCliente, String emailCliente,
			String telefoneCliente, String cidadeCliente, String enderecoCliente, String cpfCliente) {

		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.sobreNomeCliente = sobreNomeCliente;
		this.emailCliente = emailCliente;
		this.telefoneCliente = telefoneCliente;
		this.cidadeCliente = cidadeCliente;
		this.enderecoCliente = enderecoCliente;
		this.cpfCliente = cpfCliente;
	}
	/*
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSobreNomeCliente() {
		return sobreNomeCliente;
	}

	public void setSobreNomeCliente(String sobreNomeCliente) {
		this.sobreNomeCliente = sobreNomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	*/

	@Override

	public String toString() {

		return "ID_Cliente: " + idCliente + " " + "Nome Cliente: " + nomeCliente + " " + "Sobre nome Cliente: "
				+ sobreNomeCliente + " " + "Email Cliente: " + emailCliente + " " + "Telefone Cliente: "
				+ telefoneCliente + " " + "Cidade Cliente" + cidadeCliente + " " + "Endereço Cliente: "
				+ enderecoCliente + " ";

	}
	
	public String gravarAlgo() {
		String ret = "Sucesso!";
	
		
		try {
			//aqui dou o nome ao meu diretorio observe que nao extensão txt, doc
			//ai se trata de uma pasta
			File myDir = new File("d:/mydir");
			//isso aqui é importante criei minha pasta - diretório
			myDir.mkdir();
				//aqui ja definir o nome do meu arquivo
				//dentro do diretorio olhe o nome do objeto myDir
				File myFile = new File(myDir, "arq.txt");
				//crio o arquivo
				myFile.createNewFile();
			
			//String localArquivo =System.getProperty("d:/inicio");
			FileOutputStream file = new FileOutputStream("d:/inicio/teste"+ this.getIdCliente());
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(this);
			stream.flush();
		} catch (Exception e) {
			ret = "erro" + e.toString();
		}

		return ret;
	}
	
	public static Cliente lerClienteSalvo(int codigo) {
		try {
			FileInputStream file = new FileInputStream("d:/inicio/teste"+codigo);
			ObjectInputStream stream = new ObjectInputStream(file);
			
			return ((Cliente) stream.readObject());
		}catch(Exception e) {
			System.out.println("Falha ao ler arquivo"+ e.toString());
			return null;
		}
	}

}
