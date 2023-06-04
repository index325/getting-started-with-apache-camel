package com.index325.apachecamelms.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("files-input-route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} ends with 'xml' "))
                        .log("XML FILE ${body}")
                    .when(simple("${body} contains 'USD' "))
                        .log("Contains USD")
                    .otherwise()
                        .log("NOT A XML FILE ${body}")
                    .when()
                        .body()
                .end()
                .log("${body}")
                .to("file:files/output");
    }
}
