package br.com.azdev.restwithspringbootudemy.config;

import br.com.azdev.restwithspringbootudemy.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");


    public void addCorsMappings(CorsRegistry registry){
        //Funciona apenas para os metodos basicos
        registry.addMapping("/**")//Libera para todos os dominios
        //Para adicionar os outros metodos devemos fazer explicitamente
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD","TRACE","CONNECT");
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(new YamlJackson2HttpMessageConverter());
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //via QUERY PARAMETER
//        configurer.favorPathExtension(false)
//                .favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json",MediaType.APPLICATION_JSON)
//                .mediaType("xml",MediaType.APPLICATION_XML);

        //Header
        configurer.favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_YML);
    }
}
