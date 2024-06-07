package jimmyrai321.recordShopAPI.service;

import jimmyrai321.recordShopAPI.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbums();

    Album getAlbumByName(String name);
}
