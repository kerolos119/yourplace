package com.example.yourplace.mapper;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<D,E> implements Mapper<D,E> {
    @Autowired
    @Setter
    ModelMapper mapper;
    private Class<E> eClass;
    private Class<D> dClass;
    public  AbstractMapper(Class<E> eClass,Class<D> dClass){
        this.eClass=eClass;
        this.dClass=dClass;
    }
    @PostConstruct
    public void init(){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());


    }
    @Override
    public D toDto(E entity){
        return mapper.map(entity,dClass);
    }
    @Override
    public E toEntity(D dto){
        return mapper.map(dto,eClass);
    }


}
