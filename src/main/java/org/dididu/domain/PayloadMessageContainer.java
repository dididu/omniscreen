package org.dididu.domain;

/**
 * Laout message to be sent to the web UI.
 */
public class PayloadMessageContainer extends MessageContainer {

    public String selector;
    public String payloadHtml;

    public PayloadMessageContainer(String selector, String html) {
        this.selector = selector;
        this .payloadHtml = html;
    }
}
