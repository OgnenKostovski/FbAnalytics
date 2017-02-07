package org.springframework.social.quickstart;

import it.polimi.awt.domain.Friend;
import it.polimi.awt.services.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.FMUL;

@Controller
@ComponentScan("it.polimi.awt.services")
public class HomeController {
	private final Facebook facebook;
	private Friend user;
	public List<Friend> commonFriendsList = new ArrayList<Friend>();
	public SingleGraph friendsGraph = new SingleGraph("graph");
	private FriendsServiceInterface fsInterface;
	private UserFriendInterface ufInterface;
	private FacebookFriendsInterface ffInterface;
	private CommonFriendsInterface cfInterface;
	private CreateJsonInterface cjInterface;
	private GraphInterface gInterface;

	@Autowired
	public HomeController(Facebook facebook, FriendsServiceInterface fs,
			UserFriendInterface uf, FacebookFriendsInterface ff,
			CommonFriendsInterface cf, CreateJsonInterface cj,
			GraphInterface g, HttpSession session) {
		this.facebook = facebook;
		fsInterface = fs;
		ufInterface = uf;
		ffInterface = ff;
		cfInterface = cf;
		cjInterface = cj;
		gInterface = g;
	}

	@RequestMapping(value = { "/", "/openGraph", "/commonFriends" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute(facebook.userOperations().getUserProfile());
		return "home";
	}

	@RequestMapping(value = "aboutUs", method = RequestMethod.GET)
	public String aboutUs(Model model) {
		model.addAttribute(facebook.userOperations().getUserProfile());
		// fsInterface.flushFriends();
		return "aboutUs";
	}

	@RequestMapping(value = "/friendsList", method = RequestMethod.GET)
	public String friendsList(Model model) {

		// Initialize the user as a "friend" object
		user = ufInterface.initialize(facebook);

		// Create the list of direct friends
		ffInterface.createFacebookFreinds(facebook, user);

		// Persist
		fsInterface.addUser(user);
		fsInterface.addFriends(ffInterface.getFriends());

		model.addAttribute(facebook.userOperations().getUserProfile())
				.addAttribute("nameList", ffInterface.getListOfName())
				.addAttribute("idList", ffInterface.getListOfId());
		return "friendsList";
	}

	@RequestMapping(value = "/commonFriends", method = RequestMethod.POST)
	public String commonFriendsList(
			@RequestParam(value = "id[]", required = false) String[] idSelected,
			Model model) {

		if (idSelected == null)
			return "redirect:/friendsList";

		commonFriendsList = new ArrayList();

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());

		for (int i = 0; i < idSelected.length; i++) {
			FacebookProfile selectedFriend = facebook.userOperations()
					.getUserProfile(idSelected[i]);
			Friend commonFriend = new Friend(Long.parseLong(selectedFriend
					.getId()), user.getFacebookId(), selectedFriend.getName(),
					null, user.getLoginDate(), timeStamp);
			List<Friend> friendFriends = fsInterface
					.getUserFriends(commonFriend);
			List<Friend> mutualFriends = new ArrayList<Friend>();
			if (friendFriends != null)
				mutualFriends = friendFriends;
			String[] selectedFakeList = { idSelected[i].toString() };
			commonFriendsList.addAll(cfInterface.createCommonFriends(facebook,
					selectedFakeList, user, commonFriend, mutualFriends));
		}

		List<Friend> mFriendList = new ArrayList<Friend>();
		for (int s = 0; s < idSelected.length; s++) {
			FacebookProfile selectedFriend = facebook.userOperations()
					.getUserProfile(idSelected[s]);
			Friend commonFriend = new Friend(Long.parseLong(selectedFriend
					.getId()), user.getFacebookId(), selectedFriend.getName(),
					null, user.getLoginDate(), timeStamp);
			mFriendList.add(commonFriend);
			// mutual friends for user
			for (int i = 0; i < commonFriendsList.size(); i++) {
				for (int j = 0; j < commonFriendsList.get(i).getCommonFriends()
						.size(); j++) {
					Long cId = commonFriendsList.get(i).getCommonFriends()
							.get(j).getFacebookId();
					if (mFriendList.get(s).getCommonFriends().isEmpty()) {
						mFriendList
								.get(s)
								.getCommonFriends()
								.add(commonFriendsList.get(i)
										.getCommonFriends().get(j));
						continue;
					}
					int m;
					for (m = 0; m < mFriendList.get(s).getCommonFriends()
							.size(); m++) {
						if (mFriendList.get(s).getCommonFriends().get(m)
								.getFacebookId() == cId) {
							break;
						}
						if (m + 1 == mFriendList.get(s).getCommonFriends()
								.size()) {
							mFriendList
									.get(s)
									.getCommonFriends()
									.add(commonFriendsList.get(i)
											.getCommonFriends().get(j));
						}
					}
				}
			}
		}

		gInterface.createFriendsGraph(commonFriendsList, facebook
				.userOperations().getUserProfile().getId());
		friendsGraph = gInterface
				.calculateGraphMetrics(commonFriendsList, user);

		fsInterface.addCommonFriends(commonFriendsList);

		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("name", facebook.userOperations().getUserProfile()
				.getName());
		model.addAttribute("friends", commonFriendsList);
		return "commonFriendsList";
	}

	@RequestMapping(value = "/openGraph", method = RequestMethod.POST)
	public String newGraph(Model model) {

		cjInterface.createNewJson(
				commonFriendsList,
				facebook.userOperations().getUserProfile().getName(),
				Long.parseLong(facebook.userOperations().getUserProfile()
						.getId()));

		model.addAttribute("graph", cjInterface.getJson());
		model.addAttribute(facebook.userOperations().getUserProfile());
		model.addAttribute("name", facebook.userOperations().getUserProfile()
				.getName());
		model.addAttribute("friends", commonFriendsList);

		return "forceDirectedGraph";
	}

	@RequestMapping(value = "/graphNode", method = RequestMethod.GET)
	public String graphNode(Model model, @RequestParam(value = "id") String id,
			@RequestParam(value = "height") String height,
			@RequestParam(value = "width") String width) {

		FacebookProfile profile = facebook.userOperations().getUserProfile(id);

		model.addAttribute("betweenness", (double) Math
				.round((double) friendsGraph.getNode(id).getAttribute(
						"betweenness") * 100) / 100);
		model.addAttribute("closeness", (double) Math
				.round((double) friendsGraph.getNode(id).getAttribute(
						"closeness") * 100) / 100);
		model.addAttribute("norm_closeness", (double) Math
				.round((double) friendsGraph.getNode(id).getAttribute(
						"norm_closeness") * 100) / 100);
		model.addAttribute("degree", (double) Math.round((double) friendsGraph
				.getNode(id).getAttribute("degree") * 100) / 100);
		model.addAttribute("norm_degree", (double) Math
				.round((double) friendsGraph.getNode(id).getAttribute(
						"norm_degree") * 100) / 100);

		model.addAttribute("height", height);
		model.addAttribute("width", width);
		model.addAttribute("profile", profile);
		return "nodePopUp";
	}
}
