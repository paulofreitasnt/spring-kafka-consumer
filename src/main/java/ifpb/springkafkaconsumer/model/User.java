package ifpb.springkafkaconsumer.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node("User")
public class User {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;

    @Relationship(type="FOLLOW", direction = Relationship.Direction.OUTGOING)
    private List<User> following;

}
