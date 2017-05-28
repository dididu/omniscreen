package org.dididu.repository

import org.dididu.domain.TemplateData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DataRepository : MongoRepository<TemplateData?, String>
