package com.ccm.user.user.domain.vo;

public class SenderProperties {

    private String host;
    private int port;
    private String queueName;

    public SenderProperties(String host, int port, String queueName) {
        this.host = host;
        this.port = port;
        this.queueName = queueName;
    }

    public String getHost() { return host; }

    public int getPort() { return port; }

    public String getQueueName() { return queueName; }
}
