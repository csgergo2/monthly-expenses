package csg.monthly.expensies.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void save(final Tag tag) {
        tagRepository.save(tag);
    }
}
