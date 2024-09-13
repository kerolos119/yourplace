package com.example.yourplace.service;

import com.example.yourplace.document.Property;
import com.example.yourplace.dto.PropertyDto;
import com.example.yourplace.enim.PropertyType;
import com.example.yourplace.mapper.PropertyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    MongoTemplate template;
    @Autowired
    PropertyMapper mapper;


    public String save(PropertyDto property) {
        return template.save(mapper.toEntity(property).getId());
    }

    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        template.remove(query,Property.class);
    }
    
    public PropertyDto getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mapper.toDto (template.findOne(query,Property.class));
    }

    public void update(String id, PropertyDto property) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Property property1=template.findOne(query,Property.class);
        template.save(mapper.updateToEntity(property,property1));
    }

    public List<PropertyDto> getBySearch(String name, PropertyType type) {
        Query query = new Query();
        if (name!=null){
        query.addCriteria(Criteria.where("name").regex(name,"1"));
        }
        if (type!=null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        return template.find(query,Property.class).stream().map(property -> {
            return mapper.toDto(property);
        }).collect(Collectors.toList());
    }
}
