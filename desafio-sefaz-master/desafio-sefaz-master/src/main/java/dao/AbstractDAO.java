package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Usuario;

public abstract class AbstractDAO<T> {

	@PersistenceContext(unitName = "desafiosefaz")
	private EntityManager entityManager;
	public Class<T> tipoClass;

	public AbstractDAO() {
	}

	public AbstractDAO(Class<T> tipoClass) {
		this.tipoClass = tipoClass;
	}

	public void incluir(Usuario usuario) {
		entityManager.persist(usuario);

	}

	public void alterar(Usuario usuario) {
		entityManager.merge(usuario);

	}

	public void remover(Integer id) {
		Object registro = entityManager.find(tipoClass, id);
	
		entityManager.remove(registro);

	}

	public List<T> listarTodos() {
		StringBuilder sql = new StringBuilder();
		sql.append("from ").append(tipoClass.getSimpleName());

		TypedQuery<T> queryListarTodos = entityManager.createQuery(sql.toString(), tipoClass);

		return queryListarTodos.getResultList();
	}	
	
	public Object consultajql(String jpql, String param, String valor) {
		try {
			TypedQuery<T> typedQuery = entityManager.createQuery(jpql, tipoClass).setParameter(param, valor);
			return typedQuery.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}


}
