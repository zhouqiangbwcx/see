package com.see.common.core.utils;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.see.common.core.cahe.JedisClient;

public class UUIDBuild {

	private final static Logger logger = Logger.getLogger(UUIDBuild.class);

	private String sep = "";

	private static final int IP;

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	/**
	 * 订单号生成计数器
	 */
	private static long orderNumCount = 0L;
	/**
	 * 每毫秒生成订单号数量最大值
	 */
	private static int maxPerMSECSize = 1000;

	private static UUIDBuild uuidgen = new UUIDBuild();

	static {

		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	public static UUIDBuild getInstance() {
		return uuidgen;
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	protected int getJVM() {
		return JVM;
	}

	protected synchronized short getCount() {
		if (counter < 0) {
			counter = 0;
		}
		return counter++;
	}

	protected int getIP() {
		return IP;
	}

	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	public String generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}

	/**
	 * 获取序号 全球唯一的
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 
	 * @Title: getUUID
	 * @Description: TODO 获取序号 有序的
	 * @param name
	 *            序号开头 name=CD 返回cb20170619132029855381 不传为无序uuid
	 * @param jedisClient
	 *            必须
	 * @return
	 * @return: String
	 */
	public static String getUUID(String name, JedisClient jedisClient) {
		if (StringUtils.isNullOrEmpty(name)) {
			return getUUID();
		}
		return orderly(name, jedisClient);
	}

	/**
	 * 生成非重复订单号，理论上限1毫秒1000个，可扩展
	 * 
	 * @param tname
	 *            测试用
	 */
	private static String orderly(String orderName, JedisClient jedisClient) {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (orderName) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (null != jedisClient) {
					try {
						orderNumCount = jedisClient.incr(orderName);
						if (orderNumCount >= maxPerMSECSize) {
							jedisClient.del(orderName);
							orderNumCount = jedisClient.incr(orderName);
						}
					} catch (Exception e) {
						orderNumCount = (int) (1 + Math.random() * 1000);
					}

				} else {
					orderNumCount = (int) (1 + Math.random() * 1000);
				}
				// 组装订单号
				String countStr = maxPerMSECSize + orderNumCount + "";
				finOrderNum = orderName + "" + nowLong + countStr;
				logger.info("生成订单号:" + finOrderNum);
				return finOrderNum;
			}
		} catch (Exception e) {
			logger.error("生存订单异常：", e);
		}
		return "";
	}

}
