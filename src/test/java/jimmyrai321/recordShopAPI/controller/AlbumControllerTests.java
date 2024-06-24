package jimmyrai321.recordShopAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.service.AlbumServiceImpl;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class AlbumControllerTests {

    @Mock
    private AlbumServiceImpl albumService;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    private static List<GetAlbumDto> getAlbumList() {
        GetAlbumDto album1 = new GetAlbumDto(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",10);
        GetAlbumDto album2 = new GetAlbumDto(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",8 );

        return new java.util.ArrayList<>(List.of(
                album1,album2));
    }

    @Test
    @DisplayName("GET all albums if no param passed in url")
    void getAllAlbums() throws Exception {
        //ARRANGE
        List<GetAlbumDto> testList = getAlbumList();
        when(albumService.getAllAlbums()).thenReturn(testList);

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Third Avenue"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Dave"));

    }

    @Test
    @DisplayName("GET Album by query parameter name")
    void getAlbumByName() throws Exception {
        //ARRANGE
        Map<String,String> testMap = new HashMap<>();
        testMap.put("Third Avenue","This is a test album info!");
        when(albumService.getAlbumInfoByName("Third Avenue")).thenReturn(testMap);

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums?name=Third Avenue"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("GET album by id")
    void getAlbumByID() throws Exception {
        //ARRANGE
        List<GetAlbumDto> testList = getAlbumList();
        when(albumService.getAlbumByID(2L)).thenReturn(testList.get(1));

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Psychodrama"));
    }

    @Test
    @DisplayName("GET an id that doesn't exist should throw error")
    void getAlbumIDError() throws Exception {
        //ARRANGE
        when(albumService.getAlbumByID(3L)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] No album exist at id: (3)!"));
        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/id/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("POST new album")
    public void postJoke() throws Exception {
        //ARRANGE
        PostAlbumDto album =  new PostAlbumDto(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "ALBUM-INFO", 5);
        album.setMessage("Successful");

        when(albumService.addAlbum(album)).thenReturn(album);
        //ACT
        mockMvcController.perform(MockMvcRequestBuilders.post("/api/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(album)) //serializes joke to json string so mvcController can accept and work with it.
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());





    }

    @Test
    @DisplayName("POST new album SAD")
    public void postJokeError() throws Exception {
        //ARRANGE
        PostAlbumDto album =  new PostAlbumDto(3L,"Psychodrama", "Dave", 2019, Genre.HIPHOP, "ALBUM-INFO", -5);


        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.post("/api/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(album))) //serializes joke to json string so mvcController can accept and work with it.
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}