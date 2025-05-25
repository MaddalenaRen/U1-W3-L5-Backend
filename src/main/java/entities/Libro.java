package entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Libro")

public class Libro extends Consultabile {
    private String autore;
    private String genere;

    public Libro() {
        super();
    }

    public Libro(String isbn, String titolo, int anno, int pagine, String autore, String genere) {
        super(isbn, titolo, anno, pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(String isbn, String titolo, int anno, int pagine) {
        super(isbn, titolo, anno, pagine);
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
    }
}
