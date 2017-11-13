package com.see.service.acp.core.mogodb.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.PageParam;
import com.see.common.core.utils.StringUtils;
import com.see.service.acp.core.mogodb.dao.RpTransactionMessageDao;
import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;

@Component
public class RpTransactionMessageImpl implements RpTransactionMessageDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveRpTransactionMessage(RpTransactionMessage rpTransactionMessage) {
		rpTransactionMessage.setVersion(1);
		rpTransactionMessage.setCreateTime(new Date());
		rpTransactionMessage.setEditTime(new Date());
		mongoTemplate.save(rpTransactionMessage);
	}

	@Override
	public RpTransactionMessage findRpTransactionMessageMessageById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		RpTransactionMessage rpTransactionMessage = mongoTemplate.findOne(query, RpTransactionMessage.class);
		return rpTransactionMessage;
	}

	@Override
	public RpTransactionMessage findRpTransactionMessageByMessageId(String messageId) {
		Query query = new Query(Criteria.where("messageId").is(messageId));
		RpTransactionMessage rpTransactionMessage = mongoTemplate.findOne(query, RpTransactionMessage.class);
		return rpTransactionMessage;
	}

	@Override
	public void editRpTransactionMessageById(RpTransactionMessage rpTransactionMessage) {
		Query query = new Query(Criteria.where("messageId").is(rpTransactionMessage.getMessageId()));
		Update update = new Update();

		if (StringUtils.isNoNullOrEmpty(rpTransactionMessage.getStatus())) {
			update.update("status", rpTransactionMessage.getStatus());
		}
		if (StringUtils.isNoNullOrEmpty(rpTransactionMessage.getAreadlyDead())) {
			update.update("areadlyDead", rpTransactionMessage.getAreadlyDead());
		}

		if (StringUtils.isNoNullOrEmpty(rpTransactionMessage.getAreadlyDead())) {
			update.update("areadlyDead", rpTransactionMessage.getAreadlyDead());
		}
		if (StringUtils.isNoNullOrEmpty(rpTransactionMessage.getMessageSendTimes())) {
			update.inc("messageSendTimes", 1);
		}
		update.inc("version", 1);
		update.update("editTime", new Date());
		mongoTemplate.updateFirst(query, update, RpTransactionMessage.class);
	}

	@Override
	public void deleteRpTransactionMessageById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, RpTransactionMessage.class);
	}

	@Override
	public void deleteRpTransactionMessageByMessageId(String messageId) {
		Query query = new Query(Criteria.where("messageId").is(messageId));
		mongoTemplate.remove(query, RpTransactionMessage.class);
	}

	@Override
	public PageBean getPageRpTransactionMessage(PageParam pageParam, Map<String, Object> paramMap) {
		Criteria criteria = new Criteria();

		// 消费队列
		if (StringUtils.isNoNullOrEmpty(paramMap.get("consumerQueue"))) {
			criteria.andOperator(Criteria.where("consumerQueue").is(paramMap.get("consumerQueue")));
		}
		// 是否死亡
		if (StringUtils.isNoNullOrEmpty(paramMap.get("areadlyDead"))) {
			criteria.andOperator(Criteria.where("areadlyDead").is(paramMap.get("areadlyDead")));
		}
		// 状态
		if (StringUtils.isNoNullOrEmpty(paramMap.get("status"))) {
			criteria.andOperator(Criteria.where("status").is(paramMap.get("status")));
		}

		// 下次发送时间
		if (StringUtils.isNoNullOrEmpty(paramMap.get("sendTime"))) {
			criteria.andOperator(Criteria.where("sendTime").lt(paramMap.get("sendTime")));
		}

		if (StringUtils.isNoNullOrEmpty(paramMap.get("remark"))) {
			criteria.andOperator(new Criteria("remark").regex(".*?" + paramMap.get("remark") + ".*"));
		}
		Query query = new Query(criteria);
		query.with(new Sort(Direction.ASC, "createTime"));// 时间顺序
		long count = this.mongoTemplate.count(query, RpTransactionMessage.class);

		int start = (pageParam.getPageNum() - 1) * pageParam.getNumPerPage();
		int limit = pageParam.getNumPerPage();

		query.limit(limit);// 分页条数设置
		query.skip(start);// 跳过前start条数据

		List<RpTransactionMessage> list = this.mongoTemplate.find(query, RpTransactionMessage.class);

		PageBean pageBean = new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), new Long(count).intValue(),
				list, paramMap);
		return pageBean;
	}

}
