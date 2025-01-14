package com.catering.service.helper;
import org.modelmapper.ModelMapper;
import java.lang.reflect.ParameterizedType;

public class BaseServiceHelper<E, D> {
    private static final ModelMapper modelMapper = new ModelMapper() {
    };

    protected E convertForCreate(D d) {
        return modelMapper.map(d, getEntityClass());
    }

    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<D> getDtoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
