package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static projectResources.Queries.*;
import static projectResources.StaticEntityManager.entityManager;

@Entity
@Table(name = "departments")
public class Department {
    private Integer id;
    private String name;
    private Employee manager;
    private Set<Employee> employees;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "employee_id")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "department")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public static Stream<Employee> getInfoForAllEmployeesWithDepartmentName(String departmentName) {
        return entityManager
                .createQuery(GET_NAMES_OF_ALL_EMPLOYEES_WITH_DEPARTMENT_NAME, Employee.class)
                .setParameter("departmentName", departmentName)
                .getResultStream();
    }

    public static List<Object[]> getDepartmentNamesWithMaxSalaries(double lessThan, double moreThan) {
        return entityManager
                .createQuery(GET_DEPARTMENT_NAMES_WITH_MAX_SALARIES, Object[].class)
                .setParameter("lessThan", BigDecimal.valueOf(lessThan))
                .setParameter("moreThan", BigDecimal.valueOf(moreThan))
                .getResultList();
    }

}
