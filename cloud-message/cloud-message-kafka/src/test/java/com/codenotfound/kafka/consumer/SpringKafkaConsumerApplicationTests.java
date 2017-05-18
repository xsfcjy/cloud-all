package com.codenotfound.kafka.consumer;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaConsumerApplicationTests {


  @Autowired
  private Receiver receiver;

  @Test
  public void testReceive() throws Exception {

    receiver.getLatch().await(400000000, TimeUnit.MILLISECONDS);
//    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	Thread.sleep(100000000);
  }
}
