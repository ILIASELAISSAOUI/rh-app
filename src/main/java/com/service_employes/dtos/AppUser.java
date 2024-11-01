package com.service_employes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    private String username;
    private String password;
    private List<String> roles;
}
