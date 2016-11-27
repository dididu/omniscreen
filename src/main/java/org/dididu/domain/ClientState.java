package org.dididu.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "omniClients")
public class ClientState {
    @Id
    public String clientId;
    public String secret;
    public String body;
}
