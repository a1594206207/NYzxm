package com.nzxmmp.nzxm.entity;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.nzxmmp.nzxm.Config.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Slf4j
public class Rediscache implements Cache {
    private String Id;
    private  final ReadWriteLock readWriteLock=new ReentrantReadWriteLock(true);
    //从sprinUtil里自动注入
    private  RedisTemplate<String,Object> redisTemplate;
    //设置redis缓存的时间范围
    private  int MAX_TIME=120;
    private  int MIN_TIME=60;
    private Random random=new Random();


    public Rediscache(String Id){
        this.Id=Id;
    }

    @Override
    public String getId() {
        return this.Id;
    }

    @Override
    public void putObject(Object key, Object value) {

        if (redisTemplate==null){
            this.redisTemplate= (RedisTemplate<String,Object>)SpringUtil.getBean("redisTemplate");
        }
        int i = random.nextInt(MAX_TIME-MIN_TIME)+MIN_TIME;
        redisTemplate.opsForValue().set(key.toString(),value,i, TimeUnit.MINUTES);
    }

    @Override
    public Object getObject(Object o) {
        if (redisTemplate==null){
            this.redisTemplate= (RedisTemplate<String,Object>)SpringUtil.getBean("redisTemplate");
        }
        if(o!=null){
            Object o1 = redisTemplate.opsForValue().get(o.toString());
            return o1;
        }


        return null;
    }

    @Override
    public Object removeObject(Object o) {
        if (redisTemplate==null){
            this.redisTemplate= (RedisTemplate<String,Object>)SpringUtil.getBean("redisTemplate");
        }
        if (o!=null){
             redisTemplate.delete(o.toString());
        }
        return null;
    }

    @Override
    public void clear() {
       log.debug("清空缓存");
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
        }
        Set<String> keys = redisTemplate.keys("*:" +  this.Id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }

    }

    @Override
    public int getSize() {
        if (redisTemplate == null) {
            //由于启动期间注入失败，只能运行期间注入，这段代码可以删除
            redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
        }
        Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();

    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
