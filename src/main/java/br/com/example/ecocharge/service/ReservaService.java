package br.com.example.ecocharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.Reserva;
import br.com.example.ecocharge.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Page<Reserva> findAll(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public Page<Reserva> findAllByStatusAndData(String status, String data, Pageable pageable) {
        return reservaRepository.findAllByStatusAndData(status, data, pageable);
    }

    public Page<Reserva> findAllByStatus(String status, Pageable pageable) {
        return reservaRepository.findAllByStatus(status, pageable);
    }

    public Page<Reserva> findAllByData(String data, Pageable pageable) {
        return reservaRepository.findAllByData(data, pageable);
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrada a reserva com o id: " + id));
    }

    public List<Reserva> findAllByUsuarioId(Long id) {
        return reservaRepository.findAllByUsuarioId(id);
    }

    public List<Reserva> findAllByPontoId(Long id) {
        return reservaRepository.findAllByPontoId(id);
    }

    public Reserva create(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public Reserva update(Long id, Reserva reserva) {
        verificarId(id);
        reserva.setId(id);
        return reservaRepository.save(reserva);
    }

    public void verificarId(Long id){
        reservaRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}
