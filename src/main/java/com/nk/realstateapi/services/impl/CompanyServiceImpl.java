package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Company;
import com.nk.realstateapi.repositories.ICompanyRepository;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl extends CRUDImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepository repository;


    @Override
    protected IGenericRepository<Company, Integer> getRepo() {
        return repository;
    }

    @Override
    @CacheEvict(value = "CompanyServiceImplCache", allEntries = true)
    public Company save(Company company) throws Exception {
        return super.save(company);
    }

    @Override
    @CacheEvict(value = "CompanyServiceImplCache", allEntries = true)
    public Company update(Company company, Integer integer) throws Exception {
        return super.update(company, integer);
    }

    @Override
    @Cacheable(value = "CompanyServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public List<Company> readAll() throws Exception {
        return super.readAll();
    }

    @Override
    @Cacheable(value = "CompanyServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public Company readById(Integer integer) throws Exception {
        return super.readById(integer);
    }

    @Override
    @CacheEvict(value = "CompanyServiceImplCache", allEntries = true)
    public void delete(Integer integer) throws Exception {
        super.delete(integer);
    }

    protected String getCacheName() {
        return this.getClass().getSimpleName() + "Cache";
    }

}
