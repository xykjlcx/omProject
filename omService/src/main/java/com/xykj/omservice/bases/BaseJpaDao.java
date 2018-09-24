package com.xykj.omservice.bases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaDao<T,ID extends Serializable> extends JpaRepository<T,ID> {
}
