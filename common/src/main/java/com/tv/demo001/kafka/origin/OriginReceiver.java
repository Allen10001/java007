package com.tv.demo001.kafka.origin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

@Component
@Slf4j
public class OriginReceiver {


    /**
     * 自动提交偏移量
     */
    public void receiveWithAutoCommit(){

        Properties props = new Properties();

        /**
         * 集群是通过配置bootstrap.servers指定一个或多个broker。不用指定全部的broker，它将自动发现集群中的其余的borker（最好指定多个，万一有服务器故障）。
         */
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "origin_group_id_001");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", 10);
        props.put("heartbeatInterval", 1000);
        props.put("session.timeout.ms", 10000);
        props.put("isolation.level","read_committed");

        KafkaConsumer consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList("my-topic"));

        while(true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(30));
            for(ConsumerRecord<String, String> record : consumerRecords){
                log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
            }
        }
    }

    /**
     * 手动控制偏移量
     */
    public void receivedWithManualCommit(){

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "origin_group_id_002");
        props.put("enable.auto.commit", false);
        props.put("auto.commit.interval.ms", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", 10);
        props.put("heartbeatInterval", 1000);
        props.put("session.timeout.ms", 10000);
        //props.put("isolation.level","read_committed");

        /**
         * What to do when there is no initial offset in Kafka or if the current offset does not exist any more on the server
         * (e.g. because that data has been deleted):
         * <ul>
         * <li>earliest: automatically reset the offset to the earliest offset
         * <li>latest: automatically reset the offset to the latest offset</li>
         * <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li>
         * <li>anything else: throw exception to the consumer.
         *
         * latest和earliest区别
         *
         * 1，earliest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
         *
         * 2，latest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
         *
         * 提交过offset，latest和earliest没有区别，但是在没有提交offset情况下，用latest直接会导致无法读取旧数据。
         */
        props.put("auto.offset.reset","latest");

        KafkaConsumer consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList("my-topic"));

        log.info("topic and partition size: {}",consumer.assignment().size());


        consumer.poll(0);
        /**
         * kafka 使用 seek(TopicPartition, long) 指定新的消费位置。
         * 用于查找服务器保留的最早和最新的 offset 的特殊的方法也可用（seekToBeginning(Collection) 和 seekToEnd(Collection)）。
         * seek之前应该先poll一下
         */
        //consumer.seek((TopicPartition)consumer.assignment().iterator().next(), 10);
        // consumer.wakeup();

        while (true) {

            /**
             * timeout: The maximum time to block (must not be greater than {@link Long#MAX_VALUE} milliseconds)
             * Set Duration.ofSeconds(10) in this example.
             */
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(5));

            /**
             * process records
             */
            for(ConsumerRecord record : records){
                log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
            }

            /**
             * commit offset manually
             *
             手动提交偏移量。这样我们可以准确控制消息是成功消费的。
             这种方式就是所谓的“至少一次”保证，在故障情况下，可以重复。
             使用手动偏移控制的优点是，您可以直接控制记录何时被视为“已消耗”。
             */
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception != null){
                        log.error("manually commit error, {} ", exception.getMessage());
                    }

                    for(Map.Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()){
                        log.info("topic_partition: {}, offset: {}", entry.getKey().toString(), entry.getValue().offset());
                    }
                }
            });
        }

    }

    /**
     * 订阅指定的分区
     */
    public void receivedFixedPartition(){

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "origin_group_id_001");
        props.put("enable.auto.commit", false);
        props.put("auto.commit.interval.ms", 1000);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("max.poll.records", 10);
        props.put("max.poll.interval.ms", 10000);
        props.put("heartbeatInterval", 1000);
        props.put("session.timeout.ms", 10000);
        props.put("isolation.level","read_committed");

        KafkaConsumer consumer = new KafkaConsumer(props);

        /**
         * Note that it is not possible to use both manual partition assignment with {@link #assign(Collection)}
         * and group assignment with {@link #subscribe(Collection, ConsumerRebalanceListener)}.
         */
        // consumer.subscribe(Arrays.asList("my-topic"));

        consumer.assign(Arrays.asList(new TopicPartition("my-topic", 0)));

        while (true) {

            /**
             * timeout: The maximum time to block (must not be greater than {@link Long#MAX_VALUE} milliseconds)
             * Set Duration.ofSeconds(10) in this example.
             */
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(5));

            /**
             * process records
             */
            for(ConsumerRecord record : records){
                log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
            }

            /**
             * commit offset manually
             *
             手动提交偏移量。这样我们可以准确控制消息是成功消费的。
             这种方式就是所谓的“至少一次”保证，在故障情况下，可以重复。
             使用手动偏移控制的优点是，您可以直接控制记录何时被视为“已消耗”。
             */
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception != null){
                        log.error("manually commit error, {} ", exception.getMessage());
                    }

                    for(Map.Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()){
                        log.info("topic_partition: {}, offset: {}", entry.getKey().toString(), entry.getValue().offset());
                    }
                }
            });
        }

    }

}
