package it.polimi.awt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.polimi.awt.domain.Friend;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {


	private static final long serialVersionUID = 1L;
	private String name;
	private Long facebookId;
	private String loginDate;
	private String searchDate;
	private Long userId;
	private double degreeCentrality;
	private double normalizedDegreeCentrality;
	private double betweennessCentrality;
	private double closenessCentrality;
	private double normalizedClosenessCentrality;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long objectId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Friend> commonFriends = new ArrayList<Friend>();
	@ManyToOne(cascade = CascadeType.ALL)
	private Friend parent;

	public Friend() {
	};
	public Friend(Long fbId, Long uId, String name, Friend parent, String loginDate, String searchDate){
		this.facebookId = fbId;
		this.userId = uId;
		this.name = name;
		this.parent = parent;
		this.setLoginDate(loginDate);
		this.setSearchDate(searchDate);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public double getDegreeCentrality() {
		return degreeCentrality;
	}
	public void setDegreeCentrality(double degreeCentrality) {
		this.degreeCentrality = degreeCentrality;
	}
	public double getBetweennessCentrality() {
		return betweennessCentrality;
	}
	public void setBetweennessCentrality(double betweennessCentrality) {
		this.betweennessCentrality = betweennessCentrality;
	}
	public double getNormalizedDegreeCentrality() {
		return normalizedDegreeCentrality;
	}
	public void setNormalizedDegreeCentrality(double normalizedDegreeCentrality) {
		this.normalizedDegreeCentrality = normalizedDegreeCentrality;
	}
	public double getClosenessCentrality() {
		return closenessCentrality;
	}
	public void setClosenessCentrality(double closenessCentrality) {
		this.closenessCentrality = closenessCentrality;
	}
	public double getNormalizedClosenessCentrality() {
		return normalizedClosenessCentrality;
	}
	public void setNormalizedClosenessCentrality(
			double normalizedClosenessCentrality) {
		this.normalizedClosenessCentrality = normalizedClosenessCentrality;
	}

	public Long getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public Friend getParent() {
		return parent;
	}
	public void setParent(Friend parent) {
		this.parent = parent;
	}
	public List<Friend> getCommonFriends() {
		return commonFriends;
	}
	public void setCommonFriends(List<Friend> commonFriends) {
		this.commonFriends = commonFriends;
	}
}
