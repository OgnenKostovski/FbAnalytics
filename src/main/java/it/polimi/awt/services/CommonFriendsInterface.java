package it.polimi.awt.services;


import it.polimi.awt.domain.Friend;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

public interface CommonFriendsInterface {
	public List<Friend> createCommonFriends(Facebook facebook,
			String[] idSelected, Friend user, Friend selectedFriend, List<Friend> mutualFriends);

	public List<Friend> getCommonFriends();
}
