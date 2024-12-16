package com.mballem.demo_spring_rev_jpa.repository;

import com.mballem.demo_spring_rev_jpa.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
