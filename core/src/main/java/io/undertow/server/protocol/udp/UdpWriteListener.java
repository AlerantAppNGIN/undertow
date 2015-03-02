package io.undertow.server.protocol.udp;

import org.xnio.ChannelListener;
import org.xnio.channels.MulticastMessageChannel;

public class UdpWriteListener implements ChannelListener<MulticastMessageChannel> {

    @Override
    public void handleEvent(MulticastMessageChannel channel) {
        System.out.println("UdpWriteListener invoked!");
    }

}
