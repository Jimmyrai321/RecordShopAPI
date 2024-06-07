package jimmyrai321.recordShopAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.service.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import jimmyrai321.recordShopAPI.model.Album;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

    @Test
    @DisplayName("Get all albums if no param passed in url")
    void getAllAlbums() throws Exception {
        //ARRANGE
        Stock stock = new Stock(); stock.setStockCount(5);
        Stock stock2 = new Stock(); stock2.setStockCount(10);

        List<Album> testList = List.of(
                new Album(1L,"Loose","Nelly Furtado",2006, Genre.POP,"Loose is the third studio album by Canadian singer and songwriter Nelly Furtado, released on June 6th, 2006. The album is her most successful album to date, selling 12 million copies worldwide. The album's sound was very different than Furtado's previous works, having more of a Dance-pop/hip hop/R&B feel to it.",stock),
                new Album(2L,"Psychodrama","Dave",2019,Genre.HIPHOP,"ALBUM-INFO",stock2));


        when(albumService.getAllAlbums()).thenReturn(testList);


        //ACT
        var result = albumController.getAlbums(null);

        //ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Loose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Dave"));


        //ARRANGE
        when(albumService.getAlbumByName("Loose")).thenReturn(testList.getFirst());

        //ACT
        var result2 = albumController.getAlbums("Loose");

        //ASSERT
        mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums?name=Loose"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Loose"));
    }


}