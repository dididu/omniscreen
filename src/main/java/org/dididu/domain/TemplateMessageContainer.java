package org.dididu.domain;

/**
 * Payload message to be sent to the web UI.
 */
public class TemplateMessageContainer extends MessageContainer {

    public String template;

    public TemplateMessageContainer(String html) {
        this.template = html;
        this.type = Type.TEMPLATE;
    }
}
