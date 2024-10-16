package com.koderkt.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<com.koderkt.productservice.dtos.Role> roles;
    private boolean isEmailVerified;

}




