package net.javaguides.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

//    inject kafkatemplate from spring kafka library, use this template to send message to broker
    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    method will read a real-time wikimedia stream data
    public void sendMessage() throws InterruptedException {
        //topic name，必须对应我们创建topic时声明的名字
        String topic = "wikimedia_recentchange";

        // read real time stream data: use event source
        EventHandler eventHandler = new WikimediaCHangesHandler(kafkaTemplate, topic);
        //define the event source url
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        //create an object from the eventsource builder
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(1);
    }
}
