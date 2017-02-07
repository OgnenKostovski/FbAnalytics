package it.polimi.awt.services;

import org.springframework.social.facebook.api.Facebook;

import it.polimi.awt.domain.Friend;

public interface UserFriendInterface {

		public Friend initialize(Facebook facebook);
}
