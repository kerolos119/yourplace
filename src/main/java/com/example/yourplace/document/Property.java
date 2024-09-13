package com.example.yourplace.document;

import com.example.yourplace.enim.PropertyType;
import com.example.yourplace.model.Location;
import com.example.yourplace.model.Units;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Property {
    @Id
    private String id;
    private String name;
    private String description;
    private Location location;
    private List<Units> units;
    private PropertyType propertyType;

}
