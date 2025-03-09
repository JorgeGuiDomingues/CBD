package pt.tmg.cbd.lab1.ex5;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class ServiceSystemB {
    public static String LIMITANDTIMESLOT = "limandtime";
    public static String limit = "5";
    public static String timeslot = "60";
    public static String TIMEREQUESTS = "timerequests"; // store the time of the last request
    public static String PRODUCTSQUANTITIES = "products"; // store the number of products

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.flushAll(); // clear all the data

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

            if (jedis.hget(PRODUCTSQUANTITIES, userName) == null) { // verify if the user is in the products and set the
                                                                    // products
                jedis.hset(PRODUCTSQUANTITIES, userName, "0"); // set the number of products to 0
            }

            while (true) {
                System.out.print("Product ('Enter' for quit and '-list' to list all the products): ");
                product = sc.nextLine();
                if (product.isEmpty()) {
                    break;
                }

                if (product.equals("-list")) {
                    Set<String> products = jedis.hkeys(userName);
                    for (String p : products) {
                        System.out.println(p.substring(7) + ": " + jedis.hget(userName, p));
                    }

                    continue; // continue the loop
                }

                product = "Product" + product; // prefix "Product" to the product for Redis storage to avoid key
                                               // collisions

                System.out.print("Quantity: ");
                long quantity = sc.nextLong();
                sc.nextLine(); // consume the newline character

                if (quantity < 1) {
                    System.out.println("Quantity must be positive.");
                    continue; // continue the loop
                }

                long numberOfRequests = Long.parseLong(jedis.hget(PRODUCTSQUANTITIES, userName));

                boolean limitReached = (numberOfRequests % limitInt == 0) && numberOfRequests > 0;

                if (limitReached) {
                    long timeSinceLastRequest = getTime(jedis) - Long.parseLong(jedis.hget(TIMEREQUESTS, userName));
                    if (timeSinceLastRequest < timeslotInt) {
                        long timeToWait = timeslotInt - timeSinceLastRequest;
                        System.out.println("You have to wait " + timeToWait + " seconds to make a new request.");
                        continue; // continue the loop
                    } else {
                        jedis.hset(TIMEREQUESTS, userName, String.valueOf(getTime(jedis)));
                        jedis.hset(PRODUCTSQUANTITIES, userName, "0");
                        numberOfRequests = Long.parseLong(jedis.hget(PRODUCTSQUANTITIES, userName));
                    }
                }

                if ((quantity + numberOfRequests) > limitInt) {
                    System.out.println("You can only add " + (limitInt - numberOfRequests) + " products.");
                    continue; // continue the loop
                }

                // jedis.hsetnx(...) // is a command that sets the field in the hash only if it
                jedis.hsetnx(userName, product, "0"); // Add the product to the user's list of products if it doesn't
                                                      // exist

                // jedis.hincrBy(...) // is a command that increments the field in the hash by
                // the specified value
                jedis.hincrBy(PRODUCTSQUANTITIES, userName, quantity); // Update the number of products
                jedis.hincrBy(userName, product, quantity); // Update the quantity of the product

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
