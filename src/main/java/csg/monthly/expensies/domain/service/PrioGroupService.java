package csg.monthly.expensies.domain.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.repository.PrioGroupRepository;

@Service
public class PrioGroupService {
    @Autowired
    private PrioGroupRepository prioGroupRepository;

    public List<PrioGroup> getPrioGroups() {
        final List<PrioGroup> prioGroups = prioGroupRepository.findAll();
        Collections.sort(prioGroups);
        return prioGroups;
    }

    public void save(PrioGroup prioGroup) {
        prioGroupRepository.save(prioGroup);
    }

    public PrioGroup getPrioGroupByName(String name) {
        return prioGroupRepository.findByName(name);
    }

    public void delete(PrioGroup prioGroup) {
        prioGroupRepository.delete(prioGroup);
    }
}
