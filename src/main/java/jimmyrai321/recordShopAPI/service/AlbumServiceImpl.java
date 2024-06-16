package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.model.Stock;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import jimmyrai321.recordShopAPI.service.DTO.GetAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PostAlbumDto;
import jimmyrai321.recordShopAPI.service.DTO.PutAlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
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
    public GetAlbumDto getAlbumByID(long id) {
        //System.out.println("Running repo"); //Testing cache
        Optional<Album> album = albumRepo.findById(id);
        if(album.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] No album exist at id: ("+ id+ ")!");
        }
        Album result = album.get();
        return new GetAlbumDto(result.getId(),result.getName(),result.getArtist(), result.getRelease_year(),result.getGenre(),result.getAlbum_info(),result.getStock().getStock_count());
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

    @Override
    public PutAlbumDto updateAlbum(Long id, PutAlbumDto updateData) {
        Album album;
        if (albumRepo.existsById(id)) {
            album = albumRepo.findById(id).get();
            if (updateData.getId() != null) {
                album.setId(updateData.getId());
            }
            if (updateData.getName() != null) {
                album.setName(updateData.getName());
            }
            if (updateData.getArtist() != null) {
                album.setArtist(updateData.getArtist());
            }
            if (updateData.getAlbum_info() != null) {
                album.setAlbum_info(updateData.getAlbum_info());
            }
            if (updateData.getGenre() != null) {
                album.setGenre(updateData.getGenre());
            }
            if (updateData.getRelease_year() != null) {
                album.setRelease_year(updateData.getRelease_year());
            }
            if (updateData.getStock_count() != null) {
                album.getStock().setStock_count(updateData.getStock_count());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"[!] Album at id: "+id+" doesn't exist!");
        }
        albumRepo.save(album);

        return new PutAlbumDto(album.getId(), album.getName(), album.getArtist(), album.getRelease_year(), album.getGenre(),
                album.getAlbum_info(), album.getStock().getStock_count(), "✔ Updated Album id: " + id + " successfully!");
    }

}
