package ifpb.springkafkaconsumer.service;

import ifpb.springkafkaconsumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FollowKafkaListener {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics="${kafka.topic.follow}", groupId = "follow-listener-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(String event){
        System.out.println(event);

    }

}
