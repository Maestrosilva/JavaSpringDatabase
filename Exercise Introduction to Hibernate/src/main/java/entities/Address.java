package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static projectResources.Queries.*;
import static projectResources.StaticEntityManager.entityManager;

@Entity
@Table(name = "addresses")
public class Address {
    private Integer id;
    private String text;
    private Town town;
    private Set<Employee> employees;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "address_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "town_id",referencedColumnName = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "address")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public static List<Address> getAllAddressesAndTownsWithPeopleLivingThere() {
        return entityManager
                .createQuery(GET_ALL_ADDRESSES_AND_TOWN_WITH_PEOPLE_LIVING_THERE,Address.class)
                .setMaxResults(10)
                .getResultList();
    }

    public static int deleteAddressesOfTown(Town townToDelete) {
        List<Address> addressesToDelete = entityManager
                .createQuery(DELETE_ADDRESSES_OF_TOWN, Address.class)
                .setParameter("townToDelete", townToDelete)
                .getResultList();
        int affectedRows = addressesToDelete.size();
        addressesToDelete.forEach(entityManager::remove);
        return affectedRows;
    }
}
