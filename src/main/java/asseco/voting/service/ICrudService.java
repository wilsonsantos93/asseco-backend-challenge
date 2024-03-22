package asseco.voting.service;

import java.util.List;

public interface ICrudService<T> {
    List<T> all();
    T create(T data);
    T getById(int id);
    T update(int id, T data);
    void delete(int id);
}
