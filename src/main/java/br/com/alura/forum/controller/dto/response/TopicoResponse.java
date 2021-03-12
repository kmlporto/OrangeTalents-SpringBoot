package br.com.alura.forum.controller.dto.response;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCricao;

    public TopicoResponse(Topico topico) {
        id = topico.getId();
        titulo = topico.getTitulo();
        mensagem = topico.getMensagem();
        dataCricao = topico.getDataCriacao();
    }

    public static Page<TopicoResponse> converter(Page<Topico> topicos){
        return topicos.map(TopicoResponse::new);
    }

    public static TopicoResponse converter(Topico topico){
        return new TopicoResponse(topico);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }
}
