/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.undertow.examples.udp;

import io.undertow.Undertow;
import io.undertow.examples.UndertowExample;
import io.undertow.server.handlers.udp.UdpHandler;
import io.undertow.server.protocol.udp.UdpMessage;

/**
 * @author Istvna Kakonyi
 */
@UndertowExample("UDPServerExampe")
public class UDPServerExampe {

    public static void main(final String[] args) {
        Undertow server = Undertow.builder().addUdpListener(5080, "127.0.0.1").setHandler(new UdpHandler() {
            @Override
            public void handleRequest(UdpMessage message) {
                // siple echo test:
                message.getAddressBuffer().setDestinationAddress(message.getAddressBuffer().getSourceAddress());
                this.sendResponse(message);
            }
        }).build();
        server.start();
    }

}
