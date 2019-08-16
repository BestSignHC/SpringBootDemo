package com.hecheng.repository;

import com.hecheng.domain.UserAlias;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserAliasRepository extends MongoRepository<UserAlias, String> {

    public List<UserAlias> findByAlias(String alias);

    public UserAlias findByMidAndAccount(String mid, String account);
}
