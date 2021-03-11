package br.com.alura.forum.controller.dto.response;

import br.com.alura.forum.modelo.Resposta;

import java.time.LocalDateTime;

public class RespostaResponse {
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaResponse(Resposta resposta) {
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
