package ifpb.springkafkaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FollowService {

    @Autowired
    private Neo4jClient neo4jClient;

    public void createFollowRelationship(String followerEmail, String followingEmail) {
        String cypher = """
            MATCH (follower:User {email: $followerEmail})
            MATCH (following:User {email: $followingEmail})
            MERGE (follower)-[:FOLLOW]->(following)
            """;
        neo4jClient.query(cypher)
                .bindAll(Map.of(
                        "followerEmail", followerEmail,
                        "followingEmail", followingEmail
                ))
                .run();
    }
}
