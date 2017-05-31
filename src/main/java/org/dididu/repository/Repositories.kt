package org.dididu.repository

import org.dididu.domain.StyleDefinition
import org.dididu.domain.TemplateData
import org.dididu.domain.TemplateDefinition
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : MongoRepository<TemplateDefinition?, String>

@Repository
interface StyleRepository : MongoRepository<StyleDefinition?, String>

@Repository
interface DataRepository : MongoRepository<TemplateData?, String>
