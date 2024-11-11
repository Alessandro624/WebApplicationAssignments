package it.unical.demacs.informatica.ristoranti.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ristorante {
    protected String nome;
    protected String descrizione;
    protected String ubicazione;
    protected List<Piatto> piatti;

    public Ristorante(String nome, String descrizione, String ubicazione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ubicazione = ubicazione;
    }
}
