package com.letv.demo001;

import com.letv.demo001.kafka.origin.OriginSender;
import com.letv.demo001.kafka.template.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo001Application implements CommandLineRunner {

    @Autowired
    private KafkaSender sender;
    @Autowired
    private OriginSender originSender;

    public static void main(String[] args) {
        SpringApplication.run(Demo001Application.class, args);
    }

    @Override
    public void run(String... args){
        for(int i=0;i<10000;i++){
            sender.sendWords();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

  /*  @Override
    public void run(String... args){
        originSender.send();
    }*/

}
