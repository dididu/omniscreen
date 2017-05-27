package org.dididu.domain;

import org.springframework.data.annotation.Id;

/**
 * Defines Thymeleaf template.
 */
public class TemplateDefinition {

    @Id
    public String user;
    public String template;
    public String secret;
}
