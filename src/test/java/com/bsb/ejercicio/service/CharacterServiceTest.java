package com.bsb.ejercicio.service;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.mappers.CharacterMapper;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.repository.CharacterRepository;
import com.bsb.ejercicio.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@SpringBootTest
class CharacterServiceTest {
    @InjectMocks
    private CharacterServiceImpl service;
    @Mock
    private CharacterRepository repo;
    @Mock
    private CharacterMapper mapper;

    private  Character character1;

    private CharacterResponse response;
    private List<CharacterResponse> listResponse;
    private List<Character> list;
    private CharacterRequest request;
    @BeforeEach
    void setUp() {
      list = DatosDummy.addCharacter();
      list.forEach((c) -> repo.save(c));

 //   MockitoAnnotations.openMocks(this);
    character1=repo.findById(1L).orElse(null);

        request=new CharacterRequest();
        request.setName("Gabriel Navarro");
        request.setWeight(62.1);
        request.setAge(31);
        request.setHistory(DatosDummy.descriptionCharacter[0]);
      //  request.setListMovies(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findName() throws ErrorProcessException {
        when(repo.findByName("Robert Downey")).thenReturn(Optional.ofNullable(character1));

       // assertThat();
        assertEquals("Robert Downey", response.getName());
        //assertNotNull(service.findName("Robert-Downey"));

        //        assertEquals("Colombia", countries.get(0).getName());
      /*  BDDMockito.given(repo.findByName(character.getName()))
                .willReturn(Optional.of(character));
        //THEN
        assertThatThrownBy(() -> service.characterCreate(
                request.builder()
                        .name("Gabriel Navarro")
                        .age(37)
                        .history(DatosDummy.descriptionCharacter[0])
                        .weight(62.5)
                .build()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario existente");*/
    }

    @Test
    @DisplayName("Find all items")
    void getAll() throws ErrorProcessException {
        when(repo.findAll())
                .thenReturn(DatosDummy.addCharacter());
        //WHEN
        List<CharacterResponse> charactes = repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        //THEN
        assertThat(charactes.size())
                .isEqualTo(7);

        verify(repo, times(1)).findAll();
    }

    @Test
    void findByAge() {
    }

    @Test
    void findByRangeAge() {
    }

    @Test
    void characterCreate() throws BadRequestException, ErrorProcessException {

        CharacterResponse response=service.characterCreate(request);
        given(CharacterResponse.toEnty(response)).willReturn(character1);
        given((repo.save(CharacterResponse.toEnty(response)))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Assertions.assertNotNull(response);
        assertThat(response.getName())
                .isEqualTo(request.getName());
        assertThat(response.getId()!=null).isTrue();
    }

    @Test
    void findById() throws ErrorProcessException {

        CharacterResponse response = new CharacterResponse();
        Optional<Character> findById = Optional.of(DatosDummy.onlyCahracter());

        given(repo.findById(response.getId())).willReturn(findById);
        given(mapper.toResponse(findById.get())).willReturn(response);
    //    CharacterResponse findNews = service.findById(list.get(0).getId());
    //    Assertions.assertNotNull(findNews);

        verify(repo).findById(anyLong());
        verify(mapper).toResponse(any(Character.class));
    }

    @Test
    void update() {
    }


}