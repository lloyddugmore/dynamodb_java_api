package com.parrot.tvmetadata.services;

import com.parrot.tvmetadata.repositories.MasterTVMetaData;
import com.parrot.tvmetadata.repositories.MasterTVMetaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetaDataService {

    @Autowired
    MasterTVMetaDataRepository repository;

    @Autowired
    CacheManager cacheManager;

    @Cacheable("findByParrotIds")
    public Iterable<MasterTVMetaData> findByParrotIds (List<String> parrotId) {
        return repository.findByParrotIdIn(parrotId);
    }

    @Cacheable("findAll")
    public Iterable<MasterTVMetaData> findAll (Pageable pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Scheduled(fixedRate = 10000)
    public void evictAllCaches() {
        log.info("--- Evicting caches : " + cacheManager.getCacheNames());
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
