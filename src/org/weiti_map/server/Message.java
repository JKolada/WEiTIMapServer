
package org.weiti_map.server;

import org.weiti_map.db.MyDatabase;

import com.example.kuba.weitimap.db.GroupPlanObject;

class Message {
	public static enum MessageType {
		HANDSHAKE, GET_GROUP, SEND_GROUP
	};

	private MessageType MsgType;
	private String param;
	private String allMessage;
	private boolean isValid;

	public MessageType getMsgType() {
		return MsgType;
	}

	public Message(MessageType msgtyp) {
		MsgType = msgtyp;
		isValid = false;
		configure();
	}

	public Message(MessageType msgtyp, String par) {
		MsgType = msgtyp;
		isValid = false;
		configure();
		setParam(par);
	}

	public Message(String msgtyp, String par) {
		isValid = false;
		if (msgtyp.equals(ServerUtils.HANDSHAKE_MSG_TYPE)) {
			MsgType = MessageType.HANDSHAKE;
			if (par.equals(ServerUtils.EMAIL_ADDRESS)) {
				// isValid = true;
				configure();
				printStatus();
				return;
			} else {
				printStatus();
			}
		} else if (msgtyp.equals(ServerUtils.GET_GROUP_MSG_TYPE))
			MsgType = MessageType.GET_GROUP;
		else if (msgtyp.equals(ServerUtils.SEND_GROUP_MSG_TYPE))
			MsgType = MessageType.SEND_GROUP;
		else
			return;

		configure();
		setParam(par);
		printStatus();
	}

	public boolean setParam(String par) {
		if (isValid == true) {
			System.out.println(
					"Parameter of the message is already set and message is valid");
			return false;
		}
		param = par;
		if (MsgType == MessageType.HANDSHAKE)
			return isValid;
		allMessage += param + ">";
		isValid = true;
		return isValid;
	}

	public String getParam() {
		if (isValid)
			return param;
		else
			return null;
	}

	public MessageType getType() {
		if (isValid)
			return MsgType;
		else
			return null;
	}

	public String toString() {
		if (isValid)
			return allMessage;
		else
			return null;
	}

	// public boolean isValid() {return isValid;}

	public GroupPlanObject getGroupPlanobject(MyDatabase mDB) {
		GroupPlanObject ret = null;
		if (validate(mDB)) {
			ret = mDB.getGroupPlanObject(param);
		}
		return ret;
	}

	private boolean validate(MyDatabase mDB) {
		if (MsgType != MessageType.GET_GROUP) {
			System.out.println("Message is not a group request.");
			return false;
		}
		int group_id = mDB.checkGroupNameEx(param); // TODO
		if (group_id == -1)
			return false;
		else
			return true;
	}

	private void printStatus() {
		if (isValid == true)
			System.out.println("Message \"" + toString() + "\" is valid.");
		else
			System.out.println("Message \"" + toString() + "\" is INVALID.");
	}

	private void configure() {
		allMessage = "<";
		switch (MsgType) {
		case HANDSHAKE:
			allMessage += ServerUtils.HANDSHAKE_MSG_TYPE + '/'
					+ ServerUtils.EMAIL_ADDRESS + '>';
			isValid = true;
			break;
		case GET_GROUP:
			allMessage += ServerUtils.GET_GROUP_MSG_TYPE + '/';
			break;
		case SEND_GROUP:
			allMessage += ServerUtils.SEND_GROUP_MSG_TYPE + '/';
			break;
		default:
			break;
		}
	}
}