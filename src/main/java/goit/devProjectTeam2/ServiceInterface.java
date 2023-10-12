package goit.devProjectTeam2;

import java.util.List;

public interface ServiceInterface<T> {

    T getById(Long id);

    T add(T entity);

    void deleteById(Long id);

    List<T> listAll();

}
