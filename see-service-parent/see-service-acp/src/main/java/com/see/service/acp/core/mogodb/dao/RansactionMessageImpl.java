package com.see.service.acp.core.mogodb.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Component;

import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.StringUtils;
import com.see.service.acp.core.mogodb.entity.RansactionMessageEntity;

@Component
public class RansactionMessageImpl implements RansactionMessageDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveRansactionMessage(RansactionMessageEntity ransactionMessageEntity) {
		ransactionMessageEntity.setVersion(1);
		ransactionMessageEntity.setCreateTime(new Date());
		ransactionMessageEntity.setEditTime(new Date());
		mongoTemplate.save(ransactionMessageEntity);
	}

	@Override
	public RansactionMessageEntity findRansactionMessageById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		RansactionMessageEntity ransactionMessageEntity = mongoTemplate.findOne(query, RansactionMessageEntity.class);
		return ransactionMessageEntity;
	}

	@Override
	public List<RansactionMessageEntity> findRansactionMessageByMessageId(String messageId) {
		Query query = new Query(Criteria.where("messageId").is(messageId));
		List<RansactionMessageEntity> list = mongoTemplate.find(query, RansactionMessageEntity.class);
		return list;
	}

	@Override
	public void deleteRansactionMessageById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, RansactionMessageEntity.class);
	}

	@Override
	public void deleteRansactionMessageByMessageId(String messageId) {
		Query query = new Query(Criteria.where("messageId").is(messageId));
		mongoTemplate.remove(query, RansactionMessageEntity.class);
	}

	@Override
	public PageBean getPageRansactionMessage(PageBean page) {
		Criteria criteria = new Criteria();
		Map<String, Object> map = page.getCountResultMap();
		// 消费队列
		if (StringUtils.isNoNullOrEmpty(map.get("consumerQueue"))) {
			criteria.andOperator(Criteria.where("consumerQueue").is(map.get("consumerQueue")));
		}
		// 是否死亡
		if (StringUtils.isNoNullOrEmpty(map.get("areadlyDead"))) {
			criteria.andOperator(Criteria.where("areadlyDead").is(map.get("areadlyDead")));
		}
		// 状态
		if (StringUtils.isNoNullOrEmpty(map.get("status"))) {
			criteria.andOperator(Criteria.where("status").is(map.get("status")));
		}

		// 下次发送时间
		if (StringUtils.isNoNullOrEmpty(map.get("sendTime"))) {
			criteria.andOperator(Criteria.where("sendTime").lt(map.get("sendTime")));
		}

		if (StringUtils.isNoNullOrEmpty(map.get("remark"))) {
			criteria.andOperator(new Criteria("remark").regex(".*?" + map.get("remark") + ".*"));
		}

		int start = page.getBeginPageIndex();
		int limit = page.getEndPageIndex();

		Query query = new Query(criteria);
		query.limit(limit);// 分页条数设置
		query.skip(start);// 跳过前start条数据

		query.with(new Sort(Direction.ASC, "createTime"));// 时间顺序

		List<RansactionMessageEntity> list = this.mongoTemplate.find(query, RansactionMessageEntity.class);
		long count = this.mongoTemplate.count(query, RansactionMessageEntity.class);
		page.setRecordList(list);;
		page.setTotalCount(Integer.valueOf(count + ""));
		return page;
	}

	/*
	 * public long getPagCount(List<Condition> conditions) { Query query = new
	 * Query(); if (conditions != null && conditions.size() > 0) { for
	 * (Condition condition : conditions) {
	 * query.addCriteria(Criteria.where(condition.getKey()).is(condition.
	 * getValue())); } } return count(query, PageInfo.class); }
	 */

}
