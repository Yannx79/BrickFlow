package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Population;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IPopulationRepository;
import com.nk.realstateapi.services.IPopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PopulationServiceImpl extends CRUDImpl<Population, Integer> implements IPopulationService {

    private final IPopulationRepository repository;


    @Override
    protected IGenericRepository<Population, Integer> getRepo() {
        return repository;
    }
}
