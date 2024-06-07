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

    private List<Album> albumList= new ArrayList<>();

    @Override
    public List<Album> getAllAlbums() {

        albumRepo.findAll().forEach(album -> {
            if (album.getStock().getStockCount()>0){
                albumList.add(album);
            }
        });
        return albumList;
    }

    @Override
    public Album getAlbumByName(String name) {

        albumRepo.findAll().forEach(album -> albumList.add(album));
        Optional<Album> result = albumList.stream().filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
        return result.orElse(null);
    }


}
