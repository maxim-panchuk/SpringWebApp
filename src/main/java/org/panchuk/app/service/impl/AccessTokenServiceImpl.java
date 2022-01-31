package org.panchuk.app.service.impl;

import org.panchuk.app.entity.AccessToken;
import org.panchuk.app.repository.AccessTokenRepository;
import org.panchuk.app.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AccessTokenServiceImpl implements AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Autowired
    public AccessTokenServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public void addAccessToken(AccessToken accessToken) {
        accessTokenRepository.saveAndFlush(accessToken);
    }
}
