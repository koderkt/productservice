package com.koderkt.productservice.commons;

import com.koderkt.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> responseBody = restTemplate.postForEntity(
                "http://localhost:8090/users/validate/" + token,
                null,
                UserDto.class);
        if (responseBody.getBody() == null) {
            return null;
        }
        return responseBody.getBody();
    }
}
