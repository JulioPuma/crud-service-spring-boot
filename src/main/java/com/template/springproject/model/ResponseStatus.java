package com.template.springproject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseStatus {
    private String status;
    private String description;
}
