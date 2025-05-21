package com.ibas.safetynet.kafka;

import com.ibas.safetynet.data.dao.BrnInfoDao;
import com.ibas.safetynet.data.dao.MfsAccOwnerInfoDao;
import com.ibas.safetynet.data.dao.NidInfoDao;
import com.ibas.safetynet.data.payload.brn.BrnInfoDto;
import com.ibas.safetynet.data.payload.brn.BrnInfoSaveDto;
import com.ibas.safetynet.data.payload.mfs.MfsAccOwnerInfoDto;
import com.ibas.safetynet.data.payload.mfs.MfsAccOwnerInfoSaveDto;
import com.ibas.safetynet.data.payload.nid.NidInfoSaveDto;
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
    private final BrnInfoDao brnInfoDao;
    private final NidInfoDao nidInfoDao;
    private final MfsAccOwnerInfoDao mfsAccOwnerInfoDao;

    @Value("${kafka.brnTopic}")
    private String brnTopic;

    @Value("${kafka.nidTopic}")
    private String nidTopic;

    @Value("${kafka.mfsTopic}")
    private String mfsTopic;

    public void publishBrnMessage(BrnInfoSaveDto dto) {
        try {
            kafkaTemplate.send(brnTopic, dto.getUbrn(), dto);
        } catch (Exception e) {
            log.error("Error in publishing BRN message\n{}", e.getMessage());
        }
    }

    public void publishNidMessage(NidInfoSaveDto dto) {
        try {
            kafkaTemplate.send(nidTopic, dto.getNationalId(), dto);
        } catch (Exception e) {
            log.error("Error in publishing NID message\n{}", e.getMessage());
        }
    }

    public void publishMfsMessage(MfsAccOwnerInfoSaveDto dto) {
        try {
            String key = dto.getNid() + "-" + dto.getMobileNumber() + "-" + dto.getMfsId();
            kafkaTemplate.send(mfsTopic, key, dto);
        } catch (Exception e) {
            log.error("Error in publishing MFS message\n{}", e.getMessage());
        }
    }

    @KafkaListener(topics = "${kafka.brnTopic}",
            clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory",
            properties = {
                    "max.poll.interval.ms:300000",
                    "max.poll.records:50"})
    public void listenAsSingleRegistryObject(ConsumerRecord<String, BrnInfoSaveDto> cr,
                                             @Payload BrnInfoSaveDto payload, Acknowledgment ack) {
        log.info("Listened to BRN Key: {}", cr.key());
        brnInfoDao.saveBrnInfo(payload);
        ack.acknowledge();
        log.info("Acknowledged BRN Key: {}", cr.key());
    }

    @KafkaListener(topics = "${kafka.nidTopic}",
            clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory",
            properties = {
                    "max.poll.interval.ms:300000",
                    "max.poll.records:50"})
    public void listenAsSingleRegistryObject(ConsumerRecord<String, NidInfoSaveDto> cr,
                                             @Payload NidInfoSaveDto payload, Acknowledgment ack) {
        log.info("Listened to NID Key: {}", cr.key());
        nidInfoDao.saveNidInfo(payload);
        ack.acknowledge();
        log.info("Acknowledged NID Key: {}", cr.key());
    }

    @KafkaListener(topics = "${kafka.mfsTopic}",
            clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory",
            properties = {
                    "max.poll.interval.ms:300000",
                    "max.poll.records:50"})
    public void listenAsSingleRegistryObject(ConsumerRecord<String, MfsAccOwnerInfoSaveDto> cr,
                                             @Payload MfsAccOwnerInfoSaveDto payload, Acknowledgment ack) {
        log.info("Listened to MFS Key: {}", cr.key());
        mfsAccOwnerInfoDao.saveMfsAccOwnerInfo(payload);
        ack.acknowledge();
        log.info("Acknowledged MFS Key: {}", cr.key());
    }
}
