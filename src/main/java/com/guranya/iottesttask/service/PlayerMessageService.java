package com.guranya.iottesttask.service;

import com.guranya.iottesttask.entity.PlayerMessage;
import com.guranya.iottesttask.repository.PlayerMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerMessageService {
    private final PlayerMessageRepository repository;

    @Transactional
    public void save(PlayerMessage message) {
        repository.save(message);
    }

}
