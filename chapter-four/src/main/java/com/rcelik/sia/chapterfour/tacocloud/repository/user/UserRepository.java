package com.rcelik.sia.chapterfour.tacocloud.repository.user;


import com.rcelik.sia.chapterfour.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
