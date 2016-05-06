package org.weiti_map.server;

class ServerUtils {
//	final static String DEFAULT_KEYSTORE_NAME = "wmapdefaultkeystore_";
	final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    final static String EMAIL_ADDRESS = "jakubkoladadev@gmail.com";
	final static String GROUP_EXISTS = "GROUP_EXISTS";
	final static String GROUP_DOESNT_EXIST = "GROUP_DOESNT_EXIST";

    final static String HANDSHAKE_MSG_TYPE = "HANDSHAKE";
    final static String GET_GROUP_MSG_TYPE = "GET_GROUP";
    final static String SEND_GROUP_MSG_TYPE = "SEND_GROUP";

    final static String MSG_TYPES_REGEXP = HANDSHAKE_MSG_TYPE + '|' + GET_GROUP_MSG_TYPE + '|' + SEND_GROUP_MSG_TYPE;
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
