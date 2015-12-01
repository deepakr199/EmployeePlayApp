import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import dao.MorphiaObject;
import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Morphia;
import play.Application;
import play.Configuration;
import play.GlobalSettings;
import play.Logger;

import java.util.Arrays;

/**
 * The Class Global.
 */
public class Global extends GlobalSettings {

  @Override
  public void onStart(Application app) {
    initializeMorphia(app.configuration());
  }

  /**
   * Initialize morphia.
   * 
   * @param conf
   *          the conf
   */
  private void initializeMorphia(Configuration conf) {
    String mongoHost = conf.getString("mongodb.host");
    Integer port = conf.getInt("mongodb.port");
    String user = conf.getString("mongodb.user");
    String password = conf.getString("mongodb.password");
    String db = conf.getString("mongodb.db");
    MongoCredential credential = null;
    if (StringUtils.isNotBlank(user)) {
      credential = MongoCredential.createCredential(user, db, password.toCharArray());
    }

    if (credential != null) {
      MorphiaObject.mongo = new MongoClient(new ServerAddress(mongoHost, port), Arrays.asList(credential));
    } else {
      MorphiaObject.mongo = new MongoClient(new ServerAddress(mongoHost, port));
    }
    Logger.info("Connecting to mongo server - " + mongoHost);

    MorphiaObject.morphia = new Morphia();
    MorphiaObject.datastore = MorphiaObject.morphia.createDatastore(MorphiaObject.mongo, db);
    MorphiaObject.datastore.ensureIndexes();
    MorphiaObject.datastore.ensureCaps();
    MorphiaObject.datastore.setDefaultWriteConcern(WriteConcern.UNACKNOWLEDGED);
  }

}