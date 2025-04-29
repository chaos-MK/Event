package com.bib.app.service;

import com.bib.app.entities.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final RedisTemplate<String, Participant> redisTemplate;

    @Autowired
    public ParticipantService(RedisTemplate<String, Participant> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public Optional<Participant> getParticipant(String eventId, String email) {
        Participant participant = redisTemplate.opsForValue().get("event:" + eventId + ":participant:" + email);
        return Optional.ofNullable(participant);
    }


    public void removeParticipant(String eventId, String email) {
        String key = "event:" + eventId + ":participant:" + email;
        redisTemplate.delete(key);
        redisTemplate.opsForSet().remove("event:" + eventId + ":participants", email);
    }
}