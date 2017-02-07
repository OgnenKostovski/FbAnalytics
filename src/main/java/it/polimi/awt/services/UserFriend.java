package it.polimi.awt.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import it.polimi.awt.domain.Friend;

@Service
public class UserFriend implements UserFriendInterface {
	private String timeStamp;

	public Friend initialize(Facebook facebook) {

		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
		Long id = Long.parseLong(facebook.userOperations().getUserProfile().getId());
		String name = facebook.userOperations().getUserProfile().getName();
		Friend parent = null;
		String loginDate = timeStamp;
		String searchDate = null;
		Friend user = new Friend(id, id, name, parent, loginDate, searchDate);
		return user;
	}
}
