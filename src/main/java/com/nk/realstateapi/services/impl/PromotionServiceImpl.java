package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Promotion;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IPromotionRepository;
import com.nk.realstateapi.services.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl extends CRUDImpl<Promotion, Integer> implements IPromotionService {

    private final IPromotionRepository repository;


    @Override
    protected IGenericRepository<Promotion, Integer> getRepo() {
        return repository;
    }
}
