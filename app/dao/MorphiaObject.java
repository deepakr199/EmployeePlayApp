package dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MorphiaObject {

  private MorphiaObject() {
  }

  static public MongoClient mongo;
  static public Morphia morphia;
  static public Datastore datastore;
}