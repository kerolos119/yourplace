package com.example.yourplace.mapper;

public interface Mapper<D,E> {
    public E toEntity(D dto);
    public E updateToEntity(D dto, E entity);
    D toDto(E entity);
}
