package com.see.service.acp.core.mogodb.dao;

import java.util.Map;

import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.PageParam;
import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;

public interface RpTransactionMessageDao {
	/**
	 * 创建对象
	 * 
	 * @param RpTransactionMessageEntity
	 */
	public void saveRpTransactionMessage(RpTransactionMessage RpTransactionMessage);

	/**
	 * 更加id查询
	 * 
	 * @param id
	 * @return
	 */
	public RpTransactionMessage findRpTransactionMessageMessageById(String id);

	/**
	 * 根据消息id查询
	 * 
	 * @param messageId
	 * @return
	 */
	public RpTransactionMessage findRpTransactionMessageByMessageId(String messageId);

	/**
	 * 根据消息id修改
	 * @param rpTransactionMessage
	 */
	public void editRpTransactionMessageById(RpTransactionMessage rpTransactionMessage);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void deleteRpTransactionMessageById(String id);

	/**
	 * 根据消息id删除
	 * 
	 * @param messageId
	 */
	public void deleteRpTransactionMessageByMessageId(String messageId);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public PageBean getPageRpTransactionMessage(PageParam pageParam, Map<String, Object> paramMap);

}
