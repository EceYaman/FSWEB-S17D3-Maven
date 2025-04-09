package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos = new HashMap<>();

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @PostMapping
    public ResponseEntity<Kangaroo> addKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null || kangaroo.getName() == null) {
            throw new ZooException("Invalid Kangaroo data", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        if (kangaroos.containsKey(id)) {
            kangaroos.put(id, kangaroo);
            return ResponseEntity.ok(kangaroo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kangaroo> deleteKangaroo(@PathVariable Integer id) {
        Kangaroo removedKangaroo = kangaroos.remove(id);
        if (removedKangaroo != null) {
            return ResponseEntity.ok(removedKangaroo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public List<Kangaroo> findAllKangaroos() {
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kangaroo> findKangarooById(@PathVariable("id") Integer id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroo == null) {
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(kangaroo);
    }
}
