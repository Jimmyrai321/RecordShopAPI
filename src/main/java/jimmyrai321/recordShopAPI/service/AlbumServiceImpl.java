package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepo albumRepo;



    @Override
    public List<GetAlbumDto> getAllAlbums() {
        List<Album> albumList= new ArrayList<>();
        albumRepo.findAll().forEach(album -> {
            if (album.getStock().getStock_count()>0){
                albumList.add(album);
            }
        });
        return albumList.stream().map(a -> new GetAlbumDto(a.getId(),a.getName(),a.getArtist(),
                a.getRelease_year(),a.getGenre(),a.getAlbum_info(),a.getStock().getStock_count())).toList();
    }

    @Override
    public List<GetAlbumDto> getAlbumByName(String name) {

        List<Album> albumList= new ArrayList<>();
        albumRepo.findAll().forEach(albumList::add);
        List<Album> result = albumList.stream().filter(a -> a.getName().equalsIgnoreCase(name))
                .toList();
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[!] Album '" + name + "' not found in database! ");
        }

        return result.stream().map(a -> new GetAlbumDto(a.getId(),a.getName(),a.getArtist(),
                a.getRelease_year(),a.getGenre(),a.getAlbum_info(),a.getStock().getStock_count())).toList();
    }

    @Cacheable("cached")
    @Override
    public PostAlbumDto getAlbumByID(long id) {
        //System.out.println("Running repo"); //Testing cache
        Optional<Album> album = albumRepo.findById(id);
        if(album.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] No album exist at id: ("+ id+ ")!");
        }
        Album result = album.get();
        return new PostAlbumDto(result.getId(),result.getName(),result.getArtist(), result.getRelease_year(),result.getGenre(),result.getAlbum_info(),result.getStock().getStock_count());
    }

    @Override
    public PostAlbumDto addAlbum(PostAlbumDto postAlbumDto) {
        Album newAlbum = new Album();
        newAlbum.setName(postAlbumDto.getName());
        newAlbum.setArtist(postAlbumDto.getArtist());
        newAlbum.setRelease_year(postAlbumDto.getRelease_year());
        newAlbum.setGenre(postAlbumDto.getGenre());
        newAlbum.setAlbum_info(postAlbumDto.getAlbum_info());
        Stock newStock = new Stock();
        newStock.setId(newAlbum.getId());
        newStock.setAlbum(newAlbum);
        newStock.setStock_count(postAlbumDto.getStock_count());
        newAlbum.setStock(newStock);

        Album savedAlbum =albumRepo.save(newAlbum);

        PostAlbumDto albumResponse = new PostAlbumDto();
        albumResponse.setMessage("✔ Album added successfully");
        albumResponse.setId(savedAlbum.getId());
        albumResponse.setName(savedAlbum.getName());
        albumResponse.setArtist(savedAlbum.getArtist());
        albumResponse.setRelease_year(savedAlbum.getRelease_year());
        albumResponse.setGenre(savedAlbum.getGenre());
        albumResponse.setAlbum_info(savedAlbum.getAlbum_info());
        albumResponse.setStock_count(savedAlbum.getStock().getStock_count());
        return albumResponse;
    }


}
