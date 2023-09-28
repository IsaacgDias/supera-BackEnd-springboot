package br.com.banco.controllers;

import br.com.banco.models.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

//@RestController retorna em Json
@RestController
@RequestMapping("/")
public class BancoController {
    //Injetar Instâncias
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TransferenciaService transferenciaService;


    // Começa com a lista de todas as transferências
    @GetMapping()
    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }

    //@RequestParam("dataInical") para extrair parâmetros da URL
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) para especificar o formato esperado para as datas com o formato ISO 8601
    @GetMapping("/por-periodo")
    public List<Transferencia> buscarTransferênciasPorPeriodo(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return transferenciaService.buscarTransferenciasPorPeriodo(dataInicial, dataFinal);
    }


}
