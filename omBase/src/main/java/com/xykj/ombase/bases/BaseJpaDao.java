package com.xykj.ombase.bases;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseJpaDao<T,ID extends Serializable> extends JpaRepository<T,ID> {
}
