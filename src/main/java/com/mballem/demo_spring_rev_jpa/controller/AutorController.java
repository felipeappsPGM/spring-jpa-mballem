package com.mballem.demo_spring_rev_jpa.controller;


import com.mballem.demo_spring_rev_jpa.dao.AutorDao;
import com.mballem.demo_spring_rev_jpa.model.Autor;
import com.mballem.demo_spring_rev_jpa.model.InfoAutor;
import com.mballem.demo_spring_rev_jpa.projection.AutorInfoProjection;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorDao autorDao;

    public AutorController(AutorDao autorDao){
        this.autorDao = autorDao;
    }

    @PostMapping
    public Autor salvar(@RequestBody Autor autor){
        autorDao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor){
        autorDao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id){
        autorDao.delete(id);
        return "Autor do " + id + " deletado com sucesso";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id){
        return autorDao.findById(id);
    }

    @GetMapping()
    public List<Autor> getAll(){
        return autorDao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAllByNomeOrSobrenome(@RequestParam String termo){
        return autorDao.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalAutores(){
        return autorDao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor){
        return autorDao.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getCargoAutor(@RequestParam String cargo){
        return autorDao.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection getAutorInfo(@RequestParam Long id){
        return autorDao.findAutorInfoById(id);
    }
}
