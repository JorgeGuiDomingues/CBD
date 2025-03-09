package redis.ex3;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class SimplePost {
    public static String USERS_KEY = "users"; // Key set for users' name
    public static String USERS_LIST = "list"; // Key list for users' name
    public static String USERS_HASHMAP = "map"; // Key hashmap for users' name

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        // some users
        String[] users = { "Ana", "Pedro", "Maria", "Luis" };

        // Set users
        // jedis.del(USERS_KEY); // remove if exists to avoid wrong type

        for (String user : users) {
            jedis.sadd(USERS_KEY, user);

            jedis.lpush(USERS_LIST, user);

            jedis.hset(USERS_HASHMAP, user, user);
        }

        System.out.println("Set Users:");
        jedis.smembers(USERS_KEY).forEach(System.out::println);

        System.out.println("\nList Users:");
        jedis.lrange(USERS_LIST, 0, jedis.llen(USERS_LIST)).forEach(System.out::println);

        System.out.println("\nHashMap Users:");
        for (Map.Entry<String, String> entry : jedis.hgetAll(USERS_HASHMAP).entrySet()) {
            System.out.println(entry.getValue());
        }

        jedis.del(USERS_KEY);
        jedis.del(USERS_LIST);
        jedis.del(USERS_HASHMAP);

        jedis.close();
    }
}