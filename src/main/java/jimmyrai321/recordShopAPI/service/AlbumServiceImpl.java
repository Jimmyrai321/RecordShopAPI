package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;
import jimmyrai321.recordShopAPI.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
            if (album.getStock().getStockCount()>0){
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

    @Override
    public Album getAlbumByID(long id) {
        Optional<Album> album = albumRepo.findById(id);
        return album.orElse(null);
    }


}
