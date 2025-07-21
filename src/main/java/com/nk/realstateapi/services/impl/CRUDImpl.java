package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.services.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, K> implements ICRUD<T, K> {

    protected abstract IGenericRepository<T, K> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, K k) throws Exception {
        getRepo().findById(k).orElseThrow(() -> new Exception("ID NOT FOUND: " + k));
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(K k) throws Exception {
        return getRepo().findById(k).orElseThrow(() -> new Exception("ID NOT FOUND: " + k));
    }

    @Override
    public void delete(K k) throws Exception {
        getRepo().findById(k).orElseThrow(() -> new Exception("ID NOT FOUND: " + k));
        getRepo().deleteById(k);
    }

}
