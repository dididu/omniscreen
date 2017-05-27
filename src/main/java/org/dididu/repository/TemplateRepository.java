package org.dididu.repository;

import org.dididu.domain.TemplateDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends MongoRepository<TemplateDefinition, String> {
}
