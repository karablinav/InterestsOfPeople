package com.repositories;

import com.model.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UsersInfo, Long> {
    /*@Query("FROM UsersInfo ui where ui.user.name = :userName")*/
    UsersInfo findByUserId( Long userId);
}
