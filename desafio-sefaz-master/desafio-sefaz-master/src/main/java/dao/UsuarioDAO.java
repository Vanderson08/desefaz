package dao;

import javax.ejb.Stateless;

import model.Usuario;

@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario>{

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
}
