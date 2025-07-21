package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.RealState;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IRealStateRepository;
import com.nk.realstateapi.services.IRealStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealStateServiceImpl extends CRUDImpl<RealState, Integer> implements IRealStateService {

    private final IRealStateRepository repository;


    @Override
    protected IGenericRepository<RealState, Integer> getRepo() {
        return repository;
    }
}
