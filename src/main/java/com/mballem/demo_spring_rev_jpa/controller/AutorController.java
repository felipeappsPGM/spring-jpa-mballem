package com.mballem.demo_spring_rev_jpa.controller;


import com.mballem.demo_spring_rev_jpa.dao.AutorDao;
import com.mballem.demo_spring_rev_jpa.model.Autor;
import com.mballem.demo_spring_rev_jpa.model.InfoAutor;
import com.mballem.demo_spring_rev_jpa.projection.AutorInfoProjection;
import com.mballem.demo_spring_rev_jpa.services.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public Autor salvar(@RequestBody Autor autor){
        autorService.save(autor);
        return autor;
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor){
        autorService.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id){
        autorService.delete(id);
        return "Autor do " + id + " deletado com sucesso";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id){
        return autorService.findById(id);
    }

    @GetMapping()
    public List<Autor> getAll(){
        return autorService.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAllByNomeOrSobrenome(@RequestParam String termo){
        return autorService.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalAutores(){
        return autorService.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor){
        return autorService.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getCargoAutor(@RequestParam String cargo){
        return autorService.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection getAutorInfo(@RequestParam Long id){
        return autorService.findAutorInfoById(id);
    }
}
