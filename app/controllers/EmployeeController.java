package controllers;

import models.Employee;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.EmployeeService;

import java.util.List;

public class EmployeeController extends Controller {

  public Result getAllEmployees() {
    List<Employee> employees = EmployeeService.getAllEmployees();
    return ok(Json.toJson(employees));
  }

  public Result getEmployee(Long id) {
    Employee employee = EmployeeService.getEmployee(id);
    return ok(Json.toJson(employee));
  }

  public Result addEmployee() {
    Employee employee = Json.fromJson(request().body().asJson(), Employee.class);
    employee = EmployeeService.addEmployee(employee);
    return ok(Json.toJson(employee));
  }

  public Result updateEmployee() {
    Employee employee = Json.fromJson(request().body().asJson(), Employee.class);
    employee = EmployeeService.updateEmployee(employee);
    return ok(Json.toJson(employee));
  }

  public Result deleteEmployee(Long id) {
    EmployeeService.deleteEmployee(id);
    return ok();
  }
  
}
