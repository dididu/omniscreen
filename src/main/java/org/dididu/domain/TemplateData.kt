package org.dididu.domain

import org.springframework.data.annotation.Id

import java.util.HashMap

data class TemplateData(@Id val user: String, var data: Map<String, String>)
data class RestTemplateData(var data: Map<String, String> = HashMap())
