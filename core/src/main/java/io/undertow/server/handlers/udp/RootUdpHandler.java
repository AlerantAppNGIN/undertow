package io.undertow.server.handlers.udp;

import org.xnio.channels.MulticastMessageChannel;

import io.undertow.server.protocol.udp.UdpMessage;

public class RootUdpHandler extends AbstractUdpHandler {

    @Override
    public void handleRequest(UdpMessage message) {
        if (next != null && channel != null) {
            next.handleRequest(message);
        } else {
            // TODO
        }
    }

    public void addChannel(MulticastMessageChannel channel) {
        this.channel = channel;

        if (this.next != null) {
            this.next.init(channel);
        }
    }
}
