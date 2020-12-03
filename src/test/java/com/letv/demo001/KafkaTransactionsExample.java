package com.letv.demo001;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

public class KafkaTransactionsExample {

    public static void main(String args[]) {
        /*
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);


        // Note that the ‘transactional.id’ configuration _must_ be specified in the
        // producer config in order to use transactions.
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerConfig);

        // We need to initialize transactions once per producer instance. To use transactions,
        // it is assumed that the application id is specified in the config with the key
        // transactional.id.
        //
        // This method will recover or abort transactions initiated by previous instances of a
        // producer with the same app id. Any other transactional messages will report an error
        // if initialization was not performed.
        //
        // The response indicates success or failure. Some failures are irrecoverable and will
        // require a new producer  instance. See the documentation for TransactionMetadata for a
        // list of error codes.
        producer.initTransactions();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(CONSUMER_POLL_TIMEOUT);
            if (!records.isEmpty()) {
                // Start a new transaction. This will begin the process of batching the consumed
                // records as well
                // as an records produced as a result of processing the input records.
                //
                // We need to check the response to make sure that this producer is able to initiate
                // a new transaction.
                producer.beginTransaction();

                // Process the input records and send them to the output topic(s).
                List<ProducerRecord<String, String>> outputRecords = processRecords(records);
                for (ProducerRecord<String, String> outputRecord : outputRecords) {
                    producer.send(outputRecord);
                }

                // To ensure that the consumed and produced messages are batched, we need to commit
                // the offsets through
                // the producer and not the consumer.
                //
                // If this returns an error, we should abort the transaction.

                sendOffsetsResult = producer.sendOffsetsToTransaction(getUncommittedOffsets());

                // Now that we have consumed, processed, and produced a batch of messages, let's
                // commit the results.
                // If this does not report success, then the transaction will be rolled back.
                producer.commitTransaction();
            }
        }
        */
    }
}