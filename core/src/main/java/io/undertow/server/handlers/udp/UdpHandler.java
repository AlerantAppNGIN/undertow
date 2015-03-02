package io.undertow.server.handlers.udp;

import io.undertow.server.protocol.udp.UdpMessage;

import java.io.IOException;

import org.xnio.channels.MulticastMessageChannel;

public abstract class UdpHandler {

    protected MulticastMessageChannel channel;

    public abstract void handleRequest(UdpMessage message);

    public void sendResponse(UdpMessage message) {

        channel.resumeWrites();

        try {
            boolean sent = channel
                    .sendTo(message.getAddressBuffer().getDestinationAddress(), message.getBufferedData());

            if (sent) {
                System.out.println("Outgoing UDP data sent: "
                        + new String(message.getBufferedData().array(), java.nio.charset.StandardCharsets.UTF_8));
            } else {
                System.out.println("No  UDP data sent!");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            channel.suspendWrites();
        }
    }

    public void addChannel(MulticastMessageChannel channel) {
        this.channel = channel;
    }

    public MulticastMessageChannel getChannel() {
        return channel;
    }
}
