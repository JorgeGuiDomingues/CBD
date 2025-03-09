package pt.tmg.cbd.lab1.ex4;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class AutoCompleteB {
    public static String USERS_KEY = "users2";
    public static String USERS_KEY_SCORES = "usersScores";
    public static String SORTED_SET = "sortedSet";

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();

        File file = new File("src/main/resources/nomes-pt-2021.csv");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String read = sc.nextLine();
            String parts[] = read.split(";");

            jedis.zadd(USERS_KEY, 0, parts[0]);
            jedis.zadd(USERS_KEY_SCORES, Double.parseDouble(parts[1]), parts[0]);
        }

        String name = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Search for ('Enter' for quit): ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                break;
            }

            List<String> result = jedis.zrangeByLex(USERS_KEY, "[" + name, "(" + name + Character.MAX_VALUE);
            for (String str : result) {
                jedis.zadd(SORTED_SET, jedis.zscore(USERS_KEY_SCORES, str), str);
            }

            // jedis.zrevrange(...) is a command that returns all the elements in the sorted
            jedis.zrevrange(SORTED_SET, 0, -1).forEach(str -> {
                System.out.println(str + " - " + jedis.zscore(USERS_KEY_SCORES, str));
            });

            jedis.del(SORTED_SET);
        }

        sc.close();
        scanner.close();
        jedis.close();
    }
}
