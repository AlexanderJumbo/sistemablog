package com.blogapp.sistemablog.dto;

import com.blogapp.sistemablog.dto.Publications.PublicationResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublicationsPerCategorieResponse implements Serializable {

    List<PublicationResponse> publications; //= new ArrayList<>();

    private int numeroPagina;
    private int medidaPagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultima;


    public List<PublicationResponse> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationResponse> publications) {
        this.publications = publications;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getMedidaPagina() {
        return medidaPagina;
    }

    public void setMedidaPagina(int medidaPagina) {
        this.medidaPagina = medidaPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }
}
