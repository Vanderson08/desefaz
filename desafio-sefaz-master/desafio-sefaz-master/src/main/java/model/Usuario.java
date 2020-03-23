package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_id")
	@NotNull
	private List<Telefones> telefones;
	
	public Usuario() {
	}

	public Usuario(Integer id, String nome, String email, String senha, List<Telefones> telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefones = telefone;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Telefones> getTelefones() {
		return telefones;
	}
	
	public void setTelefone(List<Telefones> telefones) {
		this.telefones = telefones;
	}
	
}
