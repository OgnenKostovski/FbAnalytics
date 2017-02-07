package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Service;

@Service
public class CommonFriends implements CommonFriendsInterface {
	private List<Friend> commonFriends;
	private String timeStamp;

	public CommonFriends() {
		commonFriends = new ArrayList<Friend>();
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
	}

	public List<Friend> createCommonFriends(Facebook facebook,
			String[] idSelected, Friend user, Friend selectedFriend, List<Friend> mutualFriends) {
		commonFriends = new ArrayList<Friend>();
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());

		for (int i = 0; i < idSelected.length; i++) {

			String idString = idSelected[0];
			Long idLong = Long.parseLong(idString);
			Integer id = Integer.getInteger(idString);
			commonFriends.add(selectedFriend);
			int j = commonFriends.indexOf(selectedFriend);
			for (int k = 0; k < mutualFriends.size(); k++) {
				commonFriends
						.get(j)
						.getCommonFriends()
						.add(new Friend(mutualFriends.get(k).getFacebookId(),
								user.getFacebookId(), mutualFriends.get(k)
										.getName(), selectedFriend, user
										.getLoginDate(), timeStamp));
			}
		}
		return commonFriends;
	}

	public List<Friend> getCommonFriends() {
		return commonFriends;
	}

}
