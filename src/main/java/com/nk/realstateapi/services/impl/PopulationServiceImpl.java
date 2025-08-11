package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Population;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IPopulationRepository;
import com.nk.realstateapi.services.IPopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PopulationServiceImpl extends CRUDImpl<Population, Integer> implements IPopulationService {

    private final IPopulationRepository repository;

    @Override
    protected IGenericRepository<Population, Integer> getRepo() {
        return repository;
    }

    @Override
    @CacheEvict(value = "PopulationServiceImplCache", allEntries = true)
    public Population save(Population population) throws Exception {
        return super.save(population);
    }

    @Override
    @CacheEvict(value = "PopulationServiceImplCache", allEntries = true)
    public Population update(Population population, Integer integer) throws Exception {
        return super.update(population, integer);
    }

    @Override
    @Cacheable(value = "PopulationServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public List<Population> readAll() throws Exception {
        return super.readAll();
    }

    @Override
    @Cacheable(value = "PopulationServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public Population readById(Integer integer) throws Exception {
        return super.readById(integer);
    }

    @Override
    @CacheEvict(value = "PopulationServiceImplCache", allEntries = true)
    public void delete(Integer integer) throws Exception {
        super.delete(integer);
    }
}
