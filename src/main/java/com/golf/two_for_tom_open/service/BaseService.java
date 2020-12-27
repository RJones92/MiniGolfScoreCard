package com.golf.two_for_tom_open.service;

import java.util.List;

public interface BaseService<T> {

    public List<T> getAll();
    public T save(T object);

}
