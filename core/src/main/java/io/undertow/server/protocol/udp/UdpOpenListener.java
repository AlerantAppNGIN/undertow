package io.undertow.server.protocol.udp;

import io.undertow.UndertowLogger;
import io.undertow.server.handlers.udp.RootUdpHandler;
import io.undertow.server.handlers.udp.UdpHandler;

import org.xnio.ChannelListener;
import org.xnio.channels.MulticastMessageChannel;

public class UdpOpenListener implements ChannelListener<MulticastMessageChannel> {

    RootUdpHandler rootHandler;

    public UdpOpenListener() {
    }

    public UdpOpenListener(RootUdpHandler rootHandler) {
        this.rootHandler = rootHandler;
    }

    @Override
    public void handleEvent(MulticastMessageChannel channel) {
        UndertowLogger.REQUEST_LOGGER.debug("UdpOpenListener invoked!");

        rootHandler.addChannel(channel);
    }

    public UdpHandler getRootHandler() {
        return rootHandler;
    }

    public void setRootHandler(RootUdpHandler rootHandler) {
        this.rootHandler = rootHandler;
    }

}
