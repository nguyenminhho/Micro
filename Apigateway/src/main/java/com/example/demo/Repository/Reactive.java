package com.example.demo.Repository;

import com.example.demo.Dto.VerifierTokenRequest;
import com.example.demo.Dto.VerifierTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface Reactive {
    @PostExchange(url = "/verifier", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<VerifierTokenResponse> verifyToken(@RequestBody VerifierTokenRequest verifierTokenRequest);
}

