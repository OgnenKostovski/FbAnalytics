package it.polimi.awt.services;

import java.util.List;

import org.graphstream.graph.implementations.SingleGraph;

import it.polimi.awt.domain.Friend;

public interface GraphInterface {

	public void createFriendsGraph(List<Friend> friendsList, String userId);

	public SingleGraph calculateGraphMetrics(List<Friend> friendsList, Friend user);

	public SingleGraph getFriendsGraph();
}
