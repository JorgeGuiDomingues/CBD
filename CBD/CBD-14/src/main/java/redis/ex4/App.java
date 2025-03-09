package redis.ex4;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class App {
    public static String USERS_KEY = "users";

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();

        File file = new File("names.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            jedis.zadd(USERS_KEY, 0, sc.nextLine());
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

            for (String str : result) {
                System.out.println(str);
            }
        }

        sc.close();
        scanner.close();
        jedis.close();
    }
}
