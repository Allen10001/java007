package com.letv.demo001.kafka.origin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description // TODO: 2020-09-18 发送相关的事务API
 * @Author Allen
 * @Date 2020-09-18 16:55
 **/
@Component
@Slf4j
public class OriginSender {

    public void send(){

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 5);
        /**
         * 链接：https://www.orchome.com/303
         * 默认缓冲可立即发送，即便缓冲空间还没有满，但是，如果你想减少请求的数量，可以设置linger.ms大于0。这将指示生产者发送请求之前等待一段时间，希望更多的消息填补到未满的批中。
         * 这类似于TCP的算法，例如上面的代码段，可能100条消息在一个请求发送，因为我们设置了linger(逗留)时间为1毫秒，然后，如果我们没有填满缓冲区，
         * 这个设置将增加1毫秒的延迟请求以等待更多的消息。需要注意的是，在高负载下，相近的时间一般也会组成批，即使是 linger.ms=0。在不处于高负载的情况下，
         * 如果设置比0大，以少量的延迟代价换取更少的，更有效的请求。
         */
        props.put("batch.size", 3);
        props.put("linger.ms", 5);
        props.put("buffer.memory", 2048000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.idempotence",true);
        props.put("transaction.timeout.ms",10000);

        /**
         * 为了跨多个生产者会话实现幂等, 需要提供一个在应用层面可以稳定的跨多个会话的 transactionalId. transactionalId由用户提供.
         *
         * 有transactionalId后, Kafka可以保证:
         *
         * 1. 一个给定的transactionalId只有一个活跃的producer. 如果有新的使用同一个transactionalId的producer实例上线, 旧的实例会被隔离.
         * 2. 跨应用会话的事务恢复, 当一个应用实例死掉后, broker 会结束(取消或者提交)未完成的事务以保护新上线的实例, 在恢复工作之前将新实例置于干净的状态.
         */
        props.put("transactional.id","origin_transaction_id_001");

        /**
         * 为了实现幂等生产者语义, 引入了 producer id 的概念, PID. 每个producer在初始化的时候会被分配一个唯一PID.
         * PID的分配对用户来说是完全透明的, 且没有被客户端暴露.
         */
        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        producer.initTransactions();

        /**
         * 启动事务 — beginTransaction() API
         * 新的KafkaProducer有一个beginTransaction()方法用来发出开始事务的信号.
         * 生产者记录指示交易已经开始的本地状态, 但是在发送第一条记录之前, 在协调器看来事务还没有开始.
         * 调用该方法后，Producer 本地会记录已经开启了事务，但 Transaction Coordinator
         * 只有在 Producer 发送第一条消息后才认为事务已经开启。
         */
        producer.beginTransaction();
        for(int i = 0; i< 18; i++){
            ProducerRecord producerRecord = new ProducerRecord<String, String>("my-topic", "key-"+i,"value-"+i);
            producer.send(producerRecord);
            log.info("send topic:{} seccess, i={}", producerRecord.topic(), i);
        }

        producer.commitTransaction();

       /*
        //**
         * send 是异步的，并且一旦消息被保存在等待发送的消息缓存中，此方法就立即返回。这样并行发送多条消息而不阻塞去等待每一条消息的响应。
         * 发送的结果是一个 RecordMetadata，它指定了消息发送的分区，分配的offset和消息的时间戳。
         *//*
        for(int j=0; j< 18; j++){
            producer.send(new ProducerRecord<String, String>("my-topic", "key-j-" + j, "value-j-" + j), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception != null){
                        log.error("send error, {}", exception.getMessage());
                    }
                    log.info("the offset of the record we just send is: {}", metadata.offset());
                }
            });
        }*/

        producer.close();
    }
}
