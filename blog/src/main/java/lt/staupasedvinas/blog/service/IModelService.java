package lt.staupasedvinas.blog.service;

import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchEntityException;

public interface IModelService<T> {
    void save(T t);

    void delete(T t);

    T findById(Long id) throws NoSuchEntityException;
}
