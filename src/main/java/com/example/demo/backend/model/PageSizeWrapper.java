package com.example.demo.backend.model;

import lombok.*;

/**
 * I am not able to get Integer value from rest service
 * as follow: restTemplate.getForObject("url",Integer.class)
 * so, wrapper class have been created
 *
 * update
 * if Im not mistaken, it is ok to use getForEntity(...
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageSizeWrapper {
    private Integer pageSize;
}
