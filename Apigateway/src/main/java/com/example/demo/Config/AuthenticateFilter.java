package com.example.demo.Config;

import com.example.demo.Dto.VerifierTokenRequest;
import com.example.demo.Dto.VerifierTokenResponse;
import com.example.demo.Services.AuthenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticateFilter implements GlobalFilter, Ordered {
    @Autowired
    AuthenService authenService;

    private static String[] PUPBLIC = {"/login","/email/send"};


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

        if(isPublic(exchange.getRequest())){
            return chain.filter(exchange);
        }

        if(CollectionUtils.isEmpty(authHeader)){
            try {
                throw new Exception("Invalid Token");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        String token = authHeader.getFirst().replace("Bearer " , "");
        return authenService.verifyToken(token).flatMap(verifierTokenResponse -> {
                    if (verifierTokenResponse.isVerifier()) {
                        //Tiep tuc thuc hien cac filter khac
                        return chain.filter(exchange);
                    } else {
                        return Mono.error(new Exception("Token wrong"));
                    }
                });
    }

    private boolean isPublic(ServerHttpRequest request){
       return Arrays.stream(PUPBLIC).anyMatch(p -> request.getURI().getPath().matches(p));
    }


    @Override
    public int getOrder() {
        return -1;
    }
}
