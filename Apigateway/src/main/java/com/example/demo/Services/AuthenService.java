package com.example.demo.Services;

import com.example.demo.Dto.VerifierTokenRequest;
import com.example.demo.Dto.VerifierTokenResponse;
import com.example.demo.Repository.Reactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthenService {
    @Autowired
    Reactive reactive;

    public Mono<VerifierTokenResponse> verifyToken(String token){
        return reactive.verifyToken(VerifierTokenRequest.builder()
                        .token(token)
                .build());
    }

}
