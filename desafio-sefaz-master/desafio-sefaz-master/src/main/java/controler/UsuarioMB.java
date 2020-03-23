package controler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import model.Telefones;
import model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuarioDAO dao = new UsuarioDAO();
	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Telefones telefone = new Telefones();
	private List<Telefones> telefones = new ArrayList<Telefones>();
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public Telefones getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefones telefone) {
		this.telefone = telefone;
	}

	public UsuarioMB() {
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Verifica se já existe um usuário com o e-mail informado
	 * caso exista, exibe mensagem de e-mail já cadastrado
	 * não existindo inclui o usuário e redireciona para a pagina de manutenção
	 */
	public void salvar() {
		try {
			telefones.add(telefone);
			usuario.setTelefone(telefones);
			String jpql = "select u from Usuario u where u.email = :email";
					
			if(dao.consultajql(jpql, "email", usuario.getEmail()) == null) {
				dao.incluir(usuario);				
				FacesContext.getCurrentInstance().getExternalContext().redirect("manter.jsf");
			}else {
				FacesContext.getCurrentInstance().addMessage("",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado!", "E-mail já cadastrado!"));
			}
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * Altera os dados do usuário
	 */
	public void alterar() {
		dao.alterar(usuario);
	}
	
	/**
	 * Exclui o usuário selecionado
	 */
	public void remover() {
		dao.remover(usuario.getId());
	}
	
	/**
	 * Lista todos os usuários cadastrados
	 * @return usuários
	 */
	public List<Usuario> getUsuarios(){
		usuarios = dao.listarTodos();
		return usuarios;
		
	}
	
	/**
	 * Redireciona para a pagina de cadastro
	 * @throws IOException
	 */
	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.jsf");
	}
	
}
