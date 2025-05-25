package dao;

import entities.Consultabile;
import entities.Prestito;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ArchivioDao {

    public EntityManager em;
    public ConsultabileDao consultabileDao;

    public ArchivioDao(EntityManager em, ConsultabileDao consultabileDao) {
        this.em = em;
        this.consultabileDao = consultabileDao;
    }

    public void save(Consultabile c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public void remove(String isbn){
        consultabileDao.deleteByIsbn(isbn);
    }

    public List<Consultabile> ricercaPerAnno (int anno){
        return em.createQuery("SELECT c FROM Consultabile c WHERE c.anno= :anno", Consultabile.class)
                .setParameter("anno", anno)
                .getResultList();

    }

    public List<Consultabile> ricercaPerAutore (String autore){
        return em.createQuery("SELECT c FROM Consultabile c WHERE c.autore= :autore", Consultabile.class)
                .setParameter("autore", autore)
                .getResultList();

    }
    public List<Consultabile> ricercaPerTitolo (String titolo){
        return em.createQuery("SELECT c FROM Consultabile c WHERE c.titolo LIKE '%:titolo%'", Consultabile.class)
                .setParameter("titolo", titolo)
                .getResultList();

    }

    public List<Consultabile> elementiInPrestito (int numeroTessera){
        return em.createQuery("SELECT c FROM Consultabile c" +
                        " INNER JOIN Prestito p ON p.elementoPrestato = c.isbn " +
                        " WHERE p.utente = :numeroTessera", Consultabile.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> prestitiScaduti(){
        return em.createQuery("SELECT p FROM Prestito p WHERE p.data_di_effettiva_restituzione is null AND p.dataStimataDiRestituzione < CURRENT_DATE",Prestito.class)
                .setParameter("prestitiScaduti", prestitiScaduti())
                .getResultList();
    }


}
