package csg.monthly.expensies.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.repository.PrioGroupRepository;

@Service
public class PrioGroupService {
    @Autowired
    private PrioGroupRepository prioGroupRepository;
}
