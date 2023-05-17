package sezergemtsov.profiles.services;

import lombok.AllArgsConstructor;
import sezergemtsov.profiles.models.Message;
import sezergemtsov.profiles.repositories.Repository;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    private final Repository repository;

    public List<Message> getMessage() {
        return repository.findAll();
    }

    public void saveMessage(String message) {
        repository.save(Message.builder().message(message).build());
    }

}
