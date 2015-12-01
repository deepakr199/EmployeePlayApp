package service;

import dao.EmployeeDAO;

import models.Employee;

import java.util.List;

public interface EmployeeService {

  public static List<Employee> getAllEmployees() {
    return EmployeeDAO.getAllEmployees();
  }

  public static Employee getEmployee(Long id) {
    return EmployeeDAO.getEmployee(id);
  }

  public static Employee addEmployee(Employee employee) {
    employee.setId(EmployeeDAO.getId());
    return EmployeeDAO.saveEmployee(employee);
  }

  public static Employee updateEmployee(Employee employee) {
    return EmployeeDAO.saveEmployee(employee);
  }

  public static boolean deleteEmployee(Long id) {
    return EmployeeDAO.deleteEmployee(id);
  }

}
