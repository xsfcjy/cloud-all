package com.codenotfound.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaProductApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void testReceive() throws Exception {
		for (int i = 0; i < 100; i++) {
//			if(i%10000==0){
//				Thread.sleep(10000);
//			}
			sender.send("helloworld2.t", "Hello Spring Kafka!测试" + i);
		}
	}
}
