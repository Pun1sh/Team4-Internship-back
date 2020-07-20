package com.exadel.booking.utils.modelmapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AMapper <E, D> {

    @Autowired
    protected ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public AMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }


    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
    }

    public E toLiteEntity(D dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
    }

    public D toDto(E entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
    }


    public D toLiteDto(E entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
    }


    public List<D> toListDto(List<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFieldsToEntityConverter(source, destination);
            return context.getDestination();
        };
    }

    public Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFieldsToDtoConverter(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFieldsToDtoConverter(E source, D destination) {
    }

    void mapSpecificFieldsToEntityConverter(D source, E destination) {
    }
}
