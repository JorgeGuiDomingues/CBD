package pt.tmg.cbd.lab1.ex5;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class ServiceSystemA {
    public static String LIMITANDTIMESLOT = "limandtime";
    public static String limit = "30";
    public static String timeslot = "3600"; // 1 hour
    public static String TIMEREQUESTS = "timerequests"; // store the time of the last request
    public static String PRODUCTS = "products"; // store the number of products

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        Scanner sc = new Scanner(System.in);

        String userName = "";
        String product = "";

        jedis.lpush(LIMITANDTIMESLOT, limit);
        jedis.rpush(LIMITANDTIMESLOT, timeslot);

        int limitInt = Integer.parseInt(jedis.lpop(LIMITANDTIMESLOT));
        int timeslotInt = Integer.parseInt(jedis.rpop(LIMITANDTIMESLOT));

        while (true) {
            System.out.print("User name ('Enter' for quit): ");
            userName = sc.nextLine();
            if (userName.isEmpty()) {
                break;
            }

            userName = "User" + userName; // prefix "User" to the username for Redis storage to avoid key collisions

            if (jedis.hget(TIMEREQUESTS, userName) == null) { // verify if the user is in the timerequests and set the
                                                              // time
                jedis.hset(TIMEREQUESTS, userName, String.valueOf(getTime(jedis)));
            }

            if (jedis.hget(PRODUCTS, userName) == null) { // verify if the user is in the products and set the products
                jedis.hset(PRODUCTS, userName, String.valueOf(jedis.scard(userName))); // get the number of elements in
                                                                                       // the set
            }

            while (true) {
                System.out.print("Product ('Enter' for quit and '-list' to list all the products): ");
                product = sc.nextLine();
                if (product.isEmpty()) {
                    break;
                }

                if (product.equals("-list")) {
                    Set<String> products = jedis.smembers(userName); // get all the products
                    for (String p : products) {
                        System.out.println(p.substring(7));
                    }
                    continue; // continue the loop
                }

                product = "Product" + product; // prefix "Product" to the product for Redis storage to avoid key
                                               // collisions

                long numberOfProducts = jedis.scard(userName);
                long numberOfRequests = Long.parseLong(jedis.hget(PRODUCTS, userName));

                long newProductsRequest = numberOfProducts - numberOfRequests;
                boolean limitReached = (newProductsRequest % limitInt == 0 && newProductsRequest > 0);

                if (limitReached) {
                    long timeSinceLastRequest = getTime(jedis) - Long.parseLong(jedis.hget(TIMEREQUESTS, userName));

                    if (timeSinceLastRequest < timeslotInt) {
                        long timeToWait = timeslotInt - timeSinceLastRequest;
                        System.out.println("You have to wait " + timeToWait + " seconds to make a new request.");
                        continue; // continue the loop
                    } else {
                        jedis.hset(TIMEREQUESTS, userName, String.valueOf(getTime(jedis)));
                    }
                }

                jedis.sadd(userName, product);
            }

        }

        jedis.flushAll(); // clear all the data
        sc.close();
        jedis.close();
    }

    public static long getTime(Jedis jedis) {
        List<String> times = jedis.time(); // return current time in seconds and microseconds in a list
        return Long.parseLong(times.get(0)); // get the seconds
    }
}
