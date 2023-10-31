import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static projectResources.StaticEntityManager.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("softuni");
        entityManager = factory.createEntityManager();
        Engine engine = new Engine();
        engine.run();
    }
}