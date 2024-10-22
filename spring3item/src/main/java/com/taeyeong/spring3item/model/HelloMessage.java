package com.taeyeong.spring3item.model;

public class HelloMessage {

	private String strMessage;

	public HelloMessage(String inMsg) {
		this.strMessage = inMsg;
	}

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	
}
