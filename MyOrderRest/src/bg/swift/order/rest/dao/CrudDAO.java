package bg.swift.order.rest.dao;

public interface CrudDAO<T> {

    int insertNew(T t);
    int update(T t);
    boolean delete(T t);
    T getById(Integer id);
}
