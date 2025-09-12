package com.example.homework2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractCrudController<T, I> {

 protected final JpaRepository<T, I> repository;

 protected AbstractCrudController(JpaRepository<T, I> repository) {
  this.repository = repository;
 }

 @PostMapping
 public ResponseEntity<T> create(@RequestBody T entity) {
  try {
   T savedEntity = repository.save(entity);
   return ResponseEntity.ok(savedEntity);
  } catch (Exception e) {
   return ResponseEntity.badRequest().build();
  }
 }

 @GetMapping
 public ResponseEntity<List<T>> getAll() {
  List<T> entities = repository.findAll();
  return ResponseEntity.ok(entities);
 }

 @GetMapping("/{id}")
 public ResponseEntity<T> getById(@PathVariable I id) {
  Optional<T> entity = repository.findById(id);
  return entity.map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> delete(@PathVariable I id) {
  try {
   if (repository.existsById(id)) {
    repository.deleteById(id);
    return ResponseEntity.ok().build();
   } else {
    return ResponseEntity.notFound().build();
   }
  } catch (Exception e) {
   return ResponseEntity.badRequest().body(e.getMessage());
  }
 }
}
