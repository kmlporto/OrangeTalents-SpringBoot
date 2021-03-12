package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.persist.TopicoPersist;
import br.com.alura.forum.controller.dto.response.TopicoDetailResponse;
import br.com.alura.forum.controller.dto.response.TopicoResponse;
import br.com.alura.forum.controller.dto.update.TopicoUpdate;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    @Cacheable(value = "listaTopicos")
    public Page<TopicoResponse> lista(
            @RequestParam(required = false) String nomeCurso,
            @PageableDefault(size = 20) Pageable pageable){
        Page<Topico> topicos;
        if(nomeCurso == null){
            topicos = topicoRepository.findAll(pageable);
        }else{
            topicos = topicoRepository.findAllByCursoNome(nomeCurso, pageable);
        }
        return TopicoResponse.converter(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetailResponse> consulta(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return new ResponseEntity<>(new TopicoDetailResponse(topico.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoResponse> cadastra(@RequestBody @Valid TopicoPersist topicoPersist){
        Topico topico = topicoRepository.save(topicoPersist.converter(cursoRepository));
        TopicoResponse topicoResponse = TopicoResponse.converter(topico);
        return new ResponseEntity<>(topicoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<TopicoResponse> atualiza(@PathVariable Long id, @RequestBody @Valid TopicoUpdate topicoUpdate){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            Topico topico = topicoUpdate.atualizar(id, topicoRepository);
            topico = topicoRepository.save(topico);
            return new ResponseEntity<>(new TopicoResponse(topico), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaTopicos", allEntries = true)
    public ResponseEntity<?> deleta(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
