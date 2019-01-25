package com.insigma.redis;


import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
/**
 * @author Administrator
 * redismanager��Ҫ�������û��ṩһ������걸�ģ�ͨ��jedis��jar��������redis�ڴ����ݿ�ĸ��ַ���
 */
public class RedisManager {
	
	Log log=LogFactory.getLog(RedisManager.class);
 
    //���Զ���������redis.properties�ļ��У�����ͨ��spring��ע�ⷽʽ��ֱ��ʹ��
    @Value("${redis.ip}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.auth}")
    private String auth;
    
    //jedis.pool����
    @Value("${jedis.pool.maxActive}")
    private int maxActive;
    @Value("${jedis.pool.maxIdle}")
    private int maxIdle;
    @Value("${jedis.pool.maxWait}")
    private long maxWait;
    @Value("${jedis.pool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${jedis.pool.testOnReturn}")
    private boolean testOnReturn;
    
 
    // ����Ϊ0�Ļ�������Զ���������
    private int expire = 0;
 
    // ����һ������أ����е�redisManager��ͬʹ�á�
    private static JedisPool jedisPool = null;
 
    public RedisManager() {
    }
 
    /**
     * 
     * ��ʼ������
     * 
     * */
    public void init() {
        if (null == host || 0 == port) {
        	log.error("���ʼ��redis�����ļ�");
            throw new NullPointerException("�Ҳ���redis����");
        }
        if (jedisPool == null) {
        	JedisPoolConfig config=new JedisPoolConfig();
        	config.setMaxActive(maxActive);//redis pool�����������
        	config.setMaxIdle(maxIdle);//redis pool��󱣴������
        	config.setMaxWait(maxWait);//���ȴ�ʱ��
        	config.setTestOnBorrow(testOnBorrow);
        	config.setTestOnReturn(testOnReturn);
            jedisPool = new JedisPool(config, host, port,timeout,auth);
        }
    }
 
    /**
     * get value from redis
     * 
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }
    
    /**
     * 
     * @param key
     * @param list
     * @return
     */
    public Object get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
        	return SerializeUtil.deserialize(jedis.get(key.getBytes()));
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
 
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }
    
    /**
     * 
     * @param key
     * @param list
     * @return
     */
    public void set(String key, Object object) {
        Jedis jedis = jedisPool.getResource();
        try {
        	jedis.set(key.getBytes(),SerializeUtil.serialize(object)); 
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
    
   
    
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }
 
    /**
     * set
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(String key, String value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }
 
    /**
     * del
     * 
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
 
    /**
     * del
     * 
     * @param key
     */
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
 
    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
 
    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return dbSize;
    }
 
    /**
     * keys
     * 
     * @param regex
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }
 
    public void dels(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
            Iterator<byte[]> ito = keys.iterator();
            while (ito.hasNext()) {
                jedis.del(ito.next());
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
 
    public String getHost() {
        return host;
    }
 
    public void setHost(String host) {
        this.host = host;
    }
 
    public int getPort() {
        return port;
    }
 
    public void setPort(int port) {
        this.port = port;
    }
 
    public int getExpire() {
        return expire;
    }

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
 
}
