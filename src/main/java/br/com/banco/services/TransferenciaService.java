package br.com.banco.services;

import br.com.banco.models.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<Transferencia> buscarTransferenciasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal);
    }

    public List<Transferencia> buscarTransferenciasDoOperador(String nomeOperadorTransacao) {
        return transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
    }


    public List<Transferencia> buscarTodosFiltros(LocalDateTime dataInicial, LocalDateTime dataFinal, String nomeOperadorTransacao) {
        return transferenciaRepository.findByFiltros(dataInicial, dataFinal, nomeOperadorTransacao);
    }
}
