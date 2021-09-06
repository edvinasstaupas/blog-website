package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Entry;
import lt.staupasedvinas.blog.repository.EntryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;

    public void createEntry(Entry entry) {
        entryRepository.save(entry);
        entry.getAuthor().addEntry(entry);
    }
}
