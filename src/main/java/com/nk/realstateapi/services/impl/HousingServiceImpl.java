package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Company;
import com.nk.realstateapi.entities.Housing;
import com.nk.realstateapi.repositories.ICompanyRepository;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IHousingRepository;
import com.nk.realstateapi.services.ICompanyService;
import com.nk.realstateapi.services.IHousingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HousingServiceImpl extends CRUDImpl<Housing, Integer> implements IHousingService {

    private final IHousingRepository repository;


    @Override
    protected IGenericRepository<Housing, Integer> getRepo() {
        return repository;
    }
}
