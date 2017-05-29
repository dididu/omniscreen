package org.dididu.domain

import org.springframework.data.annotation.Id

/**
 * Defines template.
 */
class TemplateDefinition(@Id val user: String, val template: String)
