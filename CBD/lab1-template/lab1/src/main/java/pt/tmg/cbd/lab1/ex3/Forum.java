package pt.tmg.cbd.lab1.ex3;

import redis.clients.jedis.Jedis;

public class Forum {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        System.out.println(jedis.ping());
        System.out.println(jedis.info());

        System.out.println(jedis.set("user:1001", "Jorge"));
        System.out.println(jedis.set("user:1002", "Domingues"));
        System.out.println("GET user:1001: " + jedis.get("user:1001"));
        System.out.println(jedis.incr("page:views"));
        System.out.println(jedis.incrBy("page:views", 10));
        System.out.println(jedis.lpush("tasks", "Task 1"));
        System.out.println(jedis.lpush("tasks", "Task 2"));
        System.out.println("LPOP tasks: " + jedis.lpop("tasks"));
        System.out.println(jedis.sadd("online_users", "user:1001"));
        System.out.println("SISMEMBER online_users user:1001: " + jedis.sismember("online_users", "user:1001"));

        jedis.close();
    }
}