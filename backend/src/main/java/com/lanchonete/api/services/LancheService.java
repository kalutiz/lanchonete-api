package com.lanchonete.api.services;

import com.lanchonete.api.dto.IngredienteDTO;
import com.lanchonete.api.dto.LancheDTO;
import com.lanchonete.api.entities.Ingrediente;
import com.lanchonete.api.entities.Lanche;
import com.lanchonete.api.repositories.IngredienteRepository;
import com.lanchonete.api.repositories.LancheRepository;
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
import java.util.Optional;

@Service
public class LancheService {

    @Autowired
    private LancheRepository repository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional(readOnly = true)
    public Page<LancheDTO> findAllPaged(Pageable pageable) {
        Page<Lanche> list = repository.findAll(pageable);
        return list.map(x -> new LancheDTO(x, x.getIngredientes()));
    }

    @Transactional(readOnly = true)
    public LancheDTO findById(Long id) {
        Optional<Lanche> obj = repository.findById(id);
        Lanche entity = obj.orElseThrow(() -> new ResourceNotFoundException("Lanche não encontrado"));
        return new LancheDTO(entity, entity.getIngredientes());
    }

    @Transactional
    public LancheDTO insert(LancheDTO dto) {
        Lanche entity = new Lanche();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new LancheDTO(entity, entity.getIngredientes());
    }

    @Transactional
    public LancheDTO update(Long id, LancheDTO dto) {
        try {
            Lanche entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new LancheDTO(entity, entity.getIngredientes());
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

    private void copyDtoToEntity(LancheDTO dto, Lanche entity) {
        entity.setNome(dto.getNome());

        entity.getIngredientes().clear();
        for (IngredienteDTO ingredienteDTO : dto.getIngredientes()) {
            Ingrediente ingrediente = ingredienteRepository.getReferenceById(ingredienteDTO.getId());
            entity.getIngredientes().add(ingrediente);
        }
    }
}
