package com.mballem.demo_spring_rev_jpa.services;

import com.mballem.demo_spring_rev_jpa.model.Autor;
import com.mballem.demo_spring_rev_jpa.model.InfoAutor;
import com.mballem.demo_spring_rev_jpa.projection.AutorInfoProjection;
import com.mballem.demo_spring_rev_jpa.repository.AutorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor){
        this.autorRepository.save(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor){
        this.autorRepository.save(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        this.autorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id){
//        return this.autorRepository.findById(id).get();
//        return this.autorRepository.findById(id).orElseGet(() -> new Autor());
        return this.autorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Autor id=" + id +" não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        return this.autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobrenome(String termo){
        String query = "select a from Autor a where a.nome like :termo OR a.sobrenome like :termo";
        return this.manager.createQuery(query, Autor.class)
                .setParameter("termo","%" + termo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Long getTotalElements(){
        return this.autorRepository.count();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId){
        Autor autor = findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo){
        String query = """
                select a from Autor a
                where a.infoAutor.cargo like: cargo
                order by a.nome asc
                """;
        return this.manager.createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id){
        String query = """
                select new AutorInfoProjection(a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)
                from Autor a
                where a.id like: id
                """;
        return this.manager.createQuery(query, AutorInfoProjection.class)
                .setParameter("id", "%" + id + "%")
                .getSingleResult();
    }

}
