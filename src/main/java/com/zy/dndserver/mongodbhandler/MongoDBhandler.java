package com.zy.dndserver.mongodbhandler;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.List;

public class MongoDBhandler {
    private static volatile Datastore datastore;

    private MongoDBhandler() {
        // 私有化构造方法，禁止外部直接实例化
    }

    public static Datastore getInstance() {
        if (datastore == null) {
            synchronized (MongoDBhandler.class) {
                if (datastore == null) {
                    datastore = Morphia.createDatastore(MongoClients.create(), "dndgame");
                }
            }
        }
        return datastore;
    }
}
