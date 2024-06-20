package jimmyrai321.recordShopAPI.controller;

import jakarta.validation.Valid;
import jimmyrai321.recordShopAPI.model.Genre;
import jimmyrai321.recordShopAPI.service.AlbumService;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PutAlbumDto;
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
    public ResponseEntity<?> getAlbums(@RequestParam (value = "name",required = false) String name,
                                       @RequestParam (value = "artist", required = false) String artist,
                                       @RequestParam(value = "release_year", required = false) Integer release_year,
                                       @RequestParam(value = "genre", required = false) String genre) {
        List<GetAlbumDto> getAlbumDtos;
        if(name!=null){
            return new ResponseEntity<>(albumService.getAlbumInfoByName(name),HttpStatus.OK);
        }else if(artist != null) {
            return new ResponseEntity<>(albumService.getAlbumsByArtist(artist),HttpStatus.OK);
        }else if(release_year != null){
            return new ResponseEntity<>(albumService.getAlbumsByRelease(release_year),HttpStatus.OK);
        }else if(genre != null){
            return new ResponseEntity<>(albumService.getAlbumsByGenre(Genre.valueOf(genre)),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(albumService.getAllAlbums(),HttpStatus.OK);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAlbumDto> getAlbumsByID(@PathVariable Long id){
        return new ResponseEntity<>(albumService.getAlbumByID(id),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<PostAlbumDto> postAlbum(@Valid @RequestBody PostAlbumDto postAlbumDto){
        PostAlbumDto addedAlbum = albumService.addAlbum(postAlbumDto);
        return new ResponseEntity<>(addedAlbum,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PutAlbumDto> putAlbum(@PathVariable Long id, @Valid @RequestBody PutAlbumDto body){
        return new ResponseEntity<>(albumService.updateAlbum(id,body),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id){
        return new ResponseEntity<>(albumService.removeAlbum(id),HttpStatus.OK);
    }



}
