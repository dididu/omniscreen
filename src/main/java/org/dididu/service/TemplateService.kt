package org.dididu.service

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.dididu.domain.TemplateData
import org.dididu.domain.TemplateDefinition
import org.dididu.repository.DataRepository
import org.dididu.repository.TemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.io.StringWriter
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

@Component
class TemplateService @Autowired
constructor(private val dataRepository: DataRepository, private val templateRepository: TemplateRepository) {

    fun saveTemplateForUser(user: String, templateDefinition: TemplateDefinition) {
        templateDefinition.user = user
        templateRepository.save(templateDefinition)
    }

    fun saveDataForUser(user: String, templateData: TemplateData) {
        templateData.user = user
        dataRepository.save(templateData)
    }

    fun renderTemplateForUser(user: String): String {

        val templateDefinition = templateRepository.findOne(user)
        val templateData = dataRepository.findOne(user)

        val writer = StringWriter()
        val context = VelocityContext()

        templateData?.data?.forEach({ key, value -> context.put(key, value) })

        if (templateDefinition != null) {
            Velocity.evaluate(context, writer, "", templateDefinition.template)
        }

        return URLDecoder.decode(writer.toString(), "utf-8")
    }
}
