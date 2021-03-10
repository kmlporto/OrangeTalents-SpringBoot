# OrangeTalents-SpringBoot

### Módulo 1 - Introdução ao String Boot
* para facilitar a inicialização de um projeto Spring pode-se utilizar o initializr https://start.spring.io/
* agora podemos criar um controller, anotamos com @Controller;
* podemos mapear um controller com o @RequestMappin;

### Módulo 2 - Publicando endpoints
* @ResponseBody - indica que o retorno do método deve ser serializado e devolvido no corpo da resposta;
* @RestController - indica que é um controller rest, dessa forma não precisamos usar o @ResponseBody em todos os métodos;
* para facilitar nossa vida podemos adicionar o módulo do Spring Boot, o DevTools, uma dos benefícios ao usá-lo é não precisar ficar reiniciando a aplicação toda vez que é feita uma alteração no código;
* Data Transfer Object(DTO) - Objetos usados para transferência de dados, muito útil quando não queremos exibir todos os dados de uma entidade, ou quando queremos exibir dados a mais;
* agora sempre que vamos devolver ou receber dados para o usuário vamos fazer usos de dto's;
