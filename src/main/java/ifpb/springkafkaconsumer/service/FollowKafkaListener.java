package ifpb.springkafkaconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifpb.springkafkaconsumer.dto.FollowEventDto;
import ifpb.springkafkaconsumer.dto.UserCreateEventDto;
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

    @KafkaListener(topics="${kafka.topic.follow}", groupId = "follow-listener-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(Map<String, Object> eventMap){
        System.out.println(eventMap);
        String action = eventMap.get("action").toString();
        switch (action) {
            case "CREATE_USER" ->{
                UserCreateEventDto dto = objectMapper.convertValue(eventMap, UserCreateEventDto.class);
                User user = new User();
                user.setId(dto.id());
                user.setEmail(dto.email());
                userRepository.save(user);
            }
            case "FOLLOW" -> {
                FollowEventDto dto = objectMapper.convertValue(eventMap, FollowEventDto.class);

            }
        }
    }

}
