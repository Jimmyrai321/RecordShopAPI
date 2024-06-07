package jimmyrai321.recordShopAPI.repository;

import jimmyrai321.recordShopAPI.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends CrudRepository<Album,Long> {
}
