package br.com.banco.repository;

import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    //Between = SELECT * FROM tabela WHERE coluna BETWEEN valor_minimo AND valor_maximo; usado para verificar se algo estar dentro de um intervalo
    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

    // Consulta JPQL
    //@Param para mapear parâmetros de consultas personalizadas
    @Query("SELECT t FROM Transferencia t WHERE " +
            "t.dataTransferencia BETWEEN :dataInicial AND :dataFinal " +
            "AND t.nomeOperadorTransacao = :nomeOperadorTransacao")
    List<Transferencia> findByFiltros(@Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal, @Param("nomeOperadorTransacao") String nomeOperadorTransacao);

}
