package com.ibas.safetynet.kafka;

import com.ibas.safetynet.data.payload.BrnInfoDto;
import com.ibas.safetynet.data.payload.MfsAccOwnerInfoDto;
import com.ibas.safetynet.data.payload.Nid.NidInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.brnTopic}")
    private String brnTopic;

    @Value("${kafka.nidTopic}")
    private String nidTopic;

    @Value("${kafka.mfsTopic}")
    private String mfsTopic;

    public void publishBrnMessage(BrnInfoDto dto) {
        try {
            kafkaTemplate.send(brnTopic, dto.getUbrn(), dto);
        } catch (Exception e) {
            log.error("Error in publishing BRN message\n{}", e.getMessage());
        }
    }

    public void publishNidMessage(NidInfoDto dto) {
        try {
            kafkaTemplate.send(nidTopic, dto.getNationalId(), dto);
        } catch (Exception e) {
            log.error("Error in publishing NID message\n{}", e.getMessage());
        }
    }

    public void publishMfsMessage(MfsAccOwnerInfoDto dto) {
        try {
            String key = dto.getNid() + "-" + dto.getMobileNumber() + "-" + dto.getMfsId();
            kafkaTemplate.send(mfsTopic, key, dto);
        } catch (Exception e) {
            log.error("Error in publishing MFS message\n{}", e.getMessage());
        }
    }

//    @KafkaListener(topics = "${kafka.brnTopic}",
//            clientIdPrefix = "json",
//            containerFactory = "kafkaListenerContainerFactory",
//            properties = {
//                    "max.poll.interval.ms:300000",
//                    "max.poll.records:50"})
//    public void listenAsSingleRegistryObject(ConsumerRecord<String, BrnInfoDto> cr,
//                                             @Payload BrnInfoDto payload, Acknowledgment ack) {
//        log.info("BRN Key: {}", cr.key());
//        ack.acknowledge();
//    }
//
//    @KafkaListener(topics = "${kafka.nidTopic}",
//            clientIdPrefix = "json",
//            containerFactory = "kafkaListenerContainerFactory",
//            properties = {
//                    "max.poll.interval.ms:300000",
//                    "max.poll.records:50"})
//    public void listenAsSingleRegistryObject(ConsumerRecord<String, NidInfoDto> cr,
//                                             @Payload NidInfoDto payload, Acknowledgment ack) {
//        log.info("NID Key: {}", cr.key());
//        ack.acknowledge();
//    }
//
//    @KafkaListener(topics = "${kafka.mfsTopic}",
//            clientIdPrefix = "json",
//            containerFactory = "kafkaListenerContainerFactory",
//            properties = {
//                    "max.poll.interval.ms:300000",
//                    "max.poll.records:50"})
//    public void listenAsSingleRegistryObject(ConsumerRecord<String, MfsAccOwnerInfoDto> cr,
//                                             @Payload MfsAccOwnerInfoDto payload, Acknowledgment ack) {
//        log.info("MFS Key: {}", cr.key());
//        ack.acknowledge();
//    }
}
