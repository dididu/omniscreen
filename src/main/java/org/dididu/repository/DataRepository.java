package org.dididu.repository;

import org.dididu.domain.TemplateData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends MongoRepository<TemplateData, String> {
}
