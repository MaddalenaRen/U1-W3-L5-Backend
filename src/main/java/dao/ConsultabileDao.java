package dao;

import entities.Consultabile;
import jakarta.persistence.EntityManager;

public class ConsultabileDao {

private EntityManager em;

    public ConsultabileDao(EntityManager em) {
        this.em = em;
    }

    public void save(Consultabile c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public Consultabile getByIsbn(String isbn){
        return em.find(Consultabile.class, isbn);

    }

    public void deleteByIsbn(String isbn) {
        Consultabile c = getByIsbn(isbn);
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        } else {
            System.out.println("Nessun elemento con ISBN " + isbn + " trovato.");
        }
    }

    public boolean existsByIsbn(String isbn) {
        Long count = em.createQuery(
                        "SELECT COUNT(c) FROM Consultabile c WHERE c.isbn = :isbn", Long.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
        return count > 0;
    }
}
