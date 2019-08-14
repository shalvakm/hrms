package com.example.leaveservice.listener;

import com.example.leaveservice.controller.RemLeavesController;
import com.example.leaveservice.Model.RemLeaves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private RemLeavesController remLeavesController;

//    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }


    @KafkaListener(topics = "HrmsTopic", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(RemLeaves remLeaves) {
        System.out.println("Created Remaining Leaves for: " + remLeaves.getEmployeeId());
        remLeavesController.createRemLeave(remLeaves);
    }
}