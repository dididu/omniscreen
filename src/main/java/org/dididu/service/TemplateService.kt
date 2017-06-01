package org.dididu.service

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.dididu.domain.StyleDefinition
import org.dididu.domain.TemplateData
import org.dididu.domain.TemplateDefinition
import org.dididu.repository.DataRepository
import org.dididu.repository.StyleRepository
import org.dididu.repository.TemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.io.StringWriter
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

@Component
open class TemplateService @Autowired
constructor(private val dataRepository: DataRepository,
            private val templateRepository: TemplateRepository,
            private val styleRepository: StyleRepository) {

    fun saveTemplate(templateDefinition: TemplateDefinition) {
        templateRepository.save(templateDefinition)
    }

    fun saveStyle(styleDefinition: StyleDefinition) {
        styleRepository.save(styleDefinition)
    }

    fun saveData(templateData: TemplateData) {
        val currentData = dataRepository.findOne(templateData.user);

        val resultData = HashMap<String, String>();

        if (currentData != null) {
            resultData.putAll(currentData.data);
        }

        resultData.putAll(templateData.data);

        val resultTemplateData = TemplateData(templateData.user, resultData);
        dataRepository.save(resultTemplateData)
    }

    fun getStyleForUser(user: String): String {
        return styleRepository.findOne(user)?.style ?:"";
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
