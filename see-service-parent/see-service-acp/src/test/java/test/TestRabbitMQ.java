package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.see.service.Application;
import com.see.service.acp.core.mogodb.sender.HelloSender;


@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

	@Autowired
	private HelloSender helloSender;

	@Test
	public void testRabbit() {
		helloSender.send();
	}
}