package org.weiti_map.server;

class ServerUtils {
//	final static String DEFAULT_KEYSTORE_NAME = "wmapdefaultkeystore_";
	final static String EMAIL_ADDRESS = "<HANDSHAKE/jakubkoladadev@gmail.com>";
	final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	final static String GET_GROUP = "<GET_GROUP/";
	
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
