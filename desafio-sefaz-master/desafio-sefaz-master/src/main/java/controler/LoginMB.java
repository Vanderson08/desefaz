package controler;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import dao.UsuarioDAO;
import model.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioDAO dao = new UsuarioDAO();
	private String email = "";
	private String senha = "";

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

	/**
	 * Redireciona para a tela de cadastro
	 * @throws IOException
	 */
	public void redirect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.jsf");
	}

	/**
	 * verifica e-mail e senha. 
	 * Caso o e-mail não exista, exibe mensagem de login inválido
	 * Caso o e-mail exista mas a senha esteja errada, exibe mensagem de senha incorreta
	 * Caso o login esteja correto, redireciona para a pagina de manutenção de usuário
	 * @throws IOException
	 */
	public void login() throws IOException {

		String jpql = "select u from Usuario u where u.email = :email";

		try {
			Usuario usuario = new Usuario();

			Object objet = dao.consultajql(jpql, "email", email);

			if (objet == null) {
				throw new NoResultException("Usuário não cadastrado");
			} else {
				usuario = (Usuario) objet;
				if ((usuario.getSenha().equals(senha))) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("manter.jsf");
				}else {
					FacesContext.getCurrentInstance().addMessage("",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!", "Senha incorreta!"));
				}
			}
		} catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido!", "Login inválido!"));
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

}
