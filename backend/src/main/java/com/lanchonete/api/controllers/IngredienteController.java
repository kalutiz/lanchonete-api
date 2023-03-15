package com.lanchonete.api.controllers;

import com.lanchonete.api.dto.IngredienteDTO;
import com.lanchonete.api.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService service;

    @GetMapping
    public ResponseEntity<Page<IngredienteDTO>> findAll(Pageable pageable) {
        Page<IngredienteDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable Long id) {
        IngredienteDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> insert(@Valid @RequestBody IngredienteDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IngredienteDTO> update(@PathVariable Long id, @Valid @RequestBody IngredienteDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
