package org.dididu.domain

import org.springframework.data.annotation.Id

import java.util.HashMap

data class TemplateData(@Id
                   var user: String) {

    val data: Map<String, String> = HashMap()
}
