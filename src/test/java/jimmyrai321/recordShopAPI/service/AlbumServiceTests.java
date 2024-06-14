package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class AlbumServiceTests {

    @Mock
    AlbumRepo mockAlbumRepo;

    @InjectMocks
    AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("GET all albums")
    void getAllAlbums() {
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        List<Album> result = albumServiceImpl.getAllAlbums();

        //ASSERT
        assertIterableEquals(testList,result);

    }

    @Test
    @DisplayName("GET all albums in stock")
    void getAlbumIfStock(){
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,0,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        List<Album> result = albumServiceImpl.getAllAlbums();
        testList.remove(1);

        //ASSERT
        assertIterableEquals(testList,result);
    }

    @Test
    @DisplayName("GET album by name")
    void getAlbumByName() {
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        Album result = albumServiceImpl.getAlbumByName("third avenue");

        //ASSERT
        assertEquals("Third Avenue",result.getName());
    }

    @Test
    @DisplayName("return null if name querying doesn't exist")
    void getAlbumByNameSAD(){
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));
        when(mockAlbumRepo.findAll()).thenReturn(testList);

        //ACT
        Album result = albumServiceImpl.getAlbumByName("fdjjjggj");

        //ASSERT
        assertNull(result);
    }

    @Test
    @DisplayName("GET album by id")
    void getAlbumByID() {
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));
        when(mockAlbumRepo.findById(1L)).thenReturn(Optional.ofNullable(testList.getFirst()));

        //ACT
        Album result = albumServiceImpl.getAlbumByID(1);

        //ASSERT
        assertEquals("Fredo",result.getArtist());
    }

    @Test
    @DisplayName("GET id that doesn't exist")
    void getAlbumByIDSAD() {
        //ARRANGE
        Album album1 = new Album(1L, "Third Avenue", "Fredo", 2019, Genre.HIPHOP, "This is a test album info!",null);
        Album album2 = new Album(2L, "Psychodrama", "Dave", 2019, Genre.HIPHOP, "This is a test Album Info!",null );
        album1.setStock(new Stock(1L,10,album1));
        album2.setStock(new Stock(2L,8,album2));
        List<Album> testList = new java.util.ArrayList<>(List.of(
                album1,album2));

        when(mockAlbumRepo.findById(1L)).thenReturn(Optional.ofNullable(testList.getFirst()));

        //ACT
        Album result = albumServiceImpl.getAlbumByID(3);

        //ASSERT
        assertNull(result);
    }

    @Test
    @DisplayName("POST new album")
    void addAlbum(){
        //ARRANGE
        Album album = new Album(1L,"PsychoDrama","Dave",2019,Genre.HIPHOP,"I am album info",null);
        //ACT
        albumServiceImpl.addAlbum(album);
        //ASSERT
        verify(mockAlbumRepo,times(1)).save(album);
    }

}