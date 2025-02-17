package com.emretest.service;

import com.emretest.model.DeliveryDTO;
import com.emretest.model.RequestPageModel;

import java.time.LocalDateTime;
import java.util.List;

public interface IDeliveryService {

    List<DeliveryDTO> getDeliveriesByStatusCustomerAndDate(RequestPageModel requestPageModel, String deliveryStatus, Integer customerId);

}
