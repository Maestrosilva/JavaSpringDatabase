package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static projectResources.Queries.*;
import static projectResources.StaticEntityManager.entityManager;

@Entity
@Table(name = "employees")
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String jobTitle;
    private Department department;
    private Employee manager;
    private LocalDateTime hireDate;
    private BigDecimal salary;
    private Address address;
    private Set<Project> projects;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Column(name = "hire_date")
    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    @Column(name = "salary")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToMany
    @JoinTable(name = "employees_projects",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "project_id"))
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public static Employee findEmployeeWithName(String firstName, String lastName){
        try {
            return entityManager
                    .createQuery(GET_EMPLOYEE_BY_FULL_NAME, Employee.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
    public static List<String> getAllNamesOfEmployeesWithSalaryMoreThan(double min_salary){
        return entityManager
                .createQuery(GET_NAMES_OF_ALL_EMPLOYEES_WITH_SALARY_MORE_THAN, String.class)
                .setParameter("min_salary", new BigDecimal(min_salary))
                .getResultList();
    }
    public static Employee getEmployeeById(int employeeId) {
        return entityManager
                .createQuery(GET_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }
    public static Employee getEmployeeByLastName(String lastName) {
        try {
            return entityManager
                    .createQuery(GET_EMPLOYEE_BY_LAST_NAME, Employee.class)
                    .setParameter("lastName", lastName)
                    .getSingleResult();
        }
        catch (NoResultException e){
            throw new IllegalArgumentException("No employee with such last name");
        }
    }
    public static List<Employee> getEmployeeByDepartment(Set<String> departmentNames) {
        return entityManager
                .createQuery(GET_EMPLOYEE_BY_DEPARTMENT, Employee.class)
                .setParameter("departmentNames", departmentNames)
                .getResultList();
    }

    public static List<Employee> getEmployeeByFirstNamePattern(String start) {
        return entityManager
                .createQuery(GET_EMPLOYEE_BY_FIRST_NAME_PATTERN, Employee.class)
                .setParameter("startLength", start.length())
                .setParameter("start", start)
                .getResultList();
    }

    public void increaseSalary(double percent) {
        this.salary = salary.multiply(BigDecimal.valueOf(1 + percent / 100));
    }
    public static void setNullAddressesOfEmployeesWithTown(Town townToDelete) {
        List<Employee> employeesToSetAddressValueNull = entityManager
                .createQuery(SET_NULL_ADDRESSES_OF_EMPLOYEES_WITH_TOWN, Employee.class)
                .setParameter("townToDelete", townToDelete)
                .getResultList();
        employeesToSetAddressValueNull.forEach(e -> e.setAddress(null));
    }
}
