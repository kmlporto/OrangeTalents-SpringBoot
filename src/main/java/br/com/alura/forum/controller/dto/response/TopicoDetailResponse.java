package br.com.alura.forum.controller.dto.response;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDetailResponse {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCricao;
    private String autor;
    private StatusTopico status;
    private List<RespostaResponse> respostas;

    public TopicoDetailResponse(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCricao = topico.getDataCriacao();
        this.autor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaResponse::new).collect(Collectors.toList()));
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

    public String getAutor() {
        return autor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaResponse> getRespostas() {
        return respostas;
    }
}
