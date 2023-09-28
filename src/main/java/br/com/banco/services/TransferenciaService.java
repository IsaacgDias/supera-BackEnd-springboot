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
    //Between = SELECT * FROM tabela WHERE coluna BETWEEN valor_minimo AND valor_maximo; usado para verificar se algo estar dentro de um intervalo
    public List<Transferencia> buscarTransferenciasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal);
    }
}
