package com.emretest.service.impl;

import com.emretest.entities.Delivery;
import com.emretest.model.DeliveryDTO;
import com.emretest.model.RequestPageModel;
import com.emretest.repositories.DeliveryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<DeliveryDTO> getDeliveriesByStatusCustomerAndDate(RequestPageModel requestPageModel, String deliveryStatus, Integer customerId) {
        // Sayfalama bilgilerini RequestPageModel'den alıyoruz
        Pageable pageable = getPageable(requestPageModel);

        // Delivery verilerini sorgulama
        Page<Delivery> deliveries = deliveryRepository.findDeliveriesByStatusCustomerAndDate(deliveryStatus, customerId, pageable);

        // Delivery listesi üzerinde iterasyon yaparak her Delivery nesnesini DeliveryDTO'ya dönüştürme
        return deliveries.getContent().stream().map(delivery -> {
            DeliveryDTO deliveryDTO = new DeliveryDTO();
            BeanUtils.copyProperties(delivery, deliveryDTO);
            return deliveryDTO;
        }).collect(Collectors.toList());
    }

    private Pageable getPageable(RequestPageModel requestPageModel) {
        Sort sort = Sort.by(requestPageModel.getSortDirection() ? Sort.Order.asc(requestPageModel.getColumnName()) : Sort.Order.desc(requestPageModel.getColumnName()));
        return PageRequest.of(requestPageModel.getPageNumber(), requestPageModel.getPageSize(), sort);
    }
}
