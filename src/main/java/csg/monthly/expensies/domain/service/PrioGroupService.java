package csg.monthly.expensies.domain.service;

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
        return prioGroupRepository.findAll();
    }
}
