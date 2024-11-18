
package br.com.example.ecocharge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.repository.AvaliacaoRepository;

class AvaliacaoServiceTest {

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Avaliacao> expectedPage = new PageImpl<>(List.of(new Avaliacao()));
        when(avaliacaoRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Avaliacao> result = avaliacaoService.findAll(pageable);

        assertEquals(expectedPage, result);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Avaliacao expectedAvaliacao = new Avaliacao();
        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(expectedAvaliacao));

        Avaliacao result = avaliacaoService.findById(id);

        assertEquals(expectedAvaliacao, result);
    }

    @Test
    void testCreate() {
        Avaliacao avaliacao = new Avaliacao();
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao result = avaliacaoService.create(avaliacao);

        assertEquals(avaliacao, result);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(avaliacaoRepository).deleteById(id);

        avaliacaoService.deleteById(id);

        verify(avaliacaoRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao result = avaliacaoService.update(id, avaliacao);

        assertEquals(avaliacao, result);
    }

    @Test
    void testFindAllByNotaAndData() {
        Integer nota = 5;
        LocalDate data = LocalDate.now();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Avaliacao> expectedPage = new PageImpl<>(List.of(new Avaliacao()));
        when(avaliacaoRepository.findAllByNotaAndData(nota, data, pageable)).thenReturn(expectedPage);

        Page<Avaliacao> result = avaliacaoService.findAllByNotaAndData(nota, data, pageable);

        assertEquals(expectedPage, result);
    }

}