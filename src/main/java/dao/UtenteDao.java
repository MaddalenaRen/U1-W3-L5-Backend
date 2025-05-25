package dao;

import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UtenteDao {


    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save (Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();

    }

    public Utente getById(int id){
        return em.find(Utente.class, id );

    }

    public void update(Utente utente) {
        em.getTransaction().begin();
        em.merge(utente);
        em.getTransaction().commit();
    }

    public void remove(int id){
        Utente utente= getById(id);

        if(utente!=null){
            em.getTransaction().begin();
            em.remove(utente);
            em.getTransaction().commit();
        }
        else{
            System.out.println("l'utente " + utente + " non esiste");
        }
    }

    public Utente findByNumetoTessera(int numeroTessera) {
        try {
            return em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :num", Utente.class)
                    .setParameter("num", numeroTessera)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Utente> findAll() {
        return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
    }

    public boolean existsByNumeroTessera(int numeroTessera){
        Long count= em.createQuery("SELECT count (u) FROM Utente u WHERE u.numeroTessera = :num", Long.class)
                .setParameter("num", numeroTessera)
                .getSingleResult();
        return count >0;
    }

    }

