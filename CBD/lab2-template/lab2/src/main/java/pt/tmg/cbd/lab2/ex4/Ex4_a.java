package pt.tmg.cbd.lab2.ex4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;

public class Ex4_a {
    public static int limit = 30;
    public static int timeslot = 3600;

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("cbd");
        MongoCollection<Document> usersTime = database.getCollection("usersTime");

        usersTime.drop();

        Scanner sc = new Scanner(System.in);

        String userName = "";
        String product = "";

        while (true) {
            System.out.print("User name ('Enter' for quit): ");
            userName = sc.nextLine().trim();
            if (userName.isEmpty()) {
                break;
            }

            System.out.println("User: " + userName);
            addUser(usersTime, userName);

            while (true) {
                System.out.print("Product ('Enter' for quit): ");
                product = sc.nextLine().trim();
                if (product.isEmpty()) {
                    break;
                } else {
                    addProduct(usersTime, userName, product);
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
                .append("Products", new ArrayList<String>())
                .append("Date", new ArrayList<Long>());
        collection.insertOne(user);
    }

    public static void addProduct(MongoCollection<Document> collection, String userName, String product) {
        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(Filters.eq("User", userName)),
                Aggregates.project(new Document("Products", 1).append("Date", 1))); // to find the user and get the
                                                                                    // products and dates

        Document user = collection.aggregate(pipeline).first();
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        List<Long> secondList = user.getList("Date", Long.class);

        // Remover a data mais antiga se ultrapassar o timeslot
        if (secondList.size() > 0) {
            long first = secondList.get(0);
            long last = System.currentTimeMillis() / 1000;
            if (last - first > timeslot) {
                secondList.remove(0);
            }
        }

        if (secondList.size() == limit) {
            System.out.println("Limit of products reached.");
            return;
        } else {
            secondList.add(System.currentTimeMillis() / 1000);
        }

        List<String> firstList = user.getList("Products", String.class);
        firstList.add(product);

        collection.updateOne(
                Filters.eq("User", userName),
                Updates.combine(
                        Updates.set("Products", firstList),
                        Updates.set("Date", secondList)));

        System.out.println("Product added successfully.");
    }
}
