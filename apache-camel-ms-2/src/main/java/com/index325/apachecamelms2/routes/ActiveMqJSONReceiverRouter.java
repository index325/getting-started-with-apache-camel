package com.index325.apachecamelms2.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActiveMqJSONReceiverRouter extends RouteBuilder {

    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Autowired
    private MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
            .unmarshal()
            .json(JsonLibrary.Jackson, CurrencyExchange.class)
            .bean(myCurrencyExchangeProcessor)
            .bean(myCurrencyExchangeTransformer)
            .to("log:received-message-from-active-mq");
    }
}

@Component
class MyCurrencyJSONExchangeProcessor {

    Logger logger = LoggerFactory.getLogger(MyCurrencyJSONExchangeProcessor.class);

    public void processMessage(CurrencyExchange currencyExchange) {

        logger.info("The value currently is {}", currencyExchange.getConversionMultiple());

    }

}

@Component
class MyCurrencyJSONExchangeTransformer {

    Logger logger = LoggerFactory.getLogger(MyCurrencyJSONExchangeTransformer.class);

    public CurrencyExchange transformMessage(CurrencyExchange currencyExchange) {

        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        return currencyExchange;

    }

}