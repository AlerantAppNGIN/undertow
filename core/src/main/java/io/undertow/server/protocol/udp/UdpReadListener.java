package io.undertow.server.protocol.udp;

import io.undertow.UndertowLogger;
import io.undertow.server.handlers.udp.UdpHandler;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xnio.ChannelListener;
import org.xnio.channels.MulticastMessageChannel;
import org.xnio.channels.SocketAddressBuffer;

public class UdpReadListener implements ChannelListener<MulticastMessageChannel> {

    private UdpHandler rootHandler;
    private int maxBufferCapacity;

    public UdpReadListener(UdpHandler rootHandler, int maxBufferCapacity) {
        this.rootHandler = rootHandler;
        this.maxBufferCapacity = maxBufferCapacity;
    }

    @Override
    public void handleEvent(MulticastMessageChannel channel) {

        SocketAddressBuffer addressBuffer = new SocketAddressBuffer();
        // TODO max size:
        ByteBuffer buffer = ByteBuffer.allocate(maxBufferCapacity);

        try {
            int result = channel.receiveFrom(addressBuffer, buffer);

            if (result > 0) {

                byte[] sizedArray = new byte[result];
                for (int i = 0; i < result; i++) {
                    sizedArray[i] = buffer.array()[i];
                }

                ByteBuffer sizedBuffer = ByteBuffer.wrap(sizedArray);

                UndertowLogger.REQUEST_LOGGER.debug("Incoming UDP data received: "
                        + new String(sizedBuffer.array(), java.nio.charset.StandardCharsets.UTF_8));

                UdpMessage message = new UdpMessage(sizedBuffer, addressBuffer);
                rootHandler.handleRequest(message);
            } else {
                UndertowLogger.REQUEST_LOGGER.debug("No UDP data received!");
            }

        } catch (IOException e) {
            UndertowLogger.REQUEST_LOGGER.error(e.getMessage(), e);
        }
    }
}
