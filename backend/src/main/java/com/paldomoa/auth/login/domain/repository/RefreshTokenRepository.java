package com.paldomoa.auth.login.domain.repository;

import com.paldomoa.auth.login.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
