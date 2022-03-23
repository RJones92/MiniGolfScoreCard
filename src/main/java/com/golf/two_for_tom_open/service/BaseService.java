package com.golf.two_for_tom_open.service;

import java.util.List;

public interface BaseService<T> {

    List<T> getAll();

    T save(T object);

}
