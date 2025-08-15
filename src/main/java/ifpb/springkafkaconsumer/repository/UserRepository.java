package ifpb.springkafkaconsumer.repository;

import ifpb.springkafkaconsumer.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {
}
