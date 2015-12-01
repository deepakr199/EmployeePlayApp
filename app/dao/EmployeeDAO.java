package dao;

import com.mongodb.WriteConcern;
import models.Counter;
import models.Employee;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

public class EmployeeDAO {
  
  public static Long getId() {
    UpdateOperations<Counter> updateOps = MorphiaObject.datastore.createUpdateOperations(Counter.class).inc("c");
    MorphiaObject.datastore.setDefaultWriteConcern(WriteConcern.FSYNC_SAFE);
    Query<Counter> query = MorphiaObject.datastore.createQuery(Counter.class).field("_id").equal("employee");
    Counter counter = MorphiaObject.datastore.findAndModify(query, updateOps);
    if (counter != null) {
      return counter.getCounter();
    }
    Counter saveCounter = new Counter();
    saveCounter.set_id("employee");
    saveCounter.setCounter(1);
    MorphiaObject.datastore.save(saveCounter);
    return 1L;
  }
  
  public static List<Employee> getAllEmployees() {
    return MorphiaObject.datastore.createQuery(Employee.class).asList();
  }

  public static Employee getEmployee(Long id) {
    return MorphiaObject.datastore.createQuery(Employee.class).field("id").equal(id).get();
  }

  public static Employee saveEmployee(Employee employee) {
    MorphiaObject.datastore.save(employee);
     return employee;
  }

  public static boolean deleteEmployee(Long id) {
    MorphiaObject.datastore.delete(MorphiaObject.datastore.createQuery(Employee.class).field("id").equal(id));
    return true;
  }
  
}
