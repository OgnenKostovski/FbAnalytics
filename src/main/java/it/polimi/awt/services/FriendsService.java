package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;
import it.polimi.awt.repository.FriendsJPARepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ComponentScan("it.polimi.awt.repository")
public class FriendsService implements FriendsServiceInterface {

	@Autowired
	private FriendsJPARepository friendsJPAr;

	@Override
	public void addFriends(List<Friend> friendsList) {
		friendsJPAr.addFriends(friendsList);
	}

	@Override
	public void addCommonFriends(List<Friend> commonFriendsList) {
		friendsJPAr.addCommonFriends(commonFriendsList);
	}

	@Override
	public void addUser(Friend user) {
		friendsJPAr.addUser(user);
	}
	
	@Override
	public List<Friend> getUserFriends(Friend fbUser){
		return friendsJPAr.getUserFriends(fbUser);
	}
	
	@Override
	public void flushFriends() {
		friendsJPAr.flushFriends();
		return ;
	}

	public FriendsService() {
	};
}
