package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.RealState;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IRealStateRepository;
import com.nk.realstateapi.services.IRealStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealStateServiceImpl extends CRUDImpl<RealState, Integer> implements IRealStateService {

    private final IRealStateRepository repository;


    @Override
    protected IGenericRepository<RealState, Integer> getRepo() {
        return repository;
    }

    @Override
    @CacheEvict(value = "RealStateServiceImplCache", allEntries = true)
    public RealState save(RealState realState) throws Exception {
        return super.save(realState);
    }

    @Override
    @CacheEvict(value = "RealStateServiceImplCache", allEntries = true)
    public RealState update(RealState realState, Integer integer) throws Exception {
        return super.update(realState, integer);
    }

    @Override
    @Cacheable(value = "CompanyServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public List<RealState> readAll() throws Exception {
        return super.readAll();
    }

    @Override
    @Cacheable(value = "CompanyServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public RealState readById(Integer integer) throws Exception {
        return super.readById(integer);
    }

    @Override
    @CacheEvict(value = "RealStateServiceImplCache", allEntries = true)
    public void delete(Integer integer) throws Exception {
        super.delete(integer);
    }
}
