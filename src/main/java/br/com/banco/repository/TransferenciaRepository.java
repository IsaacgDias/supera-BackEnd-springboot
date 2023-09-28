package br.com.banco.repository;

import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);
}
