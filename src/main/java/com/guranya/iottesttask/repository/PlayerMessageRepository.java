package com.guranya.iottesttask.repository;

import com.guranya.iottesttask.entity.PlayerMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerMessageRepository extends JpaRepository<PlayerMessage, Long> {
}
