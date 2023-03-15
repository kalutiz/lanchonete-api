package com.lanchonete.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_lanche")
public class Lanche implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_lanche_ingrediente",
            joinColumns = @JoinColumn(name = "lanche_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Lanche() {
    }

    public Lanche(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
