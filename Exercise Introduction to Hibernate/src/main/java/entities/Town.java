package entities;

import javax.persistence.*;

import static projectResources.Queries.*;
import static projectResources.StaticEntityManager.entityManager;

@Entity
@Table(name = "towns")
public class Town {
    private Integer id;
    private String name;

    public static Town townWithName(String townName) {
        return entityManager
                .createQuery(TOWN_WITH_NAME, Town.class)
                .setParameter("townToDelete", townName)
                .getSingleResult();
    }

    public static void deleteTown(Town townToDelete) {
        entityManager.remove(townToDelete);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
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

    public static int changeToUpperCaseWhenNameLengthIsMoreThan(int length){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(CHANGE_TO_UPPER_CASE_WHEN_LONGER_THAN)
                .setParameter("length", 5);
        int affectedRows = query.executeUpdate();
        entityManager.getTransaction().commit();
        return affectedRows;
    }
}