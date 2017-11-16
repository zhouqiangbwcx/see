package com.see.service.acp.core.redis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.see.common.core.cahe.JedisClient;

@Component(value="jedisClient")
public class JedisClientSingle implements JedisClient {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	// Key（键），简单的key-value操作

	/**
	 * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public long ttl(String key) {
		return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
	 */
	@Override
	public Set<String> keys(String pattern) {
		return stringRedisTemplate.keys(pattern);
	}

	/**
	 * 实现命令：DEL key，删除一个key
	 * 
	 * @param key
	 */
	@Override
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}

	/**
	 * 递增操作
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Long incr(String key) {
		return stringRedisTemplate.opsForValue().increment(key, 1);
	}

	/**
	 * 递减操作
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Long decr(String key) {
		return stringRedisTemplate.opsForValue().increment(key, -1);
	}

	// String（字符串）

	/**
	 * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            （以秒为单位）
	 */
	@Override
	public void set(String key, String value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 实现命令：GET key，返回 key所关联的字符串值。
	 * 
	 * @param key
	 * @return stringRedisTemplate.value
	 */
	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	
	
	// Hash（哈希表）

	/**
	 * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	@Override
	public void hset(String key, String field, Object value) {
		stringRedisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	@Override
	public String hget(String key, String field) {
		return (String) stringRedisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 * 
	 * @param key
	 * @param fields
	 */
	@Override
	public void hdel(String key, Object... fields) {
		stringRedisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Map<Object, Object> hgetall(String key) {
		return stringRedisTemplate.opsForHash().entries(key);
	}

	// List（列表）

	/**
	 * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
	 * 
	 * @param key
	 * @param value
	 * @return stringRedisTemplate.执行 LPUSH命令后，列表的长度。
	 */
	@Override
	public long lpush(String key, String value) {
		return stringRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 实现命令：LPOP key，移除并返回列表 key的头元素。
	 * 
	 * @param key
	 * @return stringRedisTemplate.列表key的头元素。
	 */
	@Override
	public String lpop(String key) {
		return stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
	 * 
	 * @param key
	 * @param value
	 * @return stringRedisTemplate.执行 LPUSH命令后，列表的长度。
	 */
	@Override
	public long rpush(String key, String value) {
		return stringRedisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 实现命令：RPOP key，移除并返回列表 key的尾元素。
	 * 
	 * @param key
	 * @return stringRedisTemplate.列表key的头元素。
	 */
	@Override
	public String rpop(String key) {
		return stringRedisTemplate.opsForList().rightPop(key);
	}

	// Set（集合）
	/**
	 * 实现命令：SADD key member，将一个 member元素加入到集合 key当中，已经存在于集合的 member元素将被忽略。
	 * 
	 * @param key
	 * @param member
	 */
	@Override
	public void sadd(String key, String member) {
		stringRedisTemplate.opsForSet().add(key, member);
	}

	/**
	 * 实现命令：SMEMBERS key，返回集合 key 中的所有成员。
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Set<String> smemebers(String key) {
		return stringRedisTemplate.opsForSet().members(key);
	}

	// SortedSet（有序集合）
	/**
	 * 实现命令：ZADD key score member，将一个 member元素及其 score值加入到有序集 key当中。
	 * 
	 * @param key
	 * @param score
	 * @param member
	 */
	@Override
	public void zadd(String key, double score, String member) {
		stringRedisTemplate.opsForZSet().add(key, member, score);
	}

	/**
	 * 实现命令：ZRANGE key start stop，返回有序集 key中，指定区间内的成员。
	 * 
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 */
	@Override
	public Set<String> zrange(String key, double start, double stop) {
		return stringRedisTemplate.opsForZSet().rangeByScore(key, start, stop);
	}

}
