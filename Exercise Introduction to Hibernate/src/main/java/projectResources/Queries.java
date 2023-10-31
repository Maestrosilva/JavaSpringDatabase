package projectResources;

public class Queries {
    public static final String CHANGE_TO_UPPER_CASE_WHEN_LONGER_THAN =
            "UPDATE Town t " +
                    "SET t.name = UPPER(t.name) " +
                    "WHERE LENGTH(t.name) > : length";
    public static final String GET_EMPLOYEE_BY_FULL_NAME =
            "SELECT e FROM Employee e " +
                    "WHERE e.firstName = : firstName " +
                    "AND e.lastName =: lastName";
    public static final String GET_NAMES_OF_ALL_EMPLOYEES_WITH_SALARY_MORE_THAN =
            "SELECT e.firstName FROM Employee e " +
                    "WHERE e.salary > :min_salary";
    public static final String GET_NAMES_OF_ALL_EMPLOYEES_WITH_DEPARTMENT_NAME =
            "SELECT e FROM Employee e " +
                    "WHERE e.department.name = : departmentName " +
                    "ORDER BY e.salary, e.id";
    public static final String GET_ALL_ADDRESSES_AND_TOWN_WITH_PEOPLE_LIVING_THERE =
            "SELECT a FROM Address a " +
                    "ORDER BY a.employees.size DESC ";
    public static final String GET_DEPARTMENT_NAMES_WITH_MAX_SALARIES =
            "SELECT e.department.name, MAX(e.salary) " +
                    "FROM Employee e " +
                    "GROUP BY e.department.name " +
                    "HAVING MAX(e.salary) < :lessThan OR MAX(e.salary) > :moreThan";
    public static final String GET_EMPLOYEE_BY_FIRST_NAME_PATTERN =
            "SELECT e FROM Employee e " +
                    "WHERE LOWER(SUBSTRING(e.firstName, 1, :startLength)) =:start";
    public static final String GET_EMPLOYEE_BY_DEPARTMENT =
            "SELECT e FROM Employee e " +
                    "WHERE e.department.name IN :departmentNames";
    public static final String GET_EMPLOYEE_BY_LAST_NAME =
            "SELECT e FROM Employee e WHERE e.lastName = : lastName";
    public static final String GET_EMPLOYEE_BY_ID =
            "SELECT e FROM Employee e " +
                    "WHERE e.id = : employeeId";
    public static final String GET_LATEST_STARTED_PROJECTS =
            "SELECT p FROM Project p " +
                    "ORDER BY p.startDate DESC";
    public static final String DELETE_ADDRESSES_OF_TOWN =
            "SELECT a FROM Address a " +
                    "WHERE a.town = :townToDelete";
    public static final String SET_NULL_ADDRESSES_OF_EMPLOYEES_WITH_TOWN =
            "SELECT e FROM Employee e " +
                    "WHERE e.address.town = :townToDelete";

    public static final String TOWN_WITH_NAME =
            "SELECT t FROM Town t " +
                    "WHERE t.name =:townToDelete";
}