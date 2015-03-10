package io.undertow.server.handlers.udp;

import io.undertow.UndertowLogger;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.protocol.udp.UdpMessage;

import java.io.IOException;

import org.xnio.channels.MulticastMessageChannel;

public abstract class AbstractUdpHandler implements UdpHandler {

    protected MulticastMessageChannel channel;

    protected UdpHandler next;

    @Override
    public abstract void handleRequest(UdpMessage message);

    @Override
    public void sendResponse(UdpMessage message) {

        channel.resumeWrites();

        try {

            boolean sent = channel
                    .sendTo(message.getAddressBuffer().getDestinationAddress(), message.getBufferedData());

            if (sent) {
                UndertowLogger.REQUEST_LOGGER.debug("Outgoing UDP data sent: "
                        + new String(message.getBufferedData().array(), java.nio.charset.StandardCharsets.UTF_8));
            } else {
                UndertowLogger.REQUEST_LOGGER.debug("No UDP data sent!");
            }

        } catch (IOException e) {
            UndertowLogger.REQUEST_LOGGER.error(e.getMessage(), e);
        } finally {
            channel.suspendWrites();
        }
    }

    @Override
    public void init(MulticastMessageChannel channel){
        this.channel = channel;
    }

    @Override
    public MulticastMessageChannel getChannel() {
        return channel;
    }

    @Override
    public UdpHandler wrapNextHandler(UdpHandler handler) {
        this.next = handler;
        return this.next;
    }

    @Override
    public void handleRequest(HttpServerExchange arg0) throws Exception {
        //not implement this method as this is an udp handler
    }
}
