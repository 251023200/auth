package com.sky.auth.controller;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private static final Logger logger = Logger.getLogger(RedisService.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 缓存有效期（单位：秒)
	 */
//	@Value("${cssc.redis.expireTime.seconds}")
	private int expireTime=1800;

	public static final int forever = -1;

	public void setValue(String key, String value) {
		logger.info("set key:" + key + ",value:" + value + ",expire:"
				+ expireTime);
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 *            单位秒seconds
	 */
	public void setValue(String key, String value, int expire) {
		logger.info("set key:" + key + ",value:" + value + ",expire:" + expire);
		redisTemplate.opsForValue().set(key, value);
		if (expire > 0) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	/**
	 * 取得缓存中得值，不改变过期时间
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		logger.info("get key:" + key);
		String value = redisTemplate.opsForValue().get(key);
		return value;
	}

	/**
	 * 取得缓存中得值，重设默认过期时间
	 * 
	 * @param key
	 * @return
	 */
	public String getValueResetExpire(String key) {
		logger.info("get key:" + key);
		String value = redisTemplate.opsForValue().get(key);
		if (value != null) {
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
		return value;
	}

	/**
	 * 取得缓存中得值，设置指定的过期时间<br>
	 * 如果过期时间<=0的话，就不改变过期时间。
	 * 
	 * @param key
	 * @param expire
	 *            单位秒
	 * @return
	 */
	public String getValue(String key, int expire) {
		logger.info("get key:" + key);
		if (expire > 0) {
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
		return redisTemplate.opsForValue().get(key);
	}

	public void del(String key) {
		logger.info("delete key:" + key);
		redisTemplate.delete(key);
	}

//	@Autowired
//	private RedisTemplate<String, ?> redisTemplate;
//
//	public boolean set(final String key, final String value) {
//		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//			@Override
//			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//				connection.set(serializer.serialize(key), serializer.serialize(value));
//				return true;
//			}
//		});
//		return result;
//	}
//
//	public String get(final String key) {
//		String result = redisTemplate.execute(new RedisCallback<String>() {
//			@Override
//			public String doInRedis(RedisConnection connection) throws DataAccessException {
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//				byte[] value = connection.get(serializer.serialize(key));
//				return serializer.deserialize(value);
//			}
//		});
//		return result;
//	}
//
//	public boolean expire(final String key, long expire) {
//		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
//	}
//
//	public <T> boolean setList(String key, List<T> list) {
//		Gson g = new Gson();
//		String value = g.toJson(list);
//		return set(key, value);
//	}
//
//	public <T> List<T> getList(String key, Class<T> clz) {
//		String json = get(key);
//		if (json != null) {
//			List<T> list = JSONUtil.toList(json, clz);
//			return list;
//		}
//		return null;
//	}
//
//	public long lpush(final String key, Object obj) {
//		final String value = JSONUtil.toJson(obj);
//		long result = redisTemplate.execute(new RedisCallback<Long>() {
//			@Override
//			public Long doInRedis(RedisConnection connection) throws DataAccessException {
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//				long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
//				return count;
//			}
//		});
//		return result;
//	}
//
//	public long rpush(final String key, Object obj) {
//		final String value = JSONUtil.toJson(obj);
//		long result = redisTemplate.execute(new RedisCallback<Long>() {
//			@Override
//			public Long doInRedis(RedisConnection connection) throws DataAccessException {
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//				long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
//				return count;
//			}
//		});
//		return result;
//	}
//
//	public String lpop(final String key) {
//		String result = redisTemplate.execute(new RedisCallback<String>() {
//			@Override
//			public String doInRedis(RedisConnection connection) throws DataAccessException {
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//				byte[] res = connection.lPop(serializer.serialize(key));
//				return serializer.deserialize(res);
//			}
//		});
//		return result;
//	}

}