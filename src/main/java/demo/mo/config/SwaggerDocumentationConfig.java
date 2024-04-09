package demo.mo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
public class SwaggerDocumentationConfig {

    /**
     * Docketのカスタマイズ
     * @return Docketインスタンス
     */
    @Bean
    public Docket customImplementation() {

        RequestParameter re = new RequestParameterBuilder().name("debug")
                .description("swaggerでテストするときに、このヘッダに任意の文字列入力してください。").in(ParameterType.HEADER)
                .query(parameterSpecificationBuilder -> parameterSpecificationBuilder.defaultValue("1")
                        .allowEmptyValue(true))
                .build();
        List<RequestParameter> list = new ArrayList<RequestParameter>();
        list.add(re);

        return new Docket(DocumentationType.OAS_30).select()
//                .globalRequestParameters(list).select()
                .apis(RequestHandlerSelectors.basePackage("demo.mo.controller")).paths(PathSelectors.any())
                .build().apiInfo(apiInfo());

    }

    /**
     * ApiInfoインスタンスの設定
     * @return ApiInfoのインスタンス
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Demoシステムインターフェース定義群").description("Demoシステム").license("")
                .licenseUrl("http://unlicense.org").termsOfServiceUrl("").version("1.0.0")
                .contact(new Contact("", "", "admin@sample.com")).build();
    }

    /**
     * openApiインスタンスの設定
     * @return openApiのインスタンス
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("Demoシステムインターフェース定義群").description("Demoシステム").termsOfService("")
                        .version("1.0.0").license(new License().name("").url("http://unlicense.org"))
                        .contact(new io.swagger.v3.oas.models.info.Contact().email("admin@sample.com")))
                .schemaRequirement("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }

}
