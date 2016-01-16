package org.sipdroid.net;

import org.ice4j.socket.DatagramSocketFactory;

import java.net.DatagramSocket;
import java.net.SocketException;

public class SipdroidSocketFactory implements DatagramSocketFactory {

	@Override
	public DatagramSocket createUnboundDatagramSocket() throws SocketException {
		return new SipdroidSocket();
	}

}
