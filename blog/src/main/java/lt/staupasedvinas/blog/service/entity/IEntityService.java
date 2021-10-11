package lt.staupasedvinas.blog.service.entity;

import lt.staupasedvinas.blog.exceptions.entity.nosuch.NoSuchEntityException;

public interface IEntityService<T> {
    void save(T t);

    void delete(T t);

    T findById(Long id) throws NoSuchEntityException;
}
