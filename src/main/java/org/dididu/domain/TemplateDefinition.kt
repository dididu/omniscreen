package org.dididu.domain

import org.springframework.data.annotation.Id

/**
 * Defines template.
 */
class TemplateDefinition(@Id var user: String, var template: String)
