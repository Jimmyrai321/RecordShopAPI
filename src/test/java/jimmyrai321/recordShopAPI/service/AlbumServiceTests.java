package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumServiceTests {

    @Mock
    AlbumRepo mockAlbumRepo;

    @InjectMocks
    AlbumServiceImpl albumServiceImpl;

    @Test
    void getAllAlbums() {
        //ARRANGE
        Stock stock1 = new Stock(); Stock stock2 = new Stock();
        stock1.setStockCount(10); stock2.setStockCount(8);

        List<Album> testList = new java.util.ArrayList<>(List.of(
                new Album(1, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!", stock1),
                new Album(2, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!", stock2)
        ));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        List<Album> result = albumServiceImpl.getAllAlbums();

        //ASSERT
        assertIterableEquals(testList,result);

        //-------TEST CASE 2------
        //ARRANGE
        stock2.setStockCount(0);

        //ACT
        List<Album> result2 = albumServiceImpl.getAllAlbums();
        testList.remove(1);

        //ASSERT

        assertIterableEquals(testList,result2);
    }

    @Test
    void getAlbumByName() {
        //ARRANGE
        Stock stock1 = new Stock(); Stock stock2 = new Stock();
        stock1.setStockCount(10); stock2.setStockCount(8);

        List<Album> testList = new java.util.ArrayList<>(List.of(
                new Album(1, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!", stock1),
                new Album(2, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!", stock2)
        ));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        Album result = albumServiceImpl.getAlbumByName("third avenue");

        //ASSERT
        assertEquals("Third Avenue",result.getName());

        //-------TEST CASE 2 --------

        //ARRANGE

        //ACT
        Album result2 = albumServiceImpl.getAlbumByName("fdjjjggj");

        //ASSERT
        assertNull(result2);
    }

    @Test
    void getAlbumByID() {
        //ARRANGE
        Stock stock1 = new Stock(); Stock stock2 = new Stock();
        stock1.setStockCount(10); stock2.setStockCount(8);

        List<Album> testList = new java.util.ArrayList<>(List.of(
                new Album(1, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!", stock1),
                new Album(2, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!", stock2)
        ));
        when(mockAlbumRepo.findById(1L)).thenReturn(Optional.ofNullable(testList.getFirst()));

        //ACT
        Album result = albumServiceImpl.getAlbumByID(1);

        //ASSERT
        assertEquals("Fredo",result.getArtist());


        // ------TEST CASE 2 --------
        //ARRANGE

        //ACT
        Album result2 = albumServiceImpl.getAlbumByID(3);

        //ASSERT
        assertNull(result2);
    }
}