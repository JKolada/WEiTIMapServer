package org.weiti_map.server;

class Message
{	
	public static enum MessageType {HANDSHAKE, GET_GROUP};
	private MessageType MsgType;
	private String param;
	private String allMessage;
	
	public MessageType getMsgType() {return MsgType;};
	
	public Message(MessageType msgtyp) {
		MsgType = msgtyp;
		configure();		
	}
	
	public Message(MessageType msgtyp, String par) {
		MsgType = msgtyp;
		configure();
		param = par;
		setParam(param);
	}
	
	public void setParam(String par) {
		param = par;
		if (MsgType == MessageType.HANDSHAKE) return; 
		configure();
		allMessage += param + ">";
	}
	
	public String toString() {		
		return allMessage;
	}
	
//	public String getParam() {
//		return param;
//	}
	
	private void configure() {
		switch (MsgType) {
			case HANDSHAKE:
				allMessage = ServerUtils.EMAIL_ADDRESS;
				break;
			case GET_GROUP:
				allMessage = ServerUtils.GET_GROUP;			
			default:
				break;
		}
	}		
	
}