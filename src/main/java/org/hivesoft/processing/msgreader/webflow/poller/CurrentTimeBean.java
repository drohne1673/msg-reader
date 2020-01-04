package org.hivesoft.processing.msgreader.webflow.poller;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CurrentTimeBean {

	public Date getTime() {
		return new Date();
	}

}
