package com.example.yourplace.dto;

import com.example.yourplace.enim.PropertyType;
import com.example.yourplace.model.Location;
import com.example.yourplace.model.Units;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    private String id;
    private String name;
    private String description;
    private Location location;
    private List<Units> units;
    private PropertyType propertyType;
}
