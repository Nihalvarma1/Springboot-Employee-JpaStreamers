package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){
        return employeeService.groupbyEmployeeByCity();
    }
    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }
    @GetMapping("/getbyagerange")
    public List<Employee> getEmployeeByAgeRange(@RequestParam int min, @RequestParam int max){
        return employeeService.getEmployeeByAgeRange(min,max);
    }
    @GetMapping("/getcountbygender")
    public Long getCountByGender(@RequestParam String gender){
        return employeeService.getCountByGender(gender);
    }
    @GetMapping("/getempbyyear")
    public List<Employee> getEmployeeByJoiningYear(@RequestParam int year){
        return employeeService.getEmployeeByJoiningYear(year);
    }
    @GetMapping("/getgendercountbyyear")
    public Map<String,Long> getGenderCountByYear(@RequestParam int year){
        return employeeService.getGenderCountByYear(year);
    }
    @GetMapping("/groupbyeducation")
    public Map<String,List<Employee>> groupByEducation(){
        return employeeService.groupByEducation();
    }
    @GetMapping("/filterbyyeargenderexperienceeducation")
    public List<Employee> filterByYearGenderExperienceEducation(@RequestParam int year, @RequestParam String gender, @RequestParam int experience, @RequestParam String education){
        return employeeService.filterByYearGenderExperienceEducation(year, gender, experience, education);
    }
}
