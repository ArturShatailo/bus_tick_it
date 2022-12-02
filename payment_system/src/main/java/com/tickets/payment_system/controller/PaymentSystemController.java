package com.tickets.payment_system.controller;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.ClientDTO;
import com.tickets.payment_system.service.PayServiceBean;
import com.tickets.payment_system.service.PaymentCrudServiceBean;
import com.tickets.payment_system.service.PaymentStatusServiceBean;
import com.tickets.payment_system.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentSystemController {

    private final PaymentStatusServiceBean paymentStatusServiceBean;

    private final PayServiceBean payServiceBean;

    private final ClientMapper clientMapper;

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public String setStatus(@RequestParam Long payment_id){
        return paymentStatusServiceBean.status_processing(payment_id);
    }

    @PutMapping("/statuses")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> setStatuses(){
        return paymentStatusServiceBean.status_processing();
    }

    @PostMapping("/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public Long buyTicket(@RequestBody ClientDTO clientDTO, @PathVariable Double amount){
        return payServiceBean.do_payment(clientMapper.toObject(clientDTO), amount);
    }

}
