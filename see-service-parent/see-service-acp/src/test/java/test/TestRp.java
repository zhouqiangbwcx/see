package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.see.core.common.cahe.JedisClient;
import com.see.core.common.utils.UUIDBuild;
import com.see.service.Application;
import com.see.service.acp.dao.IRpTransactionMessageService;
import com.see.web.acp.dto.rpTransactionMessage.RpTransactionMessageDTO;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRp {
	@Autowired
	private IRpTransactionMessageService rpTransactionMessageService;

	@Autowired
	private JedisClient jedisClient;

	@Test
	public void savnRabbit() {
		RpTransactionMessageDTO rpTransactionMessageDTO = new RpTransactionMessageDTO();
		rpTransactionMessageDTO.setMessageId(UUIDBuild.getUUID("x", jedisClient));
		rpTransactionMessageDTO.setConsumerQueue("see_acp");
		rpTransactionMessageDTO.setMessageBody("xxxxxxxxxxxxxxx");
		System.out.println(rpTransactionMessageDTO.getMessageId());
		rpTransactionMessageService.saveMessageWaitingConfirm(rpTransactionMessageDTO);
	}

	@Test
	public void getRabbit() {
		RpTransactionMessageDTO rpTransactionMessageDTO = new RpTransactionMessageDTO();

		System.out.println(
				JSONObject.toJSONString(rpTransactionMessageService.getMessageByMessageId("x201711141658577861002")));
	}
	
	@Test
	public void sendRabbit() {
		RpTransactionMessageDTO rpTransactionMessageDTO = new RpTransactionMessageDTO();
		rpTransactionMessageService.confirmAndSendMessage("x201711141614403321001");
/*		System.out.println(
				JSONObject.toJSONString(rpTransactionMessageService.getMessageByMessageId("x201711141614403321001")));*/
	}
	
	@Test
	public void delRabbit() {
		RpTransactionMessageDTO rpTransactionMessageDTO = new RpTransactionMessageDTO();

		rpTransactionMessageService.deleteMessageByMessageId("x201711141614403321001");
	}
	
}
