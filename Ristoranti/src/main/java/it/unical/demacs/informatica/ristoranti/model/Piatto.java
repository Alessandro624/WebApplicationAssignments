package it.unical.demacs.informatica.ristoranti.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Piatto {
    protected String nome;
    protected String ingredienti;
    protected List<Ristorante> ristoranti;

    public Piatto(String nome, String ingredienti) {
        this.nome = nome;
        this.ingredienti = ingredienti;
    }
}
