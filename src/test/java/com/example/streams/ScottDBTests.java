package com.example.streams;

import com.example.streams.scottdb.Department;
import com.example.streams.scottdb.Employee;
import com.example.streams.scottdb.MemoryScottDB;
import com.example.streams.scottdb.SalaryGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.*;

public class ScottDBTests {
    @Test
    @DisplayName("Print all the employees and departments")
    public void assignment1() {
        MemoryScottDB database = new MemoryScottDB();

        List<Department> departments = database.getDepartments();
        List<Employee> employees = database.getEmployees();

        departments.forEach(System.out::println);
        employees.forEach(System.out::println);
    }

    @Test
    @DisplayName("Find all employees with job is Clerk")
    public void assignment2() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .filter(employee -> employee.getJob().equals("CLERK"))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Find all employees with job is Clerk and sort by salary (small to high).")
    public void assignment3() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .filter(employee -> employee.getJob().equals("CLERK"))
                .sorted(comparing(Employee::getSalary))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("What are all the unique jobs of the employees?")
    public void assignment4() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .map(Employee::getJob)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Find all employees working on department 10 and sort them by name.")
    public void assignment5() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .filter(employee -> employee.getDepartmentId() == 10)
                .sorted(comparing(Employee::getName))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Return a string of all employees names sorted alphabetically")
    public void assignment6() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        String result = employees.stream()
                .sorted(comparing(Employee::getName))
                .map(Employee::getName)
                .collect(joining(" "));

        System.out.println(result);
    }

    @Test
    @DisplayName("Are there any employees based in New York?")
    public void assignment7() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .filter(employee -> employee.getDepartment().getLocation().equals("NEW YORK"))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Print all employees having a salary in scale 2")
    public void assignment8() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();
        List<SalaryGrade> salaryGrades = database.getSalaryGrades();
        SalaryGrade salaryGrade2 = null;

        for (SalaryGrade salaryGrade : salaryGrades) {
            if (salaryGrade.getGrade() == 2) {
                salaryGrade2 = salaryGrade;
            }
        }

        if (salaryGrade2 == null) {
            return;
        }

        SalaryGrade finalSalaryGrade = salaryGrade2;
        employees.stream()
                .filter(employee -> employee.getSalary() >= finalSalaryGrade.getLowCutoff()
                            && employee.getSalary() <= finalSalaryGrade.getHighCutoff())
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("How many employees earn a salary in scale 2 or 3")
    public void assignment9() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();
        List<SalaryGrade> salaryGrades = database.getSalaryGrades();
        SalaryGrade salaryGrade2 = null;
        SalaryGrade salaryGrade3 = null;
        double lowCutoff, highCutoff;

        for (SalaryGrade salaryGrade : salaryGrades) {
            if (salaryGrade.getGrade() == 2) {
                salaryGrade2 = salaryGrade;
            }
            if (salaryGrade.getGrade() == 3) {
                salaryGrade3 = salaryGrade;
            }
        }

        if (salaryGrade2 == null && salaryGrade3 == null) {
            return;
        }
        lowCutoff = Objects.requireNonNullElse(salaryGrade2, salaryGrade3).getLowCutoff();
        highCutoff = Objects.requireNonNullElse(salaryGrade3, salaryGrade2).getHighCutoff();

        long employeeAmount = employees.stream()
                .filter(employee -> employee.getSalary() >= lowCutoff && employee.getSalary() <= highCutoff)
                .count();

        System.out.println(employeeAmount);
    }

    @Test
    @DisplayName("What's the highest value of all the salaries?")
    public void assignment10() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        Optional<Employee> highestSalaryEmployee = employees.stream()
                .max(comparing(Employee::getSalary));

        highestSalaryEmployee.ifPresent(employee -> System.out.println(employee.getSalary()));
    }

    @Test
    @DisplayName("Find the salary with the smallest value")
    public void assignment11() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        Optional<Employee> highestSalaryEmployee = employees.stream()
                .min(comparing(Employee::getSalary));

        highestSalaryEmployee.ifPresent(employee -> System.out.println(employee.getSalary()));
    }

    @Test
    @DisplayName("Find total sum of salaries for all employees.")
    public void assignment12() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();
        AtomicReference<Double> totalSalary = new AtomicReference<>((double) 0);

        employees.forEach(employee -> totalSalary.updateAndGet(v -> v + employee.getSalary()));

        System.out.println(totalSalary.get());
    }

    @Test
    @DisplayName("Show all employees grouped by job.")
    public void assignment13() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        employees.stream()
                .sorted(comparing(Employee::getJob))
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Count the number of employees for each department.")
    public void assignment14() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();
        List<Department> departments = database.getDepartments();

        departments.forEach(department -> {
            long employeeNumber = employees.stream()
                                    .filter(employee -> employee.getDepartment().equals(department))
                                    .count();
            System.out.println(department.getName() + ": " + employeeNumber);
        });
    }

    @Test
    @DisplayName("Show all employees for each department, each employee on a new line.")
    public void assignment15() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        System.out.println(employees.stream()
                .collect(groupingBy(Employee::getDepartmentId,
                        mapping(Employee::toString, joining("\n")))));
    }

    @Test
    @DisplayName("Show the highest salary for each job.")
    public void assignment16() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        System.out.println(
                employees.stream()
                        .collect(groupingBy(Employee::getJob,
                                mapping(Employee::getSalary, maxBy(naturalOrder())))));
    }

    @Test
    @DisplayName("For each job show the employees working there, grouped by department")
    public void assignment17() {
        MemoryScottDB database = new MemoryScottDB();

        List<Employee> employees = database.getEmployees();

        Map<String, Map<Integer, List<Employee>>> employeeGroupedMap =
                employees.stream()
                        .collect(groupingBy(Employee::getJob, groupingBy(Employee::getDepartmentId)));

        employeeGroupedMap.forEach((job, employeesByDepartment) -> {
            System.out.println("Job: " + job);
            employeesByDepartment.forEach((departmentId, employeeList) -> {
                System.out.println("   DepartmentId: " + departmentId);
                employeeList.forEach(employee -> System.out.println("        " + employee));
            });
        });
    }
}
