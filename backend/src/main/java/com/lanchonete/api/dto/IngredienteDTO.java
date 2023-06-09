package com.lanchonete.api.dto;

import com.lanchonete.api.entities.Ingrediente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class IngredienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 3, max = 30, message = "Deve ter entre 3 a 30 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Positive(message = "O preço deve ser positivo")
    private Double preco;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public IngredienteDTO(Ingrediente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.preco = entity.getPreco();
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
