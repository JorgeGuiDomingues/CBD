package pt.tmg.cbd.lab2.ex3.d;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class RestaurantsDAO {
    private final MongoCollection<Document> mongoCollection;

    public RestaurantsDAO(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public int countLocalidades() {
        AggregateIterable<Document> query = mongoCollection.aggregate(List.of(
                new Document("$group", new Document("_id", "$localidade")), // group by localidade
                new Document("$count", "total"))); // count the number of groups

        Document result = query.first();
        if (result == null) { // no results
            return 0;
        }
        return result.getInteger("total");
    }

    public Map<String, Integer> countRestByLocalidade() {
        AggregateIterable<Document> query = mongoCollection.aggregate(
                List.of(new Document("$group",
                        new Document("_id", "$localidade").append("total", new Document("$sum", 1)))));
        // group by localidade and count the number of restaurants in each group

        Map<String, Integer> result = new HashMap<>();

        for (Document doc : query) {
            String localidade = doc.getString("_id");
            int total = doc.getInteger("total");
            result.put(localidade, total);
        }

        return result;
    }

    public List<String> getRestWithNameCloserTo(String name) {
        List<String> result = new ArrayList<>();

        Document query = new Document("nome", new Document("$regex", ".*" + name + ".*"));
        FindIterable<Document> queryResult = mongoCollection.find(query);

        for (Document doc : queryResult) {
            result.add(doc.getString("nome"));
        }

        return result;
    }
}
