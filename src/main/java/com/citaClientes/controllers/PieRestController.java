package com.citaClientes.controllers;

import com.citaClientes.models.Pie;
import com.citaClientes.repositories.PieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by kaspernissen on 11/02/2016.
 */
@RestController
@RequestMapping("/pies")
public class PieRestController {

    @Autowired
    private PieRepository repository;

    //Devuelve todos los elementos

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Pie>> getAllPies() {
        return new ResponseEntity<>((Collection<Pie>) repository.findAll(), HttpStatus.OK);
    }

    //Devuelve un element por ID

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Pie> getPieWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    //Busca un elemento por el nombre, y lo devuelve

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<Collection<Pie>> findPieWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    //Crea un elemento con los datos dados

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPie(@RequestBody Pie input) {
        return new ResponseEntity<>(repository.save(input), HttpStatus.CREATED);
    }

    //Actualiza los datos de un elmento

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updatePie(@PathVariable Long id, @RequestBody Pie pie) {
        if (repository.findOne(id) != null) {
            //TO-DO
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Elimina un elmento

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deletePie(@PathVariable Long id) {
        if (repository.findOne(id) != null) {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Elimina todos los elementos

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllPie() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
