package com.lanchonete.api.services;

import com.lanchonete.api.dto.IngredienteDTO;
import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.repositories.IngredienteRepository;
import com.lanchonete.api.services.exceptions.DataBaseException;
import com.lanchonete.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repository;

    @Transactional(readOnly = true)
    public Page<IngredienteDTO> findAllPaged(Pageable pageable) {
        Page<Ingrediente> list = repository.findAll(pageable);
        return list.map(x -> new IngredienteDTO(x));
    }

    @Transactional(readOnly = true)
    public IngredienteDTO findById(Long id) {
        Optional<Ingrediente> obj = repository.findById(id);
        Ingrediente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Ingrediente não encontrado"));
        return new IngredienteDTO(entity);
    }

    @Transactional
    public IngredienteDTO insert(IngredienteDTO dto) {
        Ingrediente entity = new Ingrediente();
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity = repository.save(entity);
        return new IngredienteDTO(entity);
    }

    @Transactional
    public List<IngredienteDTO> insertList(List<IngredienteDTO> dtos) {
        List<IngredienteDTO> dtoList = new ArrayList<>();
        for (IngredienteDTO dto : dtos) {
            Ingrediente entity = new Ingrediente();
            entity.setNome(dto.getNome());
            entity.setPreco(dto.getPreco());
            entity = repository.save(entity);
            dtoList.add(new IngredienteDTO(entity));
        }
        return dtoList;
    }

    @Transactional
    public IngredienteDTO update(Long id, IngredienteDTO dto) {
        try {
            Ingrediente entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity.setPreco(dto.getPreco());
            entity = repository.save(entity);
            return new IngredienteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("ID não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }
    }
}
