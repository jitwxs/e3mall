package jit.wxs.jedis;

import jit.wxs.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jitwxs
 * @date 2018/4/9 15:33
 */
public class JedisClientTest {
    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient = ac.getBean(JedisClient.class);

        jedisClient.set("author", "jitwxs");
        String result = jedisClient.get("author");
        System.out.println(result);
    }
}