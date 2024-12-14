package com.mballem.demo_spring_rev_jpa.controller;


import com.mballem.demo_spring_rev_jpa.dao.AutorDao;
import com.mballem.demo_spring_rev_jpa.model.Autor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
