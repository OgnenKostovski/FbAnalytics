package it.polimi.awt.services;

import it.polimi.awt.domain.Friend;

import java.util.List;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.algorithm.measure.AbstractCentrality;
import org.graphstream.algorithm.measure.ClosenessCentrality;
import org.graphstream.algorithm.measure.DegreeCentrality;
import org.graphstream.graph.implementations.SingleGraph;
import org.springframework.stereotype.Service;

@Service
public class Graph implements GraphInterface{
	public SingleGraph friendsGraph = new SingleGraph("graph");

	public Graph() {
	}

	public void createFriendsGraph(List<Friend> friendsList, String userId) {
		friendsGraph = new SingleGraph("graph");
		friendsGraph.addNode(userId);
		for (int i = 0; i < friendsList.size(); i++) {
			String friendId = Long.toString(friendsList.get(i).getFacebookId());

			if (friendsGraph.getNode(friendId) == null) {
				friendsGraph.addNode(friendId);
				friendsGraph.addEdge(userId + friendId, userId, friendId);
			}
			for (int k = 0; k < friendsList.get(i).getCommonFriends()
					.size(); k++) {
				String commonFriendId = Long.toString(friendsList.get(i)
						.getCommonFriends().get(k).getFacebookId());

				if (friendsGraph.getNode(commonFriendId) == null) {
					friendsGraph.addNode(commonFriendId);
					friendsGraph.addEdge(friendId + commonFriendId, friendId, commonFriendId);
					friendsGraph.addEdge(userId + commonFriendId, userId, commonFriendId);
				} else if ((friendsGraph.getEdge(friendId + commonFriendId) == null)
						&& (friendsGraph.getEdge(commonFriendId + friendId) == null)) {
					friendsGraph.addEdge(friendId + commonFriendId, friendId, commonFriendId);
				}
			}

		}
	}
	public SingleGraph calculateGraphMetrics(List<Friend> friendsList,
			Friend user) {
				DegreeCentrality dc = new DegreeCentrality();
				dc.init(friendsGraph);
				dc.setCentralityAttribute("degree");
				dc.compute();

				DegreeCentrality ndc = new DegreeCentrality("norm_degree",
						AbstractCentrality.NormalizationMode.SUM_IS_1);
				ndc.init(friendsGraph);
				ndc.compute();

				BetweennessCentrality bc = new BetweennessCentrality("betweenness");
				bc.init(friendsGraph);
				bc.compute();

				ClosenessCentrality cc = new ClosenessCentrality("closeness");
				cc.init(friendsGraph);
				cc.compute();

				ClosenessCentrality ncc = new ClosenessCentrality("norm_closeness",
						AbstractCentrality.NormalizationMode.SUM_IS_1, true, false);
				ncc.init(friendsGraph);
				ncc.compute();

				Long user_Id = user.getFacebookId();
				String userId = user_Id.toString();
				user.setBetweennessCentrality((Double) friendsGraph.getNode(userId)
						.getAttribute("betweenness"));
				user.setClosenessCentrality((Double) friendsGraph.getNode(userId)
						.getAttribute("closeness"));
				user.setDegreeCentrality((Double) friendsGraph.getNode(userId).getAttribute(
						"degree"));
				user.setNormalizedClosenessCentrality((Double) friendsGraph.getNode(userId)
						.getAttribute("norm_closeness"));
				user.setNormalizedDegreeCentrality((Double) friendsGraph.getNode(userId)
						.getAttribute("norm_degree"));

				for (int i = 0; i < friendsList.size(); i++) {

					Long fbId = friendsList.get(i).getFacebookId();
					String id = fbId.toString();
					friendsList.get(i).setBetweennessCentrality(
							(Double) friendsGraph.getNode(id).getAttribute("betweenness"));
					friendsList.get(i).setClosenessCentrality(
							(Double) friendsGraph.getNode(id).getAttribute("closeness"));
					friendsList.get(i).setDegreeCentrality(
							(Double) friendsGraph.getNode(id).getAttribute("degree"));
					friendsList.get(i).setNormalizedClosenessCentrality(
							(Double) friendsGraph.getNode(id).getAttribute("norm_closeness"));
					friendsList.get(i).setNormalizedDegreeCentrality(
							(Double) friendsGraph.getNode(id).getAttribute("norm_degree"));

					for (int k = 0; k < friendsList.get(i).getCommonFriends()
							.size(); k++) {
						Long commonFbId = friendsList.get(i).getCommonFriends().get(k).getFacebookId();
						String cid = commonFbId.toString();
						friendsList
								.get(i)
								.getCommonFriends()
								.get(k)
								.setBetweennessCentrality(
										(Double) friendsGraph.getNode(cid).getAttribute(
												"betweenness"));
						friendsList
								.get(i)
								.getCommonFriends()
								.get(k)
								.setClosenessCentrality(
										(Double) friendsGraph.getNode(cid).getAttribute(
												"closeness"));
						friendsList
								.get(i)
								.getCommonFriends()
								.get(k)
								.setDegreeCentrality(
										(Double) friendsGraph.getNode(cid).getAttribute(
												"degree"));
						friendsList
								.get(i)
								.getCommonFriends()
								.get(k)
								.setNormalizedClosenessCentrality(
										(Double) friendsGraph.getNode(cid).getAttribute(
												"norm_closeness"));
						friendsList
								.get(i)
								.getCommonFriends()
								.get(k)
								.setNormalizedDegreeCentrality(
										(Double) friendsGraph.getNode(cid).getAttribute(
												"norm_degree"));
					}

				}

				return friendsGraph;
	}
	public SingleGraph getFriendsGraph() {
		return friendsGraph;
	};

}
