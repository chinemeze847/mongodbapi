package com.example.mymongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address
{
    private String country;
    private String city;
    private String postalCode;
}
