package io.undertow.server.protocol.udp;

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
        System.out.println("UdpReadListener invoked!");

        SocketAddressBuffer addressBuffer = new SocketAddressBuffer();
        // TODO max size:
        ByteBuffer buffer = ByteBuffer.allocate(maxBufferCapacity);

        try {
            int result = channel.receiveFrom(addressBuffer, buffer);

            if (result > 0) {
                System.out.println("Incoming UDP data received: "
                        + new String(buffer.array(), java.nio.charset.StandardCharsets.UTF_8));
                UdpMessage message = new UdpMessage(buffer, addressBuffer);
                rootHandler.handleRequest(message);
            } else {
                System.out.println("No UDP data received!");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
