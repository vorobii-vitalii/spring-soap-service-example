package com.example.ws.config;

import com.example.ws.utils.WebServiceConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Value("${service.schema.location}")
    private String schemaLocation;

    @Value("${service.location.url-prefix}")
    private String locationUrlPrefix;

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        var servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, locationUrlPrefix + "/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema exampleSchema) {
        var wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("ExamplePort");
        wsdlDefinition.setLocationUri(locationUrlPrefix);
        wsdlDefinition.setTargetNamespace(WebServiceConstants.NAMESPACE_URL);
        wsdlDefinition.setSchema(exampleSchema);

        return wsdlDefinition;
    }

    @Bean
    public XsdSchema exampleSchema() {
        var schemaResource = new ClassPathResource(schemaLocation);

        return new SimpleXsdSchema(schemaResource);
    }

}
