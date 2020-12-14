package com.tv.kafka;

import ch.qos.logback.core.util.FileUtil;
import com.google.common.base.Charsets;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.eac.EACIOException;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.xerial.snappy.Snappy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

@Component
@Slf4j
public class OldVersionOriginReceiver {

    public void receiveWithAutoCommit() {

        String topic = "rec_log";
        Properties props = new Properties();

        /**
         * 集群是通过配置bootstrap.servers指定一个或多个broker。不用指定全部的broker，它将自动发现集群中的其余的borker（最好指定多个，万一有服务器故障）。
         */
        props.put("bootstrap.servers", "rec_log 对应的serverip");
        props.put("group.id", "origin_group_id_004");
        props.put("zookeeper.connect", "rec_log 对应的zk");
        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("zookeeper.connection.timeout.ms", "10000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto-offset-reset", "largest");

        ConsumerConfig kafkaConsumerConfig = new ConsumerConfig(props);

        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(kafkaConsumerConfig);
        Map<String, Integer> topicMap = new java.util.HashMap<>();
        topicMap.put(topic, 1);

        /*demo1 和 demo2 可以实现相同的效果 */
        /**
         * demo1：
         */
        /*Map<String, List<KafkaStream<String, String>>> message = consumerConnector.createMessageStreams(topicMap,  new StringDecoder(null), new StringDecoder(null));
        KafkaStream<String, String> kafkaStream = message.get(topic).get(0);
        ConsumerIterator<String, String> iterator = kafkaStream.iterator();

        while(iterator.hasNext()){
            MessageAndMetadata<String, String> metadata = iterator.next();

            // System.out.println( "origin key: "+byteToHexString(metadata.key())+" message: "+byteToHexString(metadata.message()));

            try {

                System.out.println( "key: "+metadata.key()+ "\n"+
                        " message: " + metadata.message());

                *//**
                 * gzip压缩的数据开头两个字节分别是1f 和 8b ，GZIPInputStream 也是据此判断字节流是不是 gzip 压缩的数据流。 0x1f8b
                 *//*
                *//*
                 System.out.println( "key: "+new String(unGzip(metadata.key()) , Charsets.UTF_8)+ "\n"+
                        " message: " + new String(unGzip(metadata.message()), Charsets.UTF_8));
                        *//*

//                System.out.println( "key: "+new String(Snappy.uncompress(metadata.key()) , Charsets.UTF_8)+ "\n"+
//                        " message: " + new String(Snappy.uncompress(metadata.message()), Charsets.UTF_8));
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
                //log.error(e.getMessage());
            }
        }*/
        /* End of Demo1 */

        /**
         * demo2：
         */
        Map<String, List<KafkaStream<byte[], byte[]>>> message = consumerConnector.createMessageStreams(topicMap);
        KafkaStream<byte[], byte[]> kafkaStream = message.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        while(iterator.hasNext()){
            MessageAndMetadata<byte[], byte[]> metadata = iterator.next();

            // System.out.println( "origin key: "+byteToHexString(metadata.key())+" message: "+byteToHexString(metadata.message()));

            try {

                 System.out.println( "key: "+new String(metadata.key() , "UTF8")+ "\n"+
                        " message: " + new String(metadata.message(), "UTF8"));

                /*
                 * gzip压缩的数据开头两个字节分别是1f 和 8b ，GZIPInputStream 也是据此判断字节流是不是 gzip 压缩的数据流。 0x1f8b
                 */
                /*
                 System.out.println( "key: "+new String(unGzip(metadata.key()) , Charsets.UTF_8)+ "\n"+
                        " message: " + new String(unGzip(metadata.message()), Charsets.UTF_8));
                      */

//                System.out.println( "key: "+new String(Snappy.uncompress(metadata.key()) , Charsets.UTF_8)+ "\n"+
//                        " message: " + new String(Snappy.uncompress(metadata.message()), Charsets.UTF_8));
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
                //log.error(e.getMessage());
            }
        }
        /* End of Demo2 */

    }

    public static void main(String[] args){
        OldVersionOriginReceiver receiver = new OldVersionOriginReceiver();
        receiver.receiveWithAutoCommit();
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2){
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public byte[] unGzip(byte[] content) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPInputStream gis=new GZIPInputStream(new ByteArrayInputStream(content));
        byte[] buffer=new byte[1024];
        int n;
        while((n=gis.read(buffer))!=-1){
            baos.write(buffer, 0, n);
        }

        return baos.toByteArray();
    }

}
