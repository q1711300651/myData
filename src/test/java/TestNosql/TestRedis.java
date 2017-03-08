package TestNosql;

/**
 * Created by ww on 16/10/22.
 */

import com.ww.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**applicationContext
 * 单元测试
 * @author marker
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
public class TestRedis {


 @Autowired
 private RedisTemplate redis;


    @org.junit.Test
    public void test(){
        User c = new User();
        c.setPassword("wu");
        c.setUserName("wei");
        Jedis jedis=new Jedis("localhost");
        jedis.set("55","555555");
        jedis.set("553","5555553");
        System.out.println(redis.toString()+ redis.keys(""));
        redis.getConnectionFactory().getConnection().set("11".getBytes(),"22".getBytes());
        RedisConnection connection = redis.getConnectionFactory().getConnection();
        redis.delete("11");
    }
}
