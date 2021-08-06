package dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

abstract public class AbstractDao<T> {

    private final MongoCollection<Document> database;
    private static final Gson gson = new Gson();

    public AbstractDao(MongoDatabase database,String nameOfBase) {

        this.database = database.getCollection(nameOfBase);
    }

    private Document mapperFrom(T instance) {

        return Document.parse(gson.toJson(instance));
    }

    private T mapperTo(Document document, Class<T> type) {

        return gson.fromJson(document.toJson(), type);
    }

    public void createData(List<T> collection) {

        List<Document> documents = new ArrayList<>();
        for(T element : collection) {
            Document document = mapperFrom(element);
            documents.add(document);
        }
        database.insertMany(documents);
    }


    public void deleteData(Bson filter) {

        database.deleteMany(filter);
    }

    public void updateData(Bson filter, HashMap<String, String> newData) {

        final Document updateData = new Document();
        for (Map.Entry<String, String> entry : newData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (newData.get(key)!=null) {
                updateData.append(key, value);
            }
        }

        final Document updateObject  = new Document();
        updateObject.append("$set", updateData);

        database.updateOne(filter, updateObject);
    }

    public ArrayList<T> readByFieldsData(Bson filter, Class<T> type) {

        return database.find(filter)
                .map(document -> mapperTo(document, type))
                .into(new ArrayList<>());
    }

    public ArrayList<T> readAllData(Class<T> type) {

        return database.find()
                .map(document -> mapperTo(document, type))
                .into(new ArrayList<>());
    }
}
