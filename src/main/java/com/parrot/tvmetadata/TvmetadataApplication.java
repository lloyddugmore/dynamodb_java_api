package com.parrot.tvmetadata;

import com.parrot.tvmetadata.repositories.MasterTVMetaDataRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEncryptableProperties
@EnableDynamoDBRepositories(
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
						MasterTVMetaDataRepository.class}
				)
		})
@EncryptablePropertySource("encrypted.properties")
@EnableCaching
@EnableScheduling
public class TvmetadataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvmetadataApplication.class, args);
	}
}
