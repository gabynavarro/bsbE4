package com.bsb.ejercicio.service;

import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.mappers.CharacterMapper;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.repository.CharacterRepository;
import com.bsb.ejercicio.repository.GenderRepository;
import com.bsb.ejercicio.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    @Autowired
    private ICharacterService service;
    @MockBean
    private CharacterRepository repo;
    @Autowired
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

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Find by Name")
    void findName() throws ErrorProcessException {
        String name=DatosDummy.onlyCahracter().getName();
        when(repo.findByName(name)).thenReturn(Optional.ofNullable(DatosDummy.onlyCahracter()));
        response=service.findName(name);
        assertThat(response!=null).isTrue();
        assertEquals(response.getName(),name);
        assertEquals(name, response.getName());
        verify(repo).findByName(anyString());
    }

    @Test
    @DisplayName("Find all items")
    void getAll() throws ErrorProcessException {
        when(repo.findAll())
                .thenReturn(DatosDummy.addCharacter());
        List<CharacterResponse> charactes = repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        assertThat(charactes.size())
                .isEqualTo(7);

        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Find by age")
    void findByAge() throws ErrorProcessException {
        int age=45;
        when(repo.findByAge(anyInt())).thenReturn(DatosDummy.addCharacter());
        listResponse=service.findByAge(age);
        assertThat(listResponse!=null).isTrue();
        assertThat(listResponse.isEmpty()).isFalse();
        assertThat(listResponse.size()>0).isTrue();
        assertThat(listResponse.get(0).getAge()==age).isTrue();
        verify(repo).findByAge(anyInt());
    }
    @Test
    @DisplayName("Find by range age")
    void findByRangeAge() throws ErrorProcessException {
        int from=45;
        int to=52;
        when(repo.findByRangeAge(from,to)).thenReturn(DatosDummy.addCharacter());
        listResponse=service.findByRangeAge(from,to);
        assertThat(listResponse.size()>1).isTrue();
        assertThat(listResponse.get(1).getAge()>=from).isTrue();
        verify(repo).findByRangeAge(anyInt(),anyInt());
    }

    @Test
    @DisplayName("create character")
    void characterCreate() throws BadRequestException, ErrorProcessException {
        request=CharacterRequest.builder()
                .name("Gabriel")
                .age(52)
                .weight(60.3)
                .history(DatosDummy.descriptionCharacter[0])
                .listMovies(new ArrayList<>())
                .build();

        character1=DatosDummy.onlyCahracter();
        when(repo.save(any(Character.class))).thenReturn(character1);
        response=service.characterCreate(request);
        assertThat(response != null).isTrue();
        assertEquals(response.getName(),request.getName());
    }

    @Test
    @DisplayName("Find by id character")
    void findById() throws ErrorProcessException {
        character1=DatosDummy.onlyCahracter();
        when(repo.findById(character1.getId())).thenReturn(Optional.ofNullable(DatosDummy.onlyCahracter()));
        response=service.findById(character1.getId());
        assertNotNull(response);
        assertEquals(response.getId(),character1.getId());
        verify(repo).findById(anyLong());
    }

}