package business;

import java.util.List;

import model.Activity;

public class UserIndexBean {
	private List<Activity> ad;
	private List<Activity> concert;
	private List<Activity> musicale;
	private List<Activity> drama;
	private List<Activity> ballet;
	private List<Activity> parent_child;
	private List<Activity> opera;
	private List<Activity> entertainment;
	private List<Activity> sports;
	
	public List<Activity> getAd() {
		return ad;
	}
	public void setAd(List<Activity> ad) {
		this.ad = ad;
	}
	
	public List<Activity> getConcert() {
		return concert;
	}
	public void setConcert(List<Activity> concert) {
		this.concert = concert;
	}
	
	public List<Activity> getMusicale() {
		return musicale;
	}
	public void setMusicale(List<Activity> musicale) {
		this.musicale = musicale;
	}
	public List<Activity> getDrama() {
		return drama;
	}
	public void setDrama(List<Activity> drama) {
		this.drama = drama;
	}
	public List<Activity> getBallet() {
		return ballet;
	}
	public void setBallet(List<Activity> ballet) {
		this.ballet = ballet;
	}
	public List<Activity> getParent_child() {
		return parent_child;
	}
	public void setParent_child(List<Activity> parent_child) {
		this.parent_child = parent_child;
	}
	public List<Activity> getOpera() {
		return opera;
	}
	public void setOpera(List<Activity> opera) {
		this.opera = opera;
	}
	public List<Activity> getEntertainment() {
		return entertainment;
	}
	public void setEntertainment(List<Activity> entertainment) {
		this.entertainment = entertainment;
	}
	public List<Activity> getSports() {
		return sports;
	}
	public void setSports(List<Activity> sports) {
		this.sports = sports;
	}
	
	
}
