package jimmyrai321.recordShopAPI.controller;

import jakarta.validation.Valid;
import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.service.AlbumService;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PutAlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<GetAlbumDto>> getAlbums(@RequestParam (value = "name", required = false) String name){
      List<GetAlbumDto> getAlbumDtos;
        if (name == null){
            getAlbumDtos = albumService.getAllAlbums();
        }else {
            getAlbumDtos = albumService.getAlbumByName(name);
        }
        return new ResponseEntity<>(getAlbumDtos,HttpStatus.OK);

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

}
