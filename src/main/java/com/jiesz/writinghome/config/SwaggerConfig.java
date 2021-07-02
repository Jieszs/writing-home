package com.jiesz.writinghome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
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

    /**
     * 配置api文档
     */
    @Bean
    public Docket configApiDocs() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(1001).message("请求参数格式错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(2002).message("系统外部接口调用异常").build());
        responseMessageList.add(new ResponseMessageBuilder().code(3001).message("数据未找到").build());
        responseMessageList.add(new ResponseMessageBuilder().code(5001).message("没有访问权限").build());
        responseMessageList.add(new ResponseMessageBuilder().code(4001).message("服务器内部错误").build());
        StringBuilder desc = new StringBuilder();

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(new ApiInfoBuilder()
                        .title("writing-home API文档")
                        .description(desc.toString())
                        .contact(new Contact("jiesz", null, ""))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jiesz.writinghome"))
                .paths(PathSelectors.any())
                .build();
    }


}
