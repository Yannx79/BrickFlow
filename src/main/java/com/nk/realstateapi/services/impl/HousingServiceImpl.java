package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Housing;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IHousingRepository;
import com.nk.realstateapi.services.IHousingService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HousingServiceImpl extends CRUDImpl<Housing, Integer> implements IHousingService {

    private final IHousingRepository repository;

    @Override
    protected IGenericRepository<Housing, Integer> getRepo() {
        return repository;
    }

    @Override
    @CacheEvict(value = "HousingServiceImplCache", allEntries = true)
    public Housing save(Housing housing) throws Exception {
        return super.save(housing);
    }

    @Override
    @CacheEvict(value = "HousingServiceImplCache", allEntries = true)
    public Housing update(Housing housing, Integer integer) throws Exception {
        return super.update(housing, integer);
    }

    @Override
    @Cacheable(value = "HousingServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public List<Housing> readAll() throws Exception {
        return super.readAll();
    }

    @Override
    @Cacheable(value = "HousingServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public Housing readById(Integer integer) throws Exception {
        return super.readById(integer);
    }

    @Override
    @CacheEvict(value = "HousingServiceImplCache", allEntries = true)
    public void delete(Integer integer) throws Exception {
        super.delete(integer);
    }
}
