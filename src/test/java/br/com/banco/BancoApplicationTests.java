package br.com.banco;

import br.com.banco.controllers.BancoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BancoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //MockMvc permite simular requisições HTTP
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarTransferenciasTest() throws Exception  {
        // Simular uma solicitação Get para "/"
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Verifique se a resposta HTTP é 200 OK
                .andExpect(content().contentType("application/json")) // Verifique se o tipo de conteúdo é JSON
                .andReturn();

    }
    @Test
    public void testBuscarTransferenciasPorPeriodo() {
        // Representar uma data e hora
        LocalDateTime dataInicial = LocalDateTime.parse("2023-09-01T00:00:00");
        LocalDateTime dataFinal = LocalDateTime.parse("2023-09-30T23:59:59");

        // Fazer chamadas em HTTP "http://localhost:8080/por-periodo?dataInicial=2019-01-01T00:00:00&dataFinal=2019-05-30T23:59:59
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/por-periodo?dataInicial={dataInicial}&dataFinal={dataFinal}",
                String.class,
                dataInicial,
                dataFinal
        );
        //Verifica se o getStatusCode = HttpStatus.Ok
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testBuscarTransferenciasDoOperador() throws Exception {
        // Realiza a solicitação GET para o endpoint com o parâmetro
        mockMvc.perform(MockMvcRequestBuilders.get("/operador")
                        .param("nomeOperadorTransacao", "Beltrano"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1)) // Verifica se o JSON tem um elemento
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeOperadorTransacao").value("Beltrano")); // Verifica se o Json tem o campo Beltrano
    }

}
