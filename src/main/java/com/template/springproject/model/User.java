package com.template.springproject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
}
