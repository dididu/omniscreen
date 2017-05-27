package org.dididu.domain;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public class TemplateData {
    @Id
    public String user;

    public final Map<String, String> data = new HashMap<>();
}
