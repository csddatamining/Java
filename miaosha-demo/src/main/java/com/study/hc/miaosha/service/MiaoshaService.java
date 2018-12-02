package com.study.hc.miaosha.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

@Component
public class MiaoshaService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	DatabaseService databaseService;

	@PostConstruct
	public void init() {
		// 初始化。 实际上，会通过异步加载，通过任务调度
		for (int i = 0; i < 100; i++) {
			// redis list数据结构，实现令牌桶效果
			stringRedisTemplate.opsForList().leftPush("token_list", String.valueOf(i));
		}
		System.out.println("初始化完毕");
	}

	/**
	 * 秒杀具体实现
	 * 
	 * @param goodsCode 商品编码
	 * @param userId    用户ID
	 * @return
	 */
	public boolean miaosha(String goodsCode, final String userId) {

		// 频率限制 8W/S -> 4W/S
		Boolean value = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> {
			// SET EX NX 组合命令
			boolean isSuccessful = connection.set(userId.getBytes(), "xx".getBytes(), Expiration.milliseconds(10000L),
					SetOption.SET_IF_ABSENT);
			return isSuccessful;
		});

		if (!value) {
			System.out.println("您操作的频率太高了");
			return false;
		}

		// 令牌池
		String token = stringRedisTemplate.opsForList().rightPop("token_list");
		if (token == null || "".equals(token)) {
			System.out.println("没抢到token: " + userId);
			return false;
		}

		boolean result = databaseService.buy(goodsCode, userId);
		System.out.println("秒杀结果:" + result);
		return result;
	}
}
