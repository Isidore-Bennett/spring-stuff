package com.coughy.maybe.service;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.entity.UserOrderAddIn;
import com.coughy.maybe.repository.UserOrderAddInRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserOrderAddInService {

    private final UserOrderAddInRepository userOrderAddInRepository;

    public UserOrderAddInService(UserOrderAddInRepository userOrderAddInRepository) {
        this.userOrderAddInRepository = userOrderAddInRepository;
    }

    public ArrayList<UserOrderAddIn> save(ArrayList<UserOrderAddIn> userOrderAddIns) {
        return (ArrayList<UserOrderAddIn>) userOrderAddInRepository.saveAll(userOrderAddIns);
    }

    @Transactional
    public void deleteAddInsNotPresent(UserOrder userOrder) {
        Collection<UserOrderAddIn> userOrderAddIns = userOrder.getUserOrderAddIns();
        Set<String> newAddIns = userOrderAddIns != null
                ? userOrderAddIns.stream().map(userOrderAddIn -> userOrderAddIn.getAddIn().getId()).collect(Collectors.toSet())
                : new HashSet<>();
        Set<UserOrderAddIn> oldUserOrderAddIns = userOrderAddInRepository.findByUserOrder(userOrder);

        Set<AddIn> addInsToBeDeleted = oldUserOrderAddIns
                .stream()
                .filter(userOrderAddIn -> !newAddIns.contains(userOrderAddIn.getAddIn().getId()))
                .collect(Collectors.toSet())
                .stream()
                .map(UserOrderAddIn::getAddIn)
                .collect(Collectors.toSet());

        userOrderAddInRepository.deleteAllByAddInIn(addInsToBeDeleted);
    }

}
