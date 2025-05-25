package dao;

import entities.Prestito;
import jakarta.persistence.EntityManager;

public class PrestitoDao {

    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void save (Prestito p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

    }

    public Prestito getById(int id){
        return em.find(Prestito.class, id );

    }

    public void update(Prestito p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    public void remove(int id) {
        Prestito p = getById(id);

        if (p != null) {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } else {
            System.out.println("prestito " + p + " non esiste");
        }
    }
}
