package jimmyrai321.recordShopAPI.controller;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.service.AlbumService;
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
    public ResponseEntity<Album> postAlbum(@RequestBody Album postedAlbum){
        Album addedAlbum = albumService.addAlbum(postedAlbum);
        return new ResponseEntity<>(addedAlbum,HttpStatus.CREATED);

    }

}
