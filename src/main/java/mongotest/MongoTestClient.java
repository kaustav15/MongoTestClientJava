package mongotest;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MongoTestClient {

    public static void main(String[] args){
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("SSOSessions");
        MongoCollection<Document> collection = database.getCollection("SSOSessionStore");


        Document doc = new Document("name", "user "+ Calendar.getInstance().getTimeInMillis())
                .append("role", "EXOF")
                .append("country", "UK");

        collection.insertOne(doc);

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
