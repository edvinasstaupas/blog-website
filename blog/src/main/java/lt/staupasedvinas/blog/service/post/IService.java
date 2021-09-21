package lt.staupasedvinas.blog.service.post;

import lt.staupasedvinas.blog.exceptions.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    void save(T t);

    void delete(T t);

    T findById(Long id) throws NoSuchEntityException;

    Optional<T> find(Long id);

    List<T> findAll();

    Page<T> findAllPaginated(Pageable page);
}
