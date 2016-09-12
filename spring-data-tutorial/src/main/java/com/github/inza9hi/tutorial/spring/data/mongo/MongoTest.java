package com.github.inza9hi.tutorial.spring.data.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by lawulu on 16-8-22.
 */
public class MongoTest {

    public static void main(String[] args) {
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8123");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "3128");


        MongoClientURI connectionString = new MongoClientURI("mongodb://root:Test_2016@dds-2ze7b34fbefd8a941.mongodb.rds.aliyuncs.com:3717,dds-2ze7b34fbefd8a942.mongodb.rds.aliyuncs.com:3717/admin?replicaSet=mgset-1735111");
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("test");


        String str ="{" +
                "   \"name\" : \"MongoDB\"," +
                "   \"type\" : \"database\"," +
                "   \"count\" : 1," +
                "   \"info\" : {" +
                "               x : 203," +
                "               y : 102" +
                "             }" +
                "}";

        Document parse = Document.parse(str);

        Document doc = new Document()
                .append("type", "111")
                .append("11", 1)
                .append("111", new Document("x", 203).append("y", 2221));

//
//        BasicDBObject query = new BasicDBObject();
//        query.put("_id", new ObjectId("1111"));
//        Document first = collection.find(query).first();
////
////        Document myDoc = collection.find().
////                .find("57bab21c8fed5e1d942ad384").first();
      //  System.out.println(first.toJson());

        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            System.out.println(document.toJson());

        }
        Document first = collection.find(eq("_id", "1112")).first();
        System.out.println(first.toJson());
        collection.updateOne(eq("_id", "1112"),new Document("$set", doc),new UpdateOptions().upsert(true));

        first = collection.find(eq("_id", "1112")).first();

        System.out.println(first.toJson());
        System.out.println(collection.count());
        // collection.insertOne(parse);

        //主键
        //upsert
        //多写
        //性能
        // If you specify a maximum number of documents for a capped collection using the max parameter to create, the limit must be less than 232 documents. If you do not specify a maximum number of documents when creating a capped collection, there is no limit on the number of documents.
        //@see https://docs.mongodb.com/manual/reference/limits/









    }
}
