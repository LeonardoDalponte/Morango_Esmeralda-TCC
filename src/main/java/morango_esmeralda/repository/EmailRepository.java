package morango_esmeralda.repository;

import morango_esmeralda.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
