package pt.tmg.cbd.lab2.ex3;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import pt.tmg.cbd.lab2.ex3.d.RestaurantsDAO;
import java.util.Map;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

public class Ex3 {
        public static void main(String[] args) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("cbd");
                MongoCollection<Document> collection = database.getCollection("restaurants");

                collection.dropIndexes();

                System.out.println("--------------------- a) ---------------------\n");
                System.out.println("-----Adding restaurants to the collection-----");

                Document restaurant1 = new Document()
                                .append("address", new Document()
                                                .append("building", "12339")
                                                .append("coord", Arrays.asList(-54.27865, 45.34231))
                                                .append("rua", "Rua do Além")
                                                .append("zipcode", "12937"))
                                .append("localidade", "Marte")
                                .append("gastronomia", "Portuguesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 22),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 24)))
                                .append("nome", "Fundo do horizonte")
                                .append("restaurant_id", "999999");

                collection.insertOne(restaurant1);
                System.out.println("Restaurant inserted");
                System.out.println("Restaurant ID: " + restaurant1.get("restaurant_id") + "\n" + "nome: "
                                + restaurant1.get("nome"));

                Document restaurant2 = new Document()
                                .append("address", new Document()
                                                .append("building", "12438")
                                                .append("coord", Arrays.asList(-44.27865, 55.34231))
                                                .append("rua", "Rua do Universo")
                                                .append("zipcode", "13937"))
                                .append("localidade", "Júpter")
                                .append("gastronomia", "Portuguesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "B")
                                                                .append("score", 22),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 24)))
                                .append("nome", "Fundo do Mar")
                                .append("restaurant_id", "999998");

                collection.insertOne(restaurant2);
                System.out.println("Restaurant inserted");
                System.out.println("Restaurant ID: " + restaurant2.get("restaurant_id") + "\n" + "nome: "
                                + restaurant2.get("nome"));

                Document restaurant3 = new Document()
                                .append("address", new Document()
                                                .append("building", "15432")
                                                .append("coord", Arrays.asList(-24.27865, 35.34231))
                                                .append("rua", "Rua das Estrelas")
                                                .append("zipcode", "12988"))
                                .append("localidade", "Saturno")
                                .append("gastronomia", "Italiana")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 29),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "B")
                                                                .append("score", 18)))
                                .append("nome", "Cima do Céu")
                                .append("restaurant_id", "999997");

                collection.insertOne(restaurant3);
                System.out.println("Restaurant inserted");
                System.out.println("Restaurant ID: " + restaurant3.get("restaurant_id") + "\n" + "nome: "
                                + restaurant3.get("nome"));

                Document restaurant4 = new Document()
                                .append("address", new Document()
                                                .append("building", "18943")
                                                .append("coord", Arrays.asList(-74.27865, 65.34231))
                                                .append("rua", "Rua do Fogo")
                                                .append("zipcode", "15937"))
                                .append("localidade", "Vênus")
                                .append("gastronomia", "Francesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 20),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "C")
                                                                .append("score", 15)))
                                .append("nome", "Chamas do Destino")
                                .append("restaurant_id", "999996");

                collection.insertOne(restaurant4);
                System.out.println("Restaurant inserted");
                System.out.println("Restaurant ID: " + restaurant4.get("restaurant_id") + "\n" + "nome: "
                                + restaurant4.get("nome"));

                Document restaurant5 = new Document()
                                .append("address", new Document()
                                                .append("building", "19483")
                                                .append("coord", Arrays.asList(-94.27865, 75.34231))
                                                .append("rua", "Rua do Gelo")
                                                .append("zipcode", "17937"))
                                .append("localidade", "Plutão")
                                .append("gastronomia", "Chinesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "B")
                                                                .append("score", 25),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 28)))
                                .append("nome", "Frio Eterno")
                                .append("restaurant_id", "999995");

                collection.insertOne(restaurant5);
                System.out.println("Restaurant inserted");
                System.out.println("Restaurant ID: " + restaurant5.get("restaurant_id") + "\n" + "nome: "
                                + restaurant5.get("nome"));

                System.out.println("----------------------------------------------\n");

                System.out.println("------Updating restaurant with ID 999999------");

                Document query = new Document().append("restaurant_id", "999999"); // Query to find the restaurant with
                                                                                   // ID 999999

                Bson updates = Updates.combine(Updates.set("nome", "Fundo do Horizonte Atualizado")); // Update the name
                                                                                                      // of the
                                                                                                      // restaurant

                UpdateOptions options = new UpdateOptions().upsert(true); // If the document does not exist, create it

                try { // Update the document
                        UpdateResult result = collection.updateOne(query, updates, options);
                        System.out.println("Modified document count: " + result.getModifiedCount());
                        System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when the
                                                                                      // document is inserted and
                                                                                      // doesn't exist

                } catch (MongoException me) {
                        System.out.println("Unable to update due to an error: " + me);
                }

                System.out.println("----------------------------------------------\n");

                System.out.println("--------Print restaurant after update--------");

                // for (Document doc : collection.find()) {
                // doc.toJson();
                // System.out.println(
                // "Restaurant ID:" + doc.get("restaurant_id") + "\n" + "nome:" +
                // doc.get("nome"));
                // }

                // print only the updated restaurant
                FindIterable<Document> updatedRestaurant = collection.find(new Document("restaurant_id", "999999"));

                MongoCursor<Document> c = updatedRestaurant.iterator();

                while (c.hasNext()) {
                        Document doc = c.next();
                        System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" + "nome: "
                                        + doc.get("nome"));
                }

                System.out.println("----------------------------------------------\n");

                System.out.println("---Searching for restaurant with at least one grade B---");

                Bson filter = new Document("grades", new Document("$elemMatch", new Document("grade", "B")));

                FindIterable<Document> restaurantsWithGradeB = collection.find(filter);

                MongoCursor<Document> cursor = restaurantsWithGradeB.iterator();

                while (cursor.hasNext()) {
                        cursor.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                System.out.println("(...)");

                System.out.println("----------------------------------------------\n");

                System.out.println("--------------------- b) ---------------------\n");

                Document restaurant6 = new Document()
                                .append("address", new Document()
                                                .append("building", "12555")
                                                .append("coord", Arrays.asList(-74.27865, 25.34231))
                                                .append("rua", "Rua do Oceano")
                                                .append("zipcode", "12001"))
                                .append("localidade", "Neptuno")
                                .append("gastronomia", "Japonesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 21),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "B")
                                                                .append("score", 18)))
                                .append("nome", "Mar Infinito")
                                .append("restaurant_id", "999994");

                Document restaurant7 = new Document()
                                .append("address", new Document()
                                                .append("building", "16234")
                                                .append("coord", Arrays.asList(-64.27865, 15.34231))
                                                .append("rua", "Rua do Céu")
                                                .append("zipcode", "12902"))
                                .append("localidade", "Mercúrio")
                                .append("gastronomia", "Tailandesa")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1399715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 23),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1398857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 27)))
                                .append("nome", "Caminho Estrelado")
                                .append("restaurant_id", "999993");

                Document restaurant8 = new Document()
                                .append("address", new Document()
                                                .append("building", "17899")
                                                .append("coord", Arrays.asList(-44.27865, 12.34231))
                                                .append("rua", "Rua das Montanhas")
                                                .append("zipcode", "12901"))
                                .append("localidade", "Terra")
                                .append("gastronomia", "Brasileira")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1499715200000"))
                                                                .append("grade", "B")
                                                                .append("score", 19),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1578857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 30)))
                                .append("nome", "Raízes do Mundo")
                                .append("restaurant_id", "999992");

                Document restaurant9 = new Document()
                                .append("address", new Document()
                                                .append("building", "19842")
                                                .append("coord", Arrays.asList(-24.27865, 55.34231))
                                                .append("rua", "Rua da Terra")
                                                .append("zipcode", "17812"))
                                .append("localidade", "Saturno")
                                .append("gastronomia", "Mexicana")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "B")
                                                                .append("score", 20),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "C")
                                                                .append("score", 16)))
                                .append("nome", "Cozinha Estelar")
                                .append("restaurant_id", "999991");

                Document restaurant10 = new Document()
                                .append("address", new Document()
                                                .append("building", "10987")
                                                .append("coord", Arrays.asList(-54.27865, 33.34231))
                                                .append("rua", "Rua da Luz")
                                                .append("zipcode", "12934"))
                                .append("localidade", "Lua")
                                .append("gastronomia", "Italiana")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1359715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 25),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "B")
                                                                .append("score", 19)))
                                .append("nome", "Pasta Lunar")
                                .append("restaurant_id", "999990");

                Document restaurant11 = new Document()
                                .append("address", new Document()
                                                .append("building", "12210")
                                                .append("coord", Arrays.asList(-40.27865, 42.34231))
                                                .append("rua", "Rua do Pôr do Sol")
                                                .append("zipcode", "12345"))
                                .append("localidade", "Neptuno")
                                .append("gastronomia", "Espanhola")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1199715200000"))
                                                                .append("grade", "A")
                                                                .append("score", 22),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 24)))
                                .append("nome", "Tapas Cósmicas")
                                .append("restaurant_id", "999989");

                Document restaurant12 = new Document()
                                .append("address", new Document()
                                                .append("building", "14098")
                                                .append("coord", Arrays.asList(-54.27865, 31.34231))
                                                .append("rua", "Rua da Noite")
                                                .append("zipcode", "12340"))
                                .append("localidade", "Saturno")
                                .append("gastronomia", "Coreana")
                                .append("grades", Arrays.asList(
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1299715200000"))
                                                                .append("grade", "B")
                                                                .append("score", 22),
                                                new Document()
                                                                .append("date", new Document()
                                                                                .append("$date", "1378857600000"))
                                                                .append("grade", "A")
                                                                .append("score", 28)))
                                .append("nome", "Sabor do Espaço")
                                .append("restaurant_id", "999988");

                collection.insertOne(restaurant6);
                collection.insertOne(restaurant7);
                collection.insertOne(restaurant8);
                collection.insertOne(restaurant9);
                collection.insertOne(restaurant10);
                collection.insertOne(restaurant11);
                collection.insertOne(restaurant12);

                ////////////////////////

                long start = System.currentTimeMillis();

                FindIterable<Document> restlocal = collection.find(new Document("localidade", "Brooklyn"));

                MongoCursor<Document> cursor2 = restlocal.iterator();

                while (cursor2.hasNext()) {
                        cursor2.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end = System.currentTimeMillis();

                System.out.println("Time to search for restaurants in Brooklyn: " + (end - start) + "ms");

                collection.createIndex(Indexes.ascending("localidade"));

                long start2 = System.currentTimeMillis();

                FindIterable<Document> restlocal2 = collection.find(new Document("localidade", "Brooklyn"));

                MongoCursor<Document> cursor3 = restlocal2.iterator();

                while (cursor3.hasNext()) {
                        cursor3.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end2 = System.currentTimeMillis();

                System.out.println(
                                "Time to search for restaurants in Brooklyn after indexing: " + (end2 - start2) + "ms");

                System.out.println("----------------------------------------------\n");

                long start3 = System.currentTimeMillis();

                FindIterable<Document> restgastr = collection.find(new Document("gastronomia", "Hamburgers"));

                MongoCursor<Document> cursor4 = restgastr.iterator();

                while (cursor4.hasNext()) {
                        cursor4.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end3 = System.currentTimeMillis();

                System.out.println(
                                "Time to search for restaurants with Hamburgers gastronomy: " + (end3 - start3) + "ms");

                collection.createIndex(Indexes.ascending("gastronomia"));

                long start4 = System.currentTimeMillis();

                FindIterable<Document> restgastr2 = collection.find(new Document("gastronomia", "Hamburgers"));

                MongoCursor<Document> cursor5 = restgastr2.iterator();

                while (cursor5.hasNext()) {
                        cursor5.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end4 = System.currentTimeMillis();

                System.out.println("Time to search for restaurants with Hamburgers gastronomy after indexing: "
                                + (end4 - start4) + "ms");

                System.out.println("----------------------------------------------\n");

                long start5 = System.currentTimeMillis();

                FindIterable<Document> restnome = collection.find(new Document("nome", "Hot Bagels"));

                MongoCursor<Document> cursor6 = restnome.iterator();

                while (cursor6.hasNext()) {
                        cursor6.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end5 = System.currentTimeMillis();

                System.out.println("Time to search for restaurants with Hot Bagels name: " + (end5 - start5) + "ms");

                collection.createIndex(Indexes.text("nome"));

                long start6 = System.currentTimeMillis();

                FindIterable<Document> restnome2 = collection.find(new Document("nome", "Hot Bagels"));

                MongoCursor<Document> cursor7 = restnome2.iterator();

                while (cursor7.hasNext()) {
                        cursor7.next();
                        // System.out.println("Restaurant ID: " + doc.get("restaurant_id") + "\n" +
                        // "nome: "
                        // + doc.get("nome"));
                }

                long end6 = System.currentTimeMillis();

                System.out.println(
                                "Time to search for restaurants with Hot Bagels name after indexing: " + (end6 - start6)
                                                + "ms");

                System.out.println("----------------------------------------------\n");

                collection.deleteMany(Filters.in("restaurant_id", "999999", "999998", "999997", "999996", "999995",
                                "999994", "999993", "999992", "999991", "999990", "999989", "999988"));

                System.out.println("--------------------- c) ---------------------\n");

                System.out.println("4. Indique o total de restaurantes localizados no Bronx.");

                long countBronxRestaurants = collection.countDocuments(Filters.eq("localidade", "Bronx"));

                System.out.println("Total de restaurantes no Bronx: " + countBronxRestaurants);

                System.out.println("----------------------------------------------\n");

                System.out.println("8. Indique os restaurantes com latitude inferior a -95,7.");

                long countLatLessThan95_7 = collection.countDocuments(Filters.lt("address.coord.0", -95.7));
                System.out.println("Total de restaurantes com latitude inferior a -95,7: " + countLatLessThan95_7);

                System.out.println("----------------------------------------------\n");

                System.out.println(
                                "12. Liste o restaurant_id, o nome, a localidade e a gastronomia dos restaurantes localizados em \"Staten Island\", \"Queens\", ou \"Brooklyn\".");

                long countRestaurantsInAreas = collection.countDocuments(Filters.in("localidade",
                                Arrays.asList("Staten Island", "Queens", "Brooklyn")));
                System.out.println("Total de restaurantes em Staten Island, Queens ou Brooklyn: "
                                + countRestaurantsInAreas);

                System.out.println("----------------------------------------------\n");

                System.out.println(
                                "16. Liste o restaurant_id, o nome, o endereço (address) dos restaurantes onde o 2º elemento da matriz de coordenadas (coord) tem um valor superior a 42 e inferior ou igual a 52");

                long countCoordsBetween42And52 = collection.countDocuments(Filters.and(
                                Filters.gt("address.coord.1", 42),
                                Filters.lte("address.coord.1", 52)));
                System.out.println("Total de restaurantes com coordenadas entre 42 e 52: " + countCoordsBetween42And52);

                System.out.println("----------------------------------------------\n");

                System.out.println(
                                "20. Apresente o nome e número de avaliações (numGrades) dos 3 restaurante com mais avaliações.");

                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                Aggregates.project(Projections.fields(
                                                Projections.excludeId(),
                                                Projections.include("nome"),
                                                Projections.computed("numGrades", new Document("$size", "$grades")))),
                                Aggregates.sort(Sorts.descending("numGrades")),
                                Aggregates.limit(3)));

                for (Document doc : result) {
                        System.out.println(doc.toJson());
                }

                System.out.println("----------------------------------------------\n");

                System.out.println("--------------------- d) ---------------------\n");

                RestaurantsDAO restaurantsDAO = new RestaurantsDAO(collection);

                System.out.println("Numero de localidades distintas: " + restaurantsDAO.countLocalidades() + '\n');

                Map<String, Integer> countRestByLocalidade = restaurantsDAO.countRestByLocalidade();

                System.out.println("Numero de restaurantes por localidade:");
                for (Map.Entry<String, Integer> entry : countRestByLocalidade.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                }
                System.out.println();

                List<String> restWithNameCloserTo = restaurantsDAO.getRestWithNameCloserTo("Park");

                System.out.println("Nome de restaurantes contendo 'Park' no nome:");
                for (String name : restWithNameCloserTo) {
                        System.out.println(" -> " + name);
                }

                mongoClient.close();
                System.exit(0);
        }
}