package com.mt.spring_file_upload.configuration;


import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
//    @Bean
//    public GroupedOpenApi backEndGroupApi() {
//        return GroupedOpenApi.builder().group("BackEnd-api").addOpenApiCustomizer(openApi ->
//                openApi.info(getFrontendApiInfo())).packagesToScan("com.mt.controller.BackEnd").build();
//    }

    @Bean
    public GroupedOpenApi frontEndGroupApi() {
        return GroupedOpenApi.builder().group("FrontEnd-api").addOpenApiCustomizer(openApi ->
                openApi.info(getFrontendApiInfo())).packagesToScan("com.mt.spring_file_upload.controller.frontend").build();
    }

    //
    private Info getFrontendApiInfo() {
       Contact contact = new Contact();
        contact.setName("Mengty");
        contact.setEmail("Mengty@gamil.com");

        return new Info().title("Spring File Upload Frontend API").description("frontend Ecommerce API").contact(contact).version("1.0.1");
    }
}
