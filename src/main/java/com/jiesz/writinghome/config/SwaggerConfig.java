package com.jiesz.writinghome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 *
 * @author zj
 * @date 2019/12/10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("1001").description("请求参数格式错误").build());
        responseList.add(new ResponseBuilder().code("2002").description("系统外部接口调用异常").build());
        responseList.add(new ResponseBuilder().code("3001").description("数据未找到").build());
        responseList.add(new ResponseBuilder().code("5001").description("没有访问权限").build());
        responseList.add(new ResponseBuilder().code("4001").description("服务器内部错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponses(HttpMethod.GET, responseList)
                .globalResponses(HttpMethod.POST, responseList)
                .globalResponses(HttpMethod.PUT, responseList)
                .globalResponses(HttpMethod.DELETE, responseList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jiesz.writinghome"))
                .paths(PathSelectors.any())
                .build()             //添加登录认证
                .globalRequestParameters(params());
    }

    private List<RequestParameter> params() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder();

        List<RequestParameter> requestParameters = new ArrayList<>();
        RequestParameter token = parameterBuilder.name(HttpHeaders.AUTHORIZATION).description("token").in(ParameterType.HEADER)
                .required(false).build();
        requestParameters.add(token);
        return requestParameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("writing-home API文档")
                .description("writing-home API文档")
                .contact(new Contact("jiesz", null, null))
                .version("1.0")
                .build();
    }


}
