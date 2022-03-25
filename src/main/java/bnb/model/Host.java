package bnb.model;

import java.util.Date;

public class Host extends User {
	
	protected String hostUrl;
	protected Date hostSince;
	protected String hostLocation;
	protected String hostAbout;
	protected int hostListingCount;
	protected int hostTotalListingCount;
	
	public Host(int id, String name, String userName, String password, String hostUrl, Date hostSince,
			String hostLocation, String hostAbout, int hostListingCount, int hostTotalListingCount) {
		super(id, name, userName, password);
		this.hostUrl = hostUrl;
		this.hostSince = hostSince;
		this.hostLocation = hostLocation;
		this.hostAbout = hostAbout;
		this.hostListingCount = hostListingCount;
		this.hostTotalListingCount = hostTotalListingCount;
	}
	
	public Host(String name, String userName, String password, String hostUrl, Date hostSince,
			String hostLocation, String hostAbout, int hostListingCount, int hostTotalListingCount) {
		super(name, userName, password);
		this.hostUrl = hostUrl;
		this.hostSince = hostSince;
		this.hostLocation = hostLocation;
		this.hostAbout = hostAbout;
		this.hostListingCount = hostListingCount;
		this.hostTotalListingCount = hostTotalListingCount;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public Date getHostSince() {
		return hostSince;
	}

	public void setHostSince(Date hostSince) {
		this.hostSince = hostSince;
	}

	public String getHostLocation() {
		return hostLocation;
	}

	public void setHostLocation(String hostLocation) {
		this.hostLocation = hostLocation;
	}

	public String getHostAbout() {
		return hostAbout;
	}

	public void setHostAbout(String hostAbout) {
		this.hostAbout = hostAbout;
	}

	public int getHostListingCount() {
		return hostListingCount;
	}

	public void setHostListingCount(int hostListingCount) {
		this.hostListingCount = hostListingCount;
	}

	public int getHostTotalListingCount() {
		return hostTotalListingCount;
	}

	public void setHostTotalListingCount(int hostTotalListingCount) {
		this.hostTotalListingCount = hostTotalListingCount;
	}
	
	
	
}
