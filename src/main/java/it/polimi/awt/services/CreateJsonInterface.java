package it.polimi.awt.services;

import java.util.List;

import it.polimi.awt.domain.Friend;

public interface CreateJsonInterface {
	
	public void createNewJson(List<Friend> friend, String self, Long fid);
	public String getJson();
}
