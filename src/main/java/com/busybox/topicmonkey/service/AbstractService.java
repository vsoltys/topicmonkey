package com.busybox.topicmonkey.service;

import com.busybox.topicmonkey.domain.utils.SystemException;
import com.busybox.topicmonkey.domain.model.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractService<E extends AbstractEntity, K extends Serializable> {

    E create(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

    E update(E entity) throws SystemException;

    Optional<E> delete(K id);
}
