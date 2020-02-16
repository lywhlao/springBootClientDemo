package com.example.demo.service.messageQueue;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-15 16:15
 * @Description:
 */
public class KafkaTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 2);
        props.put("batch.size", 100);
        props.put("compression.type", "gzip");
        props.put("linger.ms", 50);
        props.put("buffer.memory", 33554432);
        props.put("max.request.size", 52428800);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "123");
        KafkaProducer kafkaProducer = new KafkaProducer(props);
        ProducerRecord<String, String> keyedMessage = new ProducerRecord("test", "key"+ RandomUtils.nextInt(1,100), "message");
//        kafkaProducer.send(keyedMessage);


        Consumer<String, String> consumer = createConsumer("127.0.0.1:9092", "consumerGroup", "test");


        //1.初始化
        kafkaProducer.initTransactions();


        //2.开启事务
        kafkaProducer.beginTransaction();

        ConsumerRecords<String, String> poll = consumer.poll(100);

        Map<TopicPartition, OffsetAndMetadata> offsets=new HashMap<>();

        //process
        Iterator<ConsumerRecord<String, String>> iterator = poll.iterator();

        while (iterator.hasNext()) {
            ConsumerRecord<String, String> record = poll.iterator().next();

            //process
//            record.value();

            //这里可能设计多个topic和partition
            offsets.put(new TopicPartition(record.topic(),record.partition()),new OffsetAndMetadata(record.offset()));

        }

        kafkaProducer.send(keyedMessage);

        //提交consume的偏移量
        kafkaProducer.sendOffsetsToTransaction(offsets,"consumerGroup");

        //
        kafkaProducer.commitTransaction();
    }


    public static void process(ConsumerRecord<String, String> record){
        System.out.println(String.format("topic:%s,offset:{},key:%s,value:%s",record.topic(),record.offset(),record.key(),record.value()));
    }

    public static Consumer<String, String> createConsumer(String bootStrap, String group, String topic) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 131072);//128k

//        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY,"org.apache.kafka.clients.consumer.RangeAssignor");

//        props.put("zookeeper.connect", zooKeeper);
//        props.put("group.id", group);
//        props.put("zookeeper.session.timeout.ms", "400");
//        props.put("zookeeper.sync.time.ms", "200");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("enable.auto.commit", "false");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList(topic));
        return consumer;
    }

}
