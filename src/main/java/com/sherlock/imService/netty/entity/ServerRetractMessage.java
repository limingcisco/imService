package com.sherlock.imService.netty.entity;

import com.sherlock.imService.constant.MessageConstant;

/**
 * “消息撤回”消息
 * @author Administrator
 *
 */
public class ServerRetractMessage extends ServerImMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7414260071917829424L;
	private String retractMid;//消息Id
	
	public ServerRetractMessage(){
		this.messageType = MessageConstant.MSGTYPE_RETRACT;
	}

	public String getRetractMid() {
		return retractMid;
	}

	public void setRetractMid(String retractMid) {
		this.retractMid = retractMid;
	}

}