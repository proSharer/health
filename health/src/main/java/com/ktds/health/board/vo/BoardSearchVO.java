package com.ktds.health.board.vo;

import com.ktds.health.common.web.pager.Pager;
import com.ktds.health.common.web.pager.PagerFactory;

public class BoardSearchVO {
	
	private Pager pager;
	
	private String artistId;

	public Pager getPager() {
		
		if(pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	


}
