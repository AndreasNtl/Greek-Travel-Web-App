package dao;

import entities.Photo;
import java.util.List;

public interface PhotoDAO {

    public List<Photo> list();

    public List<Photo> pagination(int roomId, int offset, int recordPerPage);

    public void create(Photo ph);

    public Photo findPhotoById(int id);

    public String remove(int id);

    public Integer DeletePhoto(int id);

    public List<Photo> findPhotoByroomId(int roomId);

    public long findPhotosInRoom(int id);
}
