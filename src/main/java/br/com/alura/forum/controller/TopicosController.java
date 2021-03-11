package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.persist.TopicoPersist;
import br.com.alura.forum.controller.dto.response.TopicoResponse;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicoRepository topicoRepository;

    private final CursoRepository cursoRepository;

    public TopicosController(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoResponse> lista(String nomeCurso){
        List<Topico> topicos;
        if(nomeCurso == null){
            topicos = topicoRepository.findAll();
        }else{
            topicos = topicoRepository.findAllByCursoNome(nomeCurso);
        }
        return TopicoResponse.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoResponse> cadastra(@RequestBody TopicoPersist topicoPersist){
        Topico topico = topicoRepository.save(topicoPersist.converter(cursoRepository));
        TopicoResponse topicoResponse = TopicoResponse.converter(topico);
        return new ResponseEntity<>(topicoResponse, HttpStatus.CREATED);
    }
}
