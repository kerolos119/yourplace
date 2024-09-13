package com.example.yourplace.controller;

import com.example.yourplace.document.Property;
import com.example.yourplace.dto.PropertyDto;
import com.example.yourplace.enim.PropertyType;
import com.example.yourplace.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public String save(@RequestBody PropertyDto property){
        return propertyService.save(property);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
         propertyService.delete(id);
    }
    @GetMapping("/{id}")
    public PropertyDto getById(@PathVariable String id){
        return propertyService.getById(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable String id,@RequestBody PropertyDto property){
        propertyService.update(id,property);
    }
    @GetMapping("/search")
    public List<PropertyDto> search(@RequestParam (required = false) String name , @RequestParam (required = false) PropertyType type){
        return propertyService.getBySearch(name,type);
    }
}
