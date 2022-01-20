package org.panchuk.app.repository;

import org.panchuk.app.entity.Dot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DotRepository extends JpaRepository <Dot, Long> {

    @Override
    <S extends Dot> S save(S entity);
    @Override
    List<Dot> findAll();
}
