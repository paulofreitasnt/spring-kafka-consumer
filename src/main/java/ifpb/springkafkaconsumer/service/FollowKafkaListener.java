package ifpb.springkafkaconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifpb.springkafkaconsumer.model.User;
import ifpb.springkafkaconsumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FollowKafkaListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FollowService followService;

    @KafkaListener(topics="${kafka.topic.follow}", groupId = "follow-listener-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(Map<String, Object> eventMap){
        System.out.println(eventMap);
        String action = eventMap.get("action").toString();
        switch (action) {
            case "CREATE_USER" ->{
                Long id = Long.valueOf(eventMap.get("id").toString());
                String email = (String) eventMap.get("email");
                User user = new User();
                user.setId(id);
                user.setEmail(email);
                userRepository.save(user);
            }
            case "FOLLOW" -> {
                String followerEmail = eventMap.get("followerEmail").toString();
                String followingEmail = eventMap.get("followingEmail").toString();
                followService.createFollowRelationship(followerEmail, followingEmail);
            }
        }
    }

}
