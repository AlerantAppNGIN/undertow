package io.undertow.server.handlers.udp;

import io.undertow.server.HttpHandler;
import io.undertow.server.protocol.udp.UdpMessage;

import org.xnio.channels.MulticastMessageChannel;

public interface UdpHandler extends HttpHandler {

    void init(MulticastMessageChannel channel);

    void handleRequest(UdpMessage message);

    void sendResponse(UdpMessage message);

    MulticastMessageChannel getChannel();

    UdpHandler wrapNextHandler(UdpHandler handler);
}
