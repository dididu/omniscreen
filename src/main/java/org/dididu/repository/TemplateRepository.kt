package org.dididu.repository

import org.dididu.domain.TemplateDefinition
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : MongoRepository<TemplateDefinition?, String>
