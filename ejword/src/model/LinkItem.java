package model;

import java.io.Serializable;

public class LinkItem implements Serializable {
	private int pageNo;
	private boolean isActive;
	public LinkItem() {}

	public LinkItem(int pageNo, boolean isActive) {
		this.pageNo= pageNo;
		this.isActive = isActive;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
