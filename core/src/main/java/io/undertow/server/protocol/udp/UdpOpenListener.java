package io.undertow.server.protocol.udp;

import io.undertow.server.handlers.udp.UdpHandler;

import org.xnio.ChannelListener;
import org.xnio.channels.MulticastMessageChannel;

public class UdpOpenListener implements ChannelListener<MulticastMessageChannel> {

    UdpHandler rootHandler;

    public UdpOpenListener(UdpHandler rootHandler) {
        this.rootHandler = rootHandler;
    }

    @Override
    public void handleEvent(MulticastMessageChannel channel) {
        System.out.println("UdpOpenListener invoked!");

        rootHandler.addChannel(channel);
    }

}
