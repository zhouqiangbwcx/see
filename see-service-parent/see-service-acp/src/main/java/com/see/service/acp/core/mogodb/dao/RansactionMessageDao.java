package com.see.service.acp.core.mogodb.dao;

import java.util.List;

import com.see.common.core.utils.PageBean;
import com.see.service.acp.core.mogodb.entity.RansactionMessageEntity;

public interface RansactionMessageDao {
	/**
	 * 创建对象
	 * 
	 * @param ransactionMessageEntity
	 */
	public void saveRansactionMessage(RansactionMessageEntity ransactionMessageEntity);

	/**
	 * 更加id查询
	 * 
	 * @param id
	 * @return
	 */
	public RansactionMessageEntity findRansactionMessageById(String id);

	/**
	 * 根据消息id查询
	 * 
	 * @param messageId
	 * @return
	 */
	public List<RansactionMessageEntity> findRansactionMessageByMessageId(String messageId);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void deleteRansactionMessageById(String id);

	/**
	 * 根据消息id删除
	 * 
	 * @param messageId
	 */
	public void deleteRansactionMessageByMessageId(String messageId);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public PageBean getPageRansactionMessage(PageBean page);

}
