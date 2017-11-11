package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.see.common.core.utils.PageBean;
import com.see.service.Application;
import com.see.service.acp.core.mogodb.dao.RansactionMessageDao;
import com.see.service.acp.core.mogodb.entity.RansactionMessageEntity;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMongoDB {
	@Autowired
	private RansactionMessageDao ransactionMessageDao;

	@Test
	public void saveRansactionMessage() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println("第1个：" + uuid);
		RansactionMessageEntity ransactionMessageEntity = new RansactionMessageEntity();
		ransactionMessageEntity.setId(uuid);
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println("第2个：" + uuid);
		ransactionMessageEntity.setMessageBody("你好");
		ransactionMessageEntity.setMessageId(uuid);
		ransactionMessageEntity.setCreateTime(new Date());
		ransactionMessageEntity.setEditTime(new Date());
		ransactionMessageEntity.setVersion(1);
		ransactionMessageEntity.setStatus("准备中");
		ransactionMessageEntity.setConsumerQueue("test");
		ransactionMessageEntity.setAreadlyDead("no");
		ransactionMessageDao.saveRansactionMessage(ransactionMessageEntity);
		RansactionMessageEntity entity = null;// ransactionMessageDao.findRansactionMessageByMessageId(uuid);

		System.out.println("第x个：" + JSONObject.toJSONString(entity));
		PageBean page = new PageBean();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("areadlyDead", "no");
		//page.setParameter(parameter);
		page = ransactionMessageDao.getPageRansactionMessage(page);
		System.out.println("第oo个：" + JSONObject.toJSONString(page));
	}

}
