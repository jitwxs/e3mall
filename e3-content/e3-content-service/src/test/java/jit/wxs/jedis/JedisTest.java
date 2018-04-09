package jit.wxs.jedis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jitwxs
 * @date 2018/4/9 13:44
 */
public class JedisTest {
    @Test
    public void testJedis() {
        // 1.获得连接对象。参数为redis所在的服务器地址及端口号
        Jedis jedis = new Jedis("192.168.30.155", 7001);

        // 2.获得数据
        String age = jedis.get("age");
        System.out.println(age);

        jedis.close();

    }

    @Test
    public void testJedisPool() {
        //1. 创建Jedis连接池配置（包含许多配置，这里只配置了3个）
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 设置最小和最大闲置个数
        poolConfig.setMinIdle(5);
        poolConfig.setMaxIdle(10);
        // 设置连接池最大个数
        poolConfig.setMaxTotal(30);

        //2. 创建Jedis连接池
        JedisPool pool = new JedisPool(poolConfig,"192.168.30.155", 7001);

        //3. 从连接池中获取Jedis对象
        Jedis jedis = pool.getResource();

        //4.操纵数据
        jedis.set("sex", "male");
        System.out.println(jedis.get("sex"));

        //5.关闭资源
        jedis.close();
        pool.close();
    }

    @Test
    public void testJedisCluster() throws IOException {
        // 1.创建一个JedisCluster对象。第一个参数nodes是一个set类型，set中包含若干个HostAndPort对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.30.155", 7001));
        nodes.add(new HostAndPort("192.168.30.155", 7002));
        nodes.add(new HostAndPort("192.168.30.155", 7003));
        nodes.add(new HostAndPort("192.168.30.155", 7004));
        nodes.add(new HostAndPort("192.168.30.155", 7005));
        nodes.add(new HostAndPort("192.168.30.155", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        // 2.直接使用JedisCluster对象操作redis，单例存在即可。
        cluster.set("test", "123");
        System.out.println(cluster.get("test"));
        // 3.关闭JedisCluster对象。
        cluster.close();
    }
}
