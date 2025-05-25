package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_id", nullable = false)
    private Consultabile elementoPrestato;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_stimata_restituzione")
    private LocalDate dataStimataDiRestituzione;

    @Column(name = "data_di_effettiva_restituzione")
    private LocalDate dataDiEffettivaRestituzione;


    public Prestito() {}


    public Prestito(Utente utente, Consultabile elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataStimataDiRestituzione = dataInizioPrestito.plusDays(30);
    }


    public Long getId() { return id; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Consultabile getElementoPrestato() { return elementoPrestato; }
    public void setElementoPrestato(Consultabile elementoPrestato) { this.elementoPrestato = elementoPrestato; }

    public LocalDate getDataInizioPrestito() { return dataInizioPrestito; }
    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataStimataDiRestituzione = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataStimataDiRestituzione() { return dataStimataDiRestituzione; }
    public void setDataStimataDiRestituzione(LocalDate dataStimataDiRestituzione) { this.dataStimataDiRestituzione = dataStimataDiRestituzione; }

    public LocalDate getDataDiEffettivaRestituzione() { return dataDiEffettivaRestituzione; }
    public void setDataDiEffettivaRestituzione(LocalDate dataDiEffettivaRestituzione) { this.dataDiEffettivaRestituzione = dataDiEffettivaRestituzione; }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataStimataDiRestituzione=" + dataStimataDiRestituzione +
                ", dataDiEffettivaRestituzione=" + dataDiEffettivaRestituzione +
                '}';
    }
}