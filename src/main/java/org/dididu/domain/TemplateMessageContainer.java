package org.dididu.domain;

/**
 * Payload message to be sent to the web UI.
 */
public class TemplateMessageContainer {

    public String renderedTemplate;

    public TemplateMessageContainer(String html) {
        this.renderedTemplate = html;
    }
}
