package pt.tmg.cbd.lab2.ex4;

import java.util.Map;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.HashMap;

public class Ex4_b {
    public static int limit = 4;
    public static int timeslot = 40;

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("cbd");
        MongoCollection<Document> usersQuantity = database.getCollection("usersQuantity");

        usersQuantity.drop();

        Scanner sc = new Scanner(System.in);

        String userName = "";
        String product = "";
        int quantity = 0;

        while (true) {
            System.out.print("User name ('Enter' for quit): ");
            userName = sc.nextLine().trim();
            if (userName.isEmpty()) {
                break;
            }

            System.out.println("User: " + userName);
            addUser(usersQuantity, userName);

            while (true) {
                System.out.print("Product ('Enter' for quit): ");
                product = sc.nextLine().trim();
                if (product.isEmpty()) {
                    break;
                } else {
                    System.out.print("Quantity: ");
                    quantity = Integer.parseInt(sc.nextLine().trim());
                    addProduct(usersQuantity, userName, product, quantity);
                }
            }
        }

        sc.close();
        mongoClient.close();

        System.exit(0);
    }

    public static void addUser(MongoCollection<Document> collection, String userName) {
        if (collection.countDocuments(Filters.eq("User", userName)) > 0) {
            return;
        }

        Document user = new Document("User", userName)
                .append("Products", new HashMap<String, Integer>())
                .append("Quantity", 0).append("Time", System.currentTimeMillis() / 1000);
        collection.insertOne(user);
    }

    public static void addProduct(MongoCollection<Document> collection, String userName, String product, int quantity) {
        Document user = collection.find(Filters.eq("User", userName)).first();

        @SuppressWarnings("unchecked")
        Map<String, Integer> products = (Map<String, Integer>) user.get("Products");

        int totalQuantity = (int) user.get("Quantity");
        long time = (long) user.get("Time");

        if (System.currentTimeMillis() / 1000 - time > timeslot) {
            totalQuantity *= 0;
        }

        if (totalQuantity + quantity > limit) {
            System.out.println("Limit exceeded.");
            return;
        }

        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }

        int newQuantity = totalQuantity + quantity;
        collection.updateOne(Filters.eq("User", userName),
                Updates.combine(
                        Updates.set("Quantity", newQuantity),
                        Updates.set("Time", System.currentTimeMillis() / 1000)));

        collection.updateOne(Filters.eq("User", userName),
                Updates.set("Products", products));

        System.out.println("Product added successfully.");
    }
}
