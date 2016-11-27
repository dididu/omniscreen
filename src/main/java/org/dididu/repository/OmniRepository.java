package org.dididu.repository;

import org.dididu.domain.ClientState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmniRepository extends MongoRepository<ClientState, String> {
}
