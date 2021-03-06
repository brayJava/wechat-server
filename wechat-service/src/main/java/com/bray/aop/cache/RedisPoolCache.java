package com.bray.aop.cache;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @Author:wuzhiyuan
 * @Description: 处理redis数据
 * @Date: Created in 13:54 2018/8/14
 * @Modified By:
 */
@Component
@Slf4j
public class RedisPoolCache {

    @Autowired
    private JedisPool jedisPool;

    //过期时间设置为30天
    private final static int EXPIRE_TIME = 60*60*24*30;
    /**
     * 从redis获取数据
     * @param redisKey redis存储key值
     * @return
     */
    public Object getRedisValueByKey(String redisKey) {
        Jedis jedis = this.getJedis();
        byte[] bytes = jedis.get(redisKey.getBytes());
        if(bytes != null) {
            return SerializeUtil.reverseSerializeObj(bytes);
        }
        return bytes;
    }
    /**
     * 往redis存储数据
     * @param redisKey 存储key值
     * @param object   存储value值，为序列化对象
     */
    public String saveDataToRedis(String redisKey ,Object object) {
        Jedis jedis = this.getJedis();
        String code = jedis.set(redisKey.getBytes(), SerializeUtil.serializeObj(object));
//        String code = jedis.set(redisKey, JSONObject.toJSON(object).toString());
        //设置过期时间(为30天)
        jedis.expire(redisKey.getBytes(),EXPIRE_TIME);
        return code;
    }
    /**
     * 删除redis数据
     * @param redisKey
     * @return
     */
    public Long deleteDataOfRedis(String redisKey) {
        Jedis jedis = this.getJedis();
        Long del = jedis.del(redisKey);
        return del;
    }
    /**
     * 存储数据到redis list
     * @param redisKey
     * @param orderStr
     * @return
     */
    public Long lpushRedis(String redisKey,String orderStr) {
        Jedis jedis = this.getJedis();
        Long orderlist = jedis.lpush(redisKey, orderStr);
        return orderlist;
    }
    /**
     * 在redis取出数据
     * @param redisKey
     * @return
     */
    public List<String> lrangeRedis(String redisKey,int start,int end) {
        Jedis jedis = this.getJedis();
        List<String> lrange = jedis.lrange(redisKey, start, end);
        return lrange;
    }
    /**
     * 设置set集合
     * @param redisKey
     * @param setValue
     * @return
     */
    public long setRedis(String redisKey,String setValue) {
        Jedis jedis = this.getJedis();
        Long sadd = jedis.sadd(redisKey, setValue);
        return sadd;
    }
    /**
     * 获取set集合
     * @param redisKey
     * @return
     */
    public Set<String> smembersRedis(String redisKey) {
        Jedis jedis = this.getJedis();
        Set<String> smembers = jedis.smembers(redisKey);
        return smembers;
    }
    /**
     * 设置map集合
     * @return
     */
    public Long setMapRedis(String mapName,String redisKey,String setValue) {
        Jedis jedis = this.getJedis();
        Long hset = jedis.hset(mapName, redisKey, setValue);
        return hset;
    }
    /**
     * 获取map中的参数
     * @return
     */
    public String getMapRedis(String mapName,String redisKey) {
        Jedis jedis = this.getJedis();
        String hget = jedis.hget(mapName, redisKey);
        return hget;
    }
    /**
     * 更新redis数据
     * @param redisKey
     * @param object
     * @return
     */
    public String updateDataOfRedis(String redisKey,Object object) {
        Jedis jedis = this.getJedis();
        if(jedis.exists(redisKey.getBytes())) {
            //先删除redis的key值
            jedis.del(redisKey.getBytes());
        }
        log.info(">>>>>>>>>>key:{},value:{}",redisKey, JSONObject.toJSON(object));
        //更新对应数据
        String code = jedis.set(redisKey.getBytes(), SerializeUtil.serializeObj(object));
//        String code = jedis.set(redisKey, JSONObject.toJSON(object).toString());
        //设置过期时间(为30天)
        jedis.expire(redisKey.getBytes(),EXPIRE_TIME);
        return code;
    }

    /**
     * 获取jedis失败则释放jedis放入池中
     * @return
     */
    private Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if(jedis != null ) {
                //获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return jedis;
    }
}
