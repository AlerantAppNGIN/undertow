package io.undertow.server.protocol.udp;

import java.nio.ByteBuffer;

import org.xnio.channels.SocketAddressBuffer;

public class UdpMessage {
    // private MulticastMessageChannel channel;
    private ByteBuffer bufferedData;
    private SocketAddressBuffer addressBuffer;

    public UdpMessage(ByteBuffer bufferedData, SocketAddressBuffer addressBuffer) {
        this.bufferedData = bufferedData;
        this.addressBuffer = addressBuffer;
        // this.channel = channel;
    }

    // public MulticastMessageChannel getChannel() {
    // return channel;
    // }

    public ByteBuffer getBufferedData() {
        return bufferedData;
    }

    public SocketAddressBuffer getAddressBuffer() {
        return addressBuffer;
    }
}
