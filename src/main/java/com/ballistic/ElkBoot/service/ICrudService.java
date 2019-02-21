package com.ballistic.ElkBoot.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<R> {

    public void save(R r);

    public void save(List<R> r);

    public R update(R r);

    public Iterable<R> update(List<R> r);

    public Iterable<R> fetchAll();

    public Optional<R> findById(String uuId);

    public void deleteAll();
}
