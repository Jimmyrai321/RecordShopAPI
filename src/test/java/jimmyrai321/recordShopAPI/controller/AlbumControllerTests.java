package jimmyrai321.recordShopAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.service.AlbumServiceImpl;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import jimmyrai321.recordShopAPI.model.Album;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    private static List<Album> getAlbumList() {
        Album album1 = new Album(1, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        return new java.util.ArrayList<>(List.of(
                album1,album2));
    }

    @Test
    @DisplayName("GET all albums if no param passed in url")
    void getAllAlbums() throws Exception {
        //ARRANGE
        List<Album> testList = getAlbumList();
        when(albumService.getAllAlbums()).thenReturn(testList);

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Loose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Dave"));

    }

    @Test
    @DisplayName("GET Album by query parameter")
    void getAlbumByName() throws Exception {
        //ARRANGE
        List<Album> testList = getAlbumList();
        when(albumService.getAlbumByName("Loose")).thenReturn(testList.getFirst());

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums?name=Loose"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Loose"));
    }

    @Test
    @DisplayName("GET album by id")
    void getAlbumByID() throws Exception {
        //ARRANGE
        List<Album> testList = getAlbumList();
        when(albumService.getAlbumByID(2)).thenReturn(testList.get(1));

        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Psychodrama"));
    }

    @Test
    @DisplayName("GET an id that doesn't exist should throw error")
    void getAlbumIDError() throws Exception {
        //ARRANGE
        when(albumService.getAlbumByID(3)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] No album exist at id: (3)!"));
        //ACT AND ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/id/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("POST new album")
    public void postJoke() throws Exception {
        //ARRANGE
        Album album =  new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "ALBUM-INFO", null);
        album.setStock(new Stock(2L,10,album));
        when(albumService.addAlbum(album)).thenReturn(album);

        //ACT
        mockMvcController.perform(MockMvcRequestBuilders.post("/api/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(album))) //serializes joke to json string so mvcController can accept and work with it.
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //ASSERT
        verify(albumService,times(1)).addAlbum(album);

    }




}