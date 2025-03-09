package redis.ex3;

import redis.clients.jedis.Jedis;

public class Forum {
    public static void main(String[] args) {
        // Ensure you have redis-server running
        Jedis jedis = new Jedis();
        System.out.println(jedis.ping());
        System.out.println(jedis.info());

        System.out.println(jedis.aclCat());
        System.out.println(jedis.aclUsers());
        System.out.println(jedis.set("benfica", "campeao"));
        System.out.println(jedis.bitcount("benfica"));

        jedis.close();
    }
}