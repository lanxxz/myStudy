package com.czy.bootdemo.enabletest;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * program: myStudy
 * description:
 *
 * @author: alien
 * @since: 2021/01/09 13:15
 */
public class ServerImportSelector implements ImportSelector, EnvironmentAware {

    private Environment environment;

    @Override
    public String[] selectImports(AnnotationMetadata classMetadata) {
        Map<String, Object> annotationAttributes = classMetadata.getAnnotationAttributes(EnableServer.class.getName());
        String value = (String) annotationAttributes.get("value");

        if (environment != null) {
            value = environment.resolvePlaceholders(value);
        }

        String returnVal;
        if ("ftp".equals(value)) {
            returnVal = FtpServer.class.getName();
        } else if ("mail".equals(value)) {
            returnVal = MailServer.class.getName();
        } else {
            returnVal = null;
        }

        return new String[]{returnVal};
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
