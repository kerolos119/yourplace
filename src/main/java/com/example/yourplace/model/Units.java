package com.example.yourplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Units {
    private String name;
    private String availableUnits;
    private String description;
    private Float price;
    private String beds;
    private String capacity;
}
