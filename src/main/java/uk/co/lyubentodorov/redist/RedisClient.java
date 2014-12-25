package uk.co.lyubentodorov.redist;

import redis.clients.jedis.Jedis;

/**
 * Access DB Layer and retrieves data. Implemented as
 * singleton to save DB connections.
 */
public class RedisClient
{
    private Jedis jedis;
    private static final String hostAddress = "localhost";
    private static RedisClient client = null;

    private RedisClient()
    {
        jedis = new Jedis(hostAddress);
    }

    public static RedisClient getClient()
    {
        if (client == null)
            client = new RedisClient();
        return client;
    }

    public String fetch(String key)
    {
        return client.jedis.get(key);
    }
}
