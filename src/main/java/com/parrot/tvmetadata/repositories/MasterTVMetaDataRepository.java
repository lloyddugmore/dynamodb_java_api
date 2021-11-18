package com.parrot.tvmetadata.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
@EnableScanCount
public interface MasterTVMetaDataRepository extends CrudRepository<MasterTVMetaData, String> {
    Iterable<MasterTVMetaData> findByParrotIdIn(List<String> parrotIds);
    Iterable<MasterTVMetaData> findAll(Pageable pageRequest);
}
