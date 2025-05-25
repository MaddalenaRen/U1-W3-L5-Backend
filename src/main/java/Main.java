import dao.ArchivioDao;
import dao.ConsultabileDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {




    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

        EntityManager entityManager = emf.createEntityManager();

        ConsultabileDao consultabileDao = new ConsultabileDao(entityManager);

        ArchivioDao archivioDao = new ArchivioDao(entityManager, consultabileDao);



    }
}
