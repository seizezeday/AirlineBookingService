package bionic.project.abo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.User;


@Repository
public class UserDAO extends GenericDAO<User> implements Serializable{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<User> findUser(String login, String password){
		TypedQuery<User> query = em.createQuery(
						"SELECT u FROM User u WHERE u.login = :login and u.password = :password",
						User.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		
		List<User> u = query.getResultList();

		return u;
		
	}
	
	public List<User> showAllUsers(){
		return em.createQuery("from User").getResultList();
		
	}
	
	

}
