package com.bsb.ejercicio.service;

import com.bsb.ejercicio.datos.CharacterServiceDataUtils;
import com.bsb.ejercicio.datos.DatosDummy;
import com.bsb.ejercicio.exception.BadRequestException;
import com.bsb.ejercicio.exception.ErrorProcessException;
import com.bsb.ejercicio.model.entity.Character;
import com.bsb.ejercicio.model.entity.Movie;
import com.bsb.ejercicio.model.mappers.CharacterMapper;
import com.bsb.ejercicio.model.request.CharacterRequest;
import com.bsb.ejercicio.model.response.character.CharacterResponse;
import com.bsb.ejercicio.repository.CharacterRepository;
import com.bsb.ejercicio.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {
    @Mock
    public CharacterRepository repo;
   @InjectMocks
    public CharacterServiceImpl service;

   @Autowired
   public  CharacterMapper mapper;

    public  Character character1;
    public CharacterResponse response;
    public List<CharacterResponse> listResponse;
    public List<Character> list;
    public CharacterRequest request;
    @BeforeEach
    void setUp() {
      MockitoAnnotations.openMocks(this);
      list = DatosDummy.addCharacter();
      list.forEach((c) -> repo.save(c));


        //  when(service.findById(anyLong())).thenReturn(response);
    character1=new Character();
    character1.setName(DatosDummy.nameCharater[0]);
    character1.setWeight(65.5);
    character1.setAge(63);
    character1.setHistory(DatosDummy.descriptionCharacter[0]);
    character1.setListMovie(new ArrayList<>());
    character1.setSoftDeleted(false);
   /****** Request *********/


    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void characterCreate() throws BadRequestException, ErrorProcessException {
        response=new CharacterResponse();
        request=new CharacterRequest();
        request.setName("GabrielNavarro");
        request.setWeight(62.1);
        request.setAge(31);
        request.setHistory(DatosDummy.descriptionCharacter[0]);
        request.setListMovies(Arrays.asList());
    //    Character c=mapper.toCharacter(request);
        when(service.characterCreate(request)).thenReturn(CharacterServiceDataUtils.getCharacterResponse());
        assertNotNull(response);
    }
    @Test
    void findName() throws ErrorProcessException {


    }

    @Test
    @DisplayName("Find all items")
    void getAll() throws ErrorProcessException {
        when(repo.findAll())
                .thenReturn(DatosDummy.addCharacter());
        List<CharacterResponse> charactes = repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());        //THEN
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
    void findById() throws ErrorProcessException {

    }

    @Test
    void update() {
    }


}