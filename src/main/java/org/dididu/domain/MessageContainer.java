package org.dididu.domain;

/**
 * Message to be sent to the web UI.
 */
public class MessageContainer {
    public enum Type {
        LAYOUT,
        PAYLOAD
    };

    public Type type;
}
