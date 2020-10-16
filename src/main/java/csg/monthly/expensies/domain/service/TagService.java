package csg.monthly.expensies.domain.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.TagComment;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.domain.repository.TagCommentRepository;
import csg.monthly.expensies.domain.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TagCommentRepository tagCommentRepository;

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

    public boolean isTagDeletable(Tag tag) {
        return itemRepository.findByTagId(tag.getId()) == 0;
    }

    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    public Optional<TagComment> getCommentByTag(Tag tag) {
        return Optional.ofNullable(tagCommentRepository.findByTag(tag.getId()));
    }

    @Transactional
    public void saveComment(Tag tag, String comment) {
        Application.getBean(TagCommentRepository.class).deleteByTag(tag.getId());
        if (comment != null && !comment.isEmpty()) {
            tagCommentRepository.save(new TagComment(comment, tag.getId()));
        }
    }

    public List<Tag> findByPrioGroup(PrioGroup prioGroup) {
        final List<Tag> tags = tagRepository.findByPrioGroup(prioGroup);
        Collections.sort(tags);
        return tags;
    }
}
