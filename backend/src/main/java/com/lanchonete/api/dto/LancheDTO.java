package com.lanchonete.api.dto;

import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.entities.Lanche;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LancheDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private List<IngredienteDTO> ingredientes = new ArrayList<>();

    public LancheDTO() {
    }

    public LancheDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public LancheDTO(Lanche entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }

    public LancheDTO(Lanche entity, List<Ingrediente> ingredientes) {
        this(entity);
        ingredientes.forEach(ingrediente -> this.ingredientes.add(new IngredienteDTO(ingrediente)));
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

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
