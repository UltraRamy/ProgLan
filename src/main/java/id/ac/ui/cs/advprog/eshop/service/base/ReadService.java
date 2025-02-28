package id.ac.ui.cs.advprog.eshop.service.base;
import java.util.List;

public interface ReadService<T, ID> {
    List<T> findAll();
    T findById(ID id);
}
