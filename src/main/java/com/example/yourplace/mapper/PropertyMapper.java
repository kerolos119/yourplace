package com.example.yourplace.mapper;

import com.example.yourplace.document.Property;
import com.example.yourplace.dto.PropertyDto;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper extends AbstractMapper<PropertyDto, Property> {


    public PropertyMapper() {super(Property.class, PropertyDto.class);}
    @Override
    public Property updateToEntity(PropertyDto dto, Property entity) {
        if (dto.getName() !=null && !dto.getName().isEmpty())
            entity.setName(dto.getName());
        if (dto.getDescription() !=null && !dto.getDescription().isEmpty())
            entity.setDescription(dto.getDescription());
        if (dto.getUnits() !=null && !dto.getUnits().isEmpty())
            entity.setUnits(dto.getUnits());
        if (dto.getPropertyType()!=null )
            entity.setPropertyType(dto.getPropertyType());

        return entity;
    }

}
