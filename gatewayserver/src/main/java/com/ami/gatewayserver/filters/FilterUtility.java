package com.ami.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID="bank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders){
        if(requestHeaders.get(CORRELATION_ID)!=null){
            List<String> requestHeadersList=requestHeaders.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        }else{
            return null;
        }
    }

    public ServerWebExchange setRequestHeaders(ServerWebExchange exchange, String name, String value){
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId){
        return this.setRequestHeaders(exchange, CORRELATION_ID, correlationId);
    }
}
