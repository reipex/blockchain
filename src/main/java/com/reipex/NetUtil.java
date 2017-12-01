package com.reipex;

import org.bitlet.weupnp.GatewayDevice;
import org.bitlet.weupnp.GatewayDiscover;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

public final class NetUtil {
    private static GatewayDevice activeGatewayDevice;

    static {
        try {
            GatewayDiscover gatewayDiscover = new GatewayDiscover();
            Map<InetAddress, GatewayDevice> gateways = gatewayDiscover.discover();

            if (gateways.isEmpty()) {
                //log.error("No gateways found");
            }
            activeGatewayDevice = gatewayDiscover.getValidGateway();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            //log.error(e.getMessage(), e);
        }
    }

    private NetUtil() {
    }

    public static InetAddress getLocalIp() {
        return activeGatewayDevice.getLocalAddress();
    }

    public static InetAddress getPublicIp() {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(activeGatewayDevice.getExternalIPAddress());
        } catch (IOException | SAXException e) {
            //log.error(e.getMessage(), e);
        }
        return address;
    }
}