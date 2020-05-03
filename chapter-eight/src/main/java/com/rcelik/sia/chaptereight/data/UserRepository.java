package com.rcelik.sia.chaptereight.data;

import com.rcelik.sia.chaptereight.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserName(String userName);
}
