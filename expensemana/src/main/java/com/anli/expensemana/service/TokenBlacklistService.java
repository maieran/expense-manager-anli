package com.anli.expensemana.service;

public interface TokenBlacklistService {

    public void blacklist(String token);
    public boolean isBlacklisted(String token);
}
