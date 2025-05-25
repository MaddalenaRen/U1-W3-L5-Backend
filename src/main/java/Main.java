import dao.ArchivioDao;
import dao.ConsultabileDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.Libro;
import entities.Prestito;
import entities.Rivista;
import entities.Utente;
import enumeration.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {




    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");

        EntityManager entityManager = emf.createEntityManager();

        ConsultabileDao consultabileDao = new ConsultabileDao(entityManager);
        ArchivioDao archivioDao = new ArchivioDao(entityManager, consultabileDao);
        UtenteDao utenteDao = new UtenteDao(entityManager);
        PrestitoDao prestitoDao = new PrestitoDao(entityManager);



        Libro libro1 = new Libro("123ABC", "Il Signore degli Anelli", 1954, 1000, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro("456DEF", "Harry Potter", 1997,332,"J.K. Rowling", "Fantasy");

        Rivista rivista1 = new Rivista("789GHI","Focus", 1992,130, Periodicita.MENSILE );
        Rivista rivista2 = new Rivista("101LMN","Cosmopolitan",1886,150,Periodicita.MENSILE);

        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(2000,5,6),123450300);
        Utente u2 = new Utente("Dario", "Bianchi", LocalDate.of(1998,7,25),456523000);
        Utente u3 = new Utente("Aldo", "Baglio", LocalDate.of(1960,5,2),98700654);

        Prestito p1 = new Prestito(u1,rivista1,LocalDate.of(2025,3,1));
        p1.setDataDiEffettivaRestituzione(LocalDate.of(2025, 3, 25));
        Prestito p2 = new Prestito(u2,libro1,LocalDate.of(2025,4,2));
        Prestito p3 = new Prestito(u3,rivista2,LocalDate.of(2025,5,10));

        utenteDao.save(u1);
        utenteDao.save(u2);
        utenteDao.save(u3);

        consultabileDao.save(libro1);
        consultabileDao.save(libro2);
        consultabileDao.save(rivista1);
        consultabileDao.save(rivista2);

        prestitoDao.save(p1);
        prestitoDao.save(p2);
        prestitoDao.save(p3);



        System.out.println("--- Ricerca per titolo 'Harry' ---");
        archivioDao.ricercaPerTitolo("Harry").forEach(System.out::println);

        System.out.println("--- Ricerca per titolo 'Il Signore' ---");
        archivioDao.ricercaPerTitolo("Il Signore").forEach(System.out::println);

        System.out.println("--- Ricerca per titolo 'Focus' ---");
        archivioDao.ricercaPerTitolo("Focus").forEach(System.out::println);

        System.out.println("--- Ricerca per titolo 'Cosmopolitan' ---");
        archivioDao.ricercaPerTitolo("Cosmopolitan").forEach(System.out::println);

        System.out.println("--- Elementi in prestito da utente 123450300 ---");
        archivioDao.elementiInPrestito(123450300).forEach(System.out::println);

        System.out.println("--- Elementi in prestito da utente 456523000 ---");
        archivioDao.elementiInPrestito(456523000).forEach(System.out::println);

        System.out.println("--- Elementi in prestito da utente 0001234503 ---");
        archivioDao.elementiInPrestito(98700654).forEach(System.out::println);

        System.out.println("--- Prestiti scaduti ---");
        archivioDao.prestitiScaduti().forEach(System.out::println);



    }
}
