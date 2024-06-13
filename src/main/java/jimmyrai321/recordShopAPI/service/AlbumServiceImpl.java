package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepo albumRepo;



    @Override
    public List<Album> getAllAlbums() {
        List<Album> albumList= new ArrayList<>();
        albumRepo.findAll().forEach(album -> {
            if (album.getStock().getStock_count()>0){
                albumList.add(album);
            }
        });
        //System.out.println(albumList);
        return albumList;
    }

    @Override
    public Album getAlbumByName(String name) {
        List<Album> albumList= new ArrayList<>();
        albumRepo.findAll().forEach(albumList::add);
        Optional<Album> result = albumList.stream().filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
        return result.orElse(null);
    }

    @Cacheable("cached")
    @Override
    public Album getAlbumByID(long id) {
        //System.out.println("Running repo"); //Testing cache
        Optional<Album> album = albumRepo.findById(id);
        return album.orElse(null);
    }

    @Override
    public Album addAlbum(Album newAlbum) {
        return albumRepo.save(newAlbum);
    }


}
