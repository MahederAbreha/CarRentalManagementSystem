package edu.miu.cs489.apsd.carrentalsystem.service;

import java.util.List;

public interface UserService<T,U,R>{
    R createUser(T user);
    R updateUser(Long id, U user);
    void deleteUser(Long id);
    R getUserById(Long id);
    List<R> getAllUsers();

}
