package jimmyrai321.recordShopAPI.controller;

import jakarta.validation.Valid;
import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.service.AlbumService;
import jimmyrai321.recordShopAPI.service.DTO.AlbumDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAlbums(@RequestParam (value = "name", required = false) String name){
        if (name == null){
            return new ResponseEntity<>(albumService.getAllAlbums(),HttpStatus.OK);
        }
        Album album = albumService.getAlbumByName(name);
        if (album == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] Album '"+name+"' not found in database! ");
        }
        return new ResponseEntity<>(List.of(albumService.getAlbumByName(name)),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumsByID(@PathVariable Long id){
        var result = albumService.getAlbumByID(id);
        if(result==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] No album exist at id: ("+ id+ ")!");
        }
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<AlbumDto> postAlbum(@Valid @RequestBody AlbumDto albumDto){

        Album newAlbum = new Album();
        newAlbum.setName(albumDto.getName());
        newAlbum.setArtist(albumDto.getArtist());
        newAlbum.setReleaseYear(albumDto.getReleaseYear());
        newAlbum.setGenre(albumDto.getGenre());
        newAlbum.setAlbumInfo(albumDto.getAlbumInfo());
        Stock newStock = new Stock();
        newStock.setId(newAlbum.getId());
        newStock.setAlbum(newAlbum);
        newStock.setStock_count(albumDto.getStock_count());
        newAlbum.setStock(newStock);
        Album savedAlbum = albumService.addAlbum(newAlbum);

        AlbumDto albumResponse = new AlbumDto();
        albumResponse.setMessage("✔ Album added successfully");
        albumResponse.setId(savedAlbum.getId());
        albumResponse.setName(savedAlbum.getName());
        albumResponse.setArtist(savedAlbum.getArtist());
        albumResponse.setReleaseYear(savedAlbum.getReleaseYear());
        albumResponse.setGenre(savedAlbum.getGenre());
        albumResponse.setAlbumInfo(savedAlbum.getAlbumInfo());
        albumResponse.setStock_count(savedAlbum.getStock().getStock_count());

        return new ResponseEntity<>(albumResponse,HttpStatus.CREATED);

    }

}
