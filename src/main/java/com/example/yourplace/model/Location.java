package com.example.yourplace.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Location {
    private String country;
    private String city;
    private String street;


    public String toString(){
        return street+","+city+","+country;
    }
}
