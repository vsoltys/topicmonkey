package com.busybox.topicmonkey.service.impl;

import com.busybox.topicmonkey.domain.model.AbstractEntity;
import com.busybox.topicmonkey.domain.utils.SystemException;
import com.busybox.topicmonkey.service.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class TransactionalService<E extends AbstractEntity, K extends Serializable>
        implements AbstractService<E, K> {

    @Override
    @Transactional
    public E create(E entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(K id) {
        E entity = getRepository().findOne(id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E update(E entity) throws SystemException {
        E updated = getRepository().findOne((K) entity.getId());
        if (updated == null) {
            throw new SystemException("Error updating entity: " + entity);
        }

        getRepository().saveAndFlush(entity);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<E> delete(final K id) {
        final E deleted = getRepository().findOne(id);
        if (deleted == null) {
            return Optional.empty();
        }
//        deleted.onDelete();

        getRepository().saveAndFlush(deleted);
        return Optional.of(deleted);
    }

    protected abstract JpaRepository<E, K> getRepository();
}
