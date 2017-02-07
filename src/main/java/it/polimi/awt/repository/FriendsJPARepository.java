package it.polimi.awt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import it.polimi.awt.domain.Friend;

@ImportResource("classpath:dataSource-context.xml")
@Repository
public class FriendsJPARepository implements FriendsJPAInterface{
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public void addFriends(List<Friend> FriendsList) {
		int i;
		for (i = 0; i < FriendsList.size(); i++) {
			em.merge(FriendsList.get(i));
		}
	}
	
	@Transactional
	public void addCommonFriends(List<Friend> commonFriendsList) {
		int i;
		for (i = 0; i < commonFriendsList.size(); i++) {
			em.persist(commonFriendsList.get(i));
		}
	}

	@Transactional
	public void addUser(Friend user) {
		em.merge(user);
	}
	
	@Transactional
	public List<Friend> getUserFriends(Friend fbUser){
		Query q = em.createQuery("SELECT f FROM Friend f WHERE f.userId= ?1");
		q.setParameter(1, fbUser.getFacebookId());
		List<Friend> fbuserFriends = q.getResultList();
		return fbuserFriends;
	}
	
	@Transactional
	public void flushFriends(){
		em.flush();
	}
}
