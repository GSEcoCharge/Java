package br.com.example.ecocharge.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posto_carregamento")
public class PostoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long posto_id;

    private String nome;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String endereco;

    private String horario_funcionamento;

    private String formas_pagamento;

    private BigDecimal avaliacao_media;

}
