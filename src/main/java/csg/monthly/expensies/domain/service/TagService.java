package csg.monthly.expensies.domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        List<Tag> tags = tagRepository.findAll();
        Map<String, Tag> tagMap = new HashMap<>();
        for (Tag tag : tags) {
            tagMap.put(tag.getName(), tag);
        }
        return tagRepository.findTagsByTheirFrequency().stream().map(tag -> tagMap.get(tag[0])).collect(Collectors.toList());
    }

    public void save(final Tag tag) {
        tagRepository.save(tag);
    }
}
