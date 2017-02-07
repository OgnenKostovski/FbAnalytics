package it.polimi.awt.repository;

import java.util.List;

import it.polimi.awt.domain.Friend;

public interface FriendsJPAInterface {

	
	public interface FriendsRepository {

		public void addFriends(List<Friend> FriendsList);

		public void addCommonFriends(List<Friend> CommonFriendsList);

		public void addUser(Friend user);
		
		public List<Friend> getUserFriends(Friend fbUser);
		
		public void flushFriends();
		
	}
}
