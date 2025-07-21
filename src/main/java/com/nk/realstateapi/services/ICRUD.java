package com.nk.realstateapi.services;

import java.util.List;

public interface ICRUD<T, K> {

    T save(T t) throws Exception;

    T update(T t, K k) throws Exception;

    List<T> readAll() throws Exception;

    T readById(K k) throws Exception;

    void delete(K k) throws Exception;

}
