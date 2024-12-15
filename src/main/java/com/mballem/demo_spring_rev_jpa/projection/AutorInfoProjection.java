package com.mballem.demo_spring_rev_jpa.projection;

public class AutorInfoProjection {
    private String nomeCompleto;
    private String cargo;
    private String bio;

    public AutorInfoProjection(String nome, String sobrenome, String cargo, String bio){
        this.nomeCompleto = nome + " " + sobrenome;
        this.cargo = cargo;
        this.bio = bio;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getBio() {
        return bio;
    }

    public String getCargo() {
        return cargo;
    }


}
