package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;



@Service
public class FacebookFriends implements FacebookFriendsInterface{
	private List<String> idList;
	private List<String> nameList;
	private List<Friend> friendList;
	
	public FacebookFriends() {
		idList = new ArrayList<String>();
		nameList = new ArrayList<String>();
		friendList = new ArrayList<Friend>();
	}
	public void createFacebookFreinds(Facebook facebook, Friend user) {
		List<FacebookProfile> fbFriends;
		fbFriends = facebook.friendOperations().getFriendProfiles();
		idList = new ArrayList<String>();
		nameList = new ArrayList<String>();
		friendList = new ArrayList<Friend>();
		
		for (int i = 0; i < fbFriends.size(); i++) {
			Long friendId = Long.parseLong(fbFriends.get(i).getId());
			Long uId = user.getFacebookId();
			String friendName = fbFriends.get(i).getName();
			Friend parent = null;
			String loginDate = user.getLoginDate();
			String searchDate = null;
			Friend friend = new Friend(friendId, uId, friendName, parent, loginDate, searchDate);
			friendList.add(friend);
			idList.add(fbFriends.get(i).getId());
			nameList.add(fbFriends.get(i).getName());
		}
	}
	public List<String> getListOfId() {
		return idList;
	}
	public List<String> getListOfName() {
		return nameList;
	}
	public List<Friend> getFriends() {
		return friendList;
	}
	
}
