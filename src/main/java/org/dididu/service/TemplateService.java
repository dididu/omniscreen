package org.dididu.service;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.dididu.domain.TemplateData;
import org.dididu.domain.TemplateDefinition;
import org.dididu.repository.DataRepository;
import org.dididu.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class TemplateService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private TemplateRepository templateRepository;

    public void saveTemplateForUser(String user, TemplateDefinition templateDefinition) {
        templateDefinition.user = user;
        templateRepository.save(templateDefinition);
    }

    public void saveDataForUser(String user, TemplateData templateData) {
        templateData.user = user;
        dataRepository.save(templateData);
    }

    public String renderTemplateForUser(String user) {

        TemplateDefinition templateDefinition = templateRepository.findOne(user);
        TemplateData templateData = dataRepository.findOne(user);

        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext();

        if (templateData != null) {
            templateData.data.forEach((key, value) -> context.put(key, value));
        }

        Velocity.evaluate(context, writer, "", templateDefinition.template);

        try {
            return URLDecoder.decode(writer.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
