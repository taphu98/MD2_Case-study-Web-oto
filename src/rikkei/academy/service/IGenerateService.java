package rikkei.academy.service;

import rikkei.academy.model.user.User;

import java.util.List;

public interface IGenerateService<E> {

    List<E> findAll();
    void save(E e);
    void updateData();

}
