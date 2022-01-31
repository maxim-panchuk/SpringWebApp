package org.panchuk.app.service;

import org.panchuk.app.entity.AccessToken;
import org.springframework.stereotype.Service;

@Service
public interface AccessTokenService {
    void addAccessToken(AccessToken accessToken);
}
