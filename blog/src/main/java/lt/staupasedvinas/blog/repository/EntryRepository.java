package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Entry findByIdAndText(Long id, String text);
}
