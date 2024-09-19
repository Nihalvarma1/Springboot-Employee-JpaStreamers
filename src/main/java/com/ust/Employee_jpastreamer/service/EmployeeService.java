package com.ust.Employee_jpastreamer.service;



import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private Employeerepo employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll();
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public List<Employee> getEmployeeByAgeRange(int min, int max) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getAge() >= min && employee.getAge() <= max)
                .toList();
    }

    public List<Employee> getEmployeeByJoiningYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .toList();
    }

    public Long getCountByGender(String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(emp->emp.getGender().equalsIgnoreCase(gender))
                .count();
    }

    public Map<String, Long> getGenderCountByYear(int year){
        return jpaStreamer.stream(Employee.class)
                .filter(emp->emp.getJoiningYear()==year)
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    }

    public Map<String,List<Employee>> groupByEducation() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getEducation));
    }

    public List<Employee> filterByYearGenderExperienceEducation(int year, String gender, int experience, String education) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .filter(employee -> employee.getExperienceInCurrentDomain() == experience)
                .filter(employee -> employee.getEducation().equalsIgnoreCase(education))
                .toList();
    }
}
