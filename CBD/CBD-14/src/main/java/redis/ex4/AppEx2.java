package redis.ex4;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class AppEx2 {
    public static String USERS_KEY = "users2";
    public static String USERS_KEY_SCORES = "usersScores";

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();

        File file = new File("nomes-pt-2021.csv");
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
            if (name == "") {
                break;
            }

            List<String> result = jedis.zrangeByLex(USERS_KEY, "[" + name, "(" + name + Character.MAX_VALUE);
            Collections.sort(result, (name1, name2) -> Double.compare(jedis.zscore(USERS_KEY_SCORES, name2),
                    jedis.zscore(USERS_KEY_SCORES, name1)));

            for (String str : result) {
                System.out.println(str + " - " + jedis.zscore(USERS_KEY_SCORES, str));
            }
        }

        sc.close();
        scanner.close();
        jedis.close();
    }
}
