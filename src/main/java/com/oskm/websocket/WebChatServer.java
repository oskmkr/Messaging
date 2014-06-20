package com.oskm.websocket;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatServer.java
 * <dependency>
 *		<groupId>org.java-websocket</groupId>
 *		<artifactId>Java-WebSocket</artifactId>
 *		<version>1.3.0</version>
 * </dependency>
 * @author sungkyu.eo
 *
 */

public class WebChatServer extends WebSocketServer {
	
	private static final Logger LOG = Logger.getLogger(WebChatServer.class);

	public WebChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
		// TODO Auto-generated constructor stub
		LOG.debug("");
	}

	@Override
	public void onClose(WebSocket webSocket, int arg1, String arg2, boolean arg3) {
		// TODO Auto-generated method stub
		LOG.debug("onClose");
		
	}

	@Override
	public void onError(WebSocket webSocket, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(WebSocket webSocket, String message) {
		// TODO Auto-generated method stub
		LOG.debug("onOpen");
		
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		// TODO Auto-generated method stub
		LOG.debug("onOpen");
		this.sendToAll("new connection: " + clientHandshake.getResourceDescriptor());
		System.out.println(webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
		
	}

	public void sendToAll( String text ) {
		Collection<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}
}
