package com.nk.realstateapi.services.impl;

import com.nk.realstateapi.entities.Promotion;
import com.nk.realstateapi.repositories.IGenericRepository;
import com.nk.realstateapi.repositories.IPromotionRepository;
import com.nk.realstateapi.services.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionServiceImpl extends CRUDImpl<Promotion, Integer> implements IPromotionService {

    private final IPromotionRepository repository;


    @Override
    protected IGenericRepository<Promotion, Integer> getRepo() {
        return repository;
    }

    @Override
    @CacheEvict(value = "PromotionServiceImplCache", allEntries = true)
    public Promotion save(Promotion promotion) throws Exception {
        return super.save(promotion);
    }

    @Override
    @CacheEvict(value = "PromotionServiceImplCache", allEntries = true)
    public Promotion update(Promotion promotion, Integer integer) throws Exception {
        return super.update(promotion, integer);
    }

    @Override
    @CacheEvict(value = "PromotionServiceImplCache", allEntries = true)
    public List<Promotion> readAll() throws Exception {
        return super.readAll();
    }

    @Override
    @Cacheable(value = "PromotionServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public Promotion readById(Integer integer) throws Exception {
        return super.readById(integer);
    }

    @Override
    @Cacheable(value = "PromotionServiceImplCache", keyGenerator = "versionedKeyGenerator")
    public void delete(Integer integer) throws Exception {
        super.delete(integer);
    }
}
