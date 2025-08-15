package ifpb.springkafkaconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FollowKafkaListener {

    @KafkaListener(topics="${kafka.topic.follow}", groupId = "follow-listener-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(String event){
        System.out.println(event);
    }

}
