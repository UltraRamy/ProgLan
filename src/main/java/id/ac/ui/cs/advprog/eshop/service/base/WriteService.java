package id.ac.ui.cs.advprog.eshop.service.base;

public interface WriteService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
