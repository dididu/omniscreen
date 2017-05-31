package org.dididu.domain

import org.springframework.data.annotation.Id

/**
 * Defines style.
 */
class StyleDefinition(@Id val user: String, val style: String)
