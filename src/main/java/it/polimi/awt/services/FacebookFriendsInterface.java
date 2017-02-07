package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

public interface FacebookFriendsInterface {
	public void createFacebookFreinds(Facebook facebook, Friend user);
	public List<String> getListOfId();
	public List<String> getListOfName();
	public List<Friend> getFriends();
}
