package entities;

import enumeration.Periodicita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("RIVISTA")

public class Rivista extends Consultabile{
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;



    public Rivista(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
        super(isbn, titolo, anno, pagine);
        this.periodicita = periodicita;
    }

    public Rivista(String isbn, String titolo, int anno, int pagine) {
        super(isbn, titolo, anno, pagine);
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String getTipo() {
        return "Rivista";
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}