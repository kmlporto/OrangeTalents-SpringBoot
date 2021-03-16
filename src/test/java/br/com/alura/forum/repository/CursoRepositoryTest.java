package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void carregarUmCursoAoBuscarPeloSeuNome(){
        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        html5.setCategoria("Programação");
        entityManager.persist(html5);

        Curso curso = cursoRepository.findCursoByNome(nomeCurso);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }
    @Test
    public void naoCarregarUmCursoAoBuscarPeloSeuNome(){
        String nomeCurso = "JPA";
        Curso curso = cursoRepository.findCursoByNome(nomeCurso);
        Assertions.assertNull(curso);
    }
}
