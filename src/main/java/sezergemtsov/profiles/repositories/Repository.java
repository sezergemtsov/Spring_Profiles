package sezergemtsov.profiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sezergemtsov.profiles.models.Message;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Message, Integer> {
}
