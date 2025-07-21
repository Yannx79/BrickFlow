package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Company;
import com.nk.realstateapi.repositories.ICompanyRepository;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.services.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends CRUDImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepository repository;


    @Override
    protected IGenericRepository<Company, Integer> getRepo() {
        return repository;
    }
}
