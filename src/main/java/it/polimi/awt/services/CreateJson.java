package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CreateJson implements CreateJsonInterface {
	private String json = "";
	private final String NODES = "nodes";
	private final String NAME = "name";
	private final String ID = "id";
	private final String LINKS = "links";
	private final String SOURCE = "source";
	private final String TARGET = "target";

	public CreateJson() {
	}

	public String getJson() {
		return json;
	}

	public void createNewJson(List<Friend> friends, String self, Long fid) {
		JSONObject newJson = new JSONObject();
		JSONArray nodes = new JSONArray();
		JSONObject node = new JSONObject();
		JSONArray links = new JSONArray();
		JSONObject link = new JSONObject();
		List<Long> idPosition = new ArrayList<Long>();

		json = "";
		int j = 0;
		int i = 0;

		idPosition.add(0, fid);

		if (self.contains("\'")) {
			self = self.replaceAll("\'", "`");
		}
		try {
			node.put(NAME, self).put(ID, fid.toString());
			nodes.put(node);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		node = new JSONObject();
		for (i = 0; i < friends.size(); i++) {
			if (!idPosition.contains(friends.get(i).getFacebookId())) {
				idPosition.add(friends.get(i).getFacebookId());
				String nameFriend = friends.get(i).getName();
				if (nameFriend.contains("\'")) {
					nameFriend = nameFriend.replaceAll("\'", "`");
				}
				try {
					node.put(NAME, nameFriend).put(ID,
							friends.get(i).getFacebookId().toString());
					nodes.put(node);
					link.put(SOURCE, 0).put(TARGET,
							idPosition.indexOf(friends.get(i).getFacebookId()));
					links.put(link);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				node = new JSONObject();
				link = new JSONObject();
			}

			List<Friend> commonFriends = friends.get(i).getCommonFriends();
			for (j = 0; j < commonFriends.size(); j++) {
				if (!idPosition.contains(commonFriends.get(j).getFacebookId())) {
					idPosition.add(commonFriends.get(j).getFacebookId());
					String nameCommonFriend = commonFriends.get(j).getName();
					if (nameCommonFriend.contains("'")) {
						nameCommonFriend = nameCommonFriend.replace("'", "`");
					}
					try {
						node.put(NAME, nameCommonFriend)
								.put(ID,
										commonFriends.get(j).getFacebookId()
												.toString());
						nodes.put(node);
						link.put(SOURCE, 0).put(
								TARGET,
								idPosition.indexOf(commonFriends.get(j)
										.getFacebookId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					node = new JSONObject();
					link = new JSONObject();
					try {
						link.put(
								SOURCE,
								idPosition.indexOf(commonFriends.get(j)
										.getFacebookId())).put(
								TARGET,
								idPosition.indexOf(friends.get(i)
										.getFacebookId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					link = new JSONObject();
				} else {
					try {
						link.put(
								SOURCE,
								idPosition.indexOf(commonFriends.get(j)
										.getFacebookId())).put(
								TARGET,
								idPosition.indexOf(friends.get(i)
										.getFacebookId()));
						links.put(link);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					link = new JSONObject();
				}
			}
		}
		try {
			newJson.put(NODES, nodes).put(LINKS, links);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		json = newJson.toString();
	}

}
