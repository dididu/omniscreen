package org.dididu.domain;

/**
 * Layout message to be sent to the web UI.
 */
public class LayoutMessageContainer extends MessageContainer {

    public String layoutHtml;

    public LayoutMessageContainer(String layoutHtml) {
        this.type = Type.LAYOUT;
        this.layoutHtml = layoutHtml;
    }
}
