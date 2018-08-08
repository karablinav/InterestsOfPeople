package com.repositories;

import com.model.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UsersInfo, Long> {
    UsersInfo findByUserId( Long userId);
}
