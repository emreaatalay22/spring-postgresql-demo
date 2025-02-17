package com.emretest.controller;

import com.emretest.model.DeliveryDTO;
import com.emretest.model.RequestPageModel;
import com.emretest.service.impl.DeliveryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Timed(value = "requests.time", description = "Request Response Time",histogram = true)
    @Counted(value = "requests.count", description = "Total Request Count")
    @GetMapping
    public List<DeliveryDTO> getDeliveries(
            @RequestParam String deliveryStatus,
            @RequestParam Integer customerId,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String columnName,
            @RequestParam Boolean sortDirection) {
        System.out.println(customerId+":"+deliveryStatus);

        RequestPageModel requestPageModel = new RequestPageModel();
        requestPageModel.setPageNumber(pageNumber);
        requestPageModel.setPageSize(pageSize);
        requestPageModel.setColumnName(columnName);
        requestPageModel.setSortDirection(sortDirection);

        return deliveryService.getDeliveriesByStatusCustomerAndDate(requestPageModel, deliveryStatus, customerId);
    }
}
