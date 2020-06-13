package com.rcelik.siafive.chaptereleven.data;

import com.rcelik.siafive.chaptereleven.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);
}
