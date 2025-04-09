package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas = new HashMap<>();

    @GetMapping
    public List<Koala> getAllKoalas() {
        return koalas.values().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Koala getKoala(@PathVariable Integer id) {
        Koala koala = koalas.get(id);
        if (koala == null) {
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return koala;
    }

    @PostMapping
    public ResponseEntity<Koala> addKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return ResponseEntity.ok(koala); // Return the saved koala
    }

    @PutMapping("/{id}")
    public ResponseEntity<Koala> updateKoala(@PathVariable Integer id, @RequestBody Koala koala) {
        if (koalas.containsKey(id)) {
            koalas.put(id, koala);
            return ResponseEntity.ok(koala);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable Integer id) {
        koalas.remove(id);
    }
}
