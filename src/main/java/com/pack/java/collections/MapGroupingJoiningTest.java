package com.pack.java.collections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapGroupingJoiningTest {

    private class Employee {
        private Integer id;
        private String name;
        private BigDecimal sal;
        private Department dept;
        
        public Employee(Integer id,
                        String name,
                        BigDecimal sal,
                        Department dept) {
            super();
            this.id = id;
            this.name = name;
            this.sal = sal;
            this.dept = dept;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public BigDecimal getSal() {
            return sal;
        }
        public void setSal(BigDecimal sal) {
            this.sal = sal;
        }
        public Department getDept() {
            return dept;
        }
        public void setDept(Department dept) {
            this.dept = dept;
        }
        
        @Override
        public String toString() {
            return Arrays.asList(this.getClass().getMethods()).stream()
                                                       .filter(method -> method.getName().startsWith("get")).sorted((m1, m2) -> m1.getName().length() - m2.getName().length())
                                                       .collect(StringBuilder::new, (sb1, method) -> {
                                                        try {
                                                            sb1.append(",").append(method.invoke(this, null));
                                                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                                            // TODO Auto-generated catch block
                                                            e.printStackTrace();
                                                        }
                                                    }, (sb1, sb2) -> sb1.append(sb2))
                                                       .toString();
            
            /*Stream<Method> s = Arrays.asList(this.getClass().getMethods()).stream().filter(method -> method.getName().startsWith("getSal"));
            Optional<Method> salMet = s.findAny();
            return salMet.get().getName();*/
            
//            return "sal = " + getSal();
            
        }
    }
    
    private class Department {
        private Integer deptId;
        private String deptName;
        public Department(Integer deptId,
                          String deptName) {
            super();
            this.deptId = deptId;
            this.deptName = deptName;
        }
        public Integer getDeptId() {
            return deptId;
        }
        public void setDeptId(Integer deptId) {
            this.deptId = deptId;
        }
        public String getDeptName() {
            return deptName;
        }
        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }
        
        @Override
        public String toString() {
            return Arrays.asList(this.getClass().getMethods()).stream()
                    .filter(method -> method.getName().startsWith("get")).sorted((m1, m2) -> m1.getName().length() - m2.getName().length())
                    .collect(StringBuilder::new, (sb1, method) -> {
                     try {
                         sb1.append(",").append(method.invoke(this, null));
                     } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 }, (sb1, sb2) -> sb1.append(sb2))
                    .toString();
        }
    }
    
    public static void main(String[] args) {
        MapGroupingJoiningTest test = new MapGroupingJoiningTest();
        
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(test.new Employee(1, "Adi1", new BigDecimal(50000), test.new Department(1, "Dept1")));
        employeeList.add(test.new Employee(2, "Adi2", new BigDecimal(60000), test.new Department(2, "Dept2")));
        employeeList.add(test.new Employee(3, "Adi3", new BigDecimal(50000), test.new Department(3, "Dept3")));
        employeeList.add(test.new Employee(4, "Adi4", new BigDecimal(50000), test.new Department(4, "Dept4")));
        employeeList.add(test.new Employee(5, "Adi5", new BigDecimal(50000), test.new Department(5, "Dept5")));
        //Get employee map by dept
        Map<Department, List<Employee>> employeeMapByDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDept));
        
        //Get employee sal by dept
        Map<Department, Double> employeeSalByDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summingDouble(employee -> Double.valueOf( employee.getSal().toString() ) )));
        
        //Get employee map by partitioning below 3rd dept
        Map<Boolean, List<Employee>> employeePartitionMapForDept = employeeList.stream().collect(Collectors.partitioningBy(employee -> employee.getDept().getDeptId() <= 3));
        employeePartitionMapForDept.forEach((booleanKey, employee ) -> System.out.println("boolean = " + booleanKey + ", employee = " + employee.toString()));
    }
}
