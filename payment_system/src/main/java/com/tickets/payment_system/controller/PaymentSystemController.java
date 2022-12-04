package com.tickets.payment_system.controller;

import com.tickets.payment_system.domain.dto.ClientDTO;
import com.tickets.payment_system.service.PayServiceBean;
import com.tickets.payment_system.service.PaymentCrudServiceBean;
import com.tickets.payment_system.service.PaymentStatusServiceBean;
import com.tickets.payment_system.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/pay", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentSystemController implements PaymentSystemMethods{

    private final PaymentStatusServiceBean paymentStatusServiceBean;

    private final PaymentCrudServiceBean paymentCrudServiceBean;

    private final PayServiceBean payServiceBean;

    private final ClientMapper clientMapper;

    @Override
    @GetMapping("/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String setStatus(@PathVariable Long id){
        log.info("[Payment system] Start method setStatus with parameter id: {}", id);
        return paymentStatusServiceBean.status_processing(id);
    }

    @Override
    @GetMapping("/st/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String getPaymentStatus(@PathVariable Long id){
        log.info("[Payment system] Start method getPaymentStatus with parameter id: {}", id);
        return paymentCrudServiceBean.getById(id).getStatus();
    }

    @Override
    @PutMapping("/statuses")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> setStatuses(){
        log.info("[Payment system] Start method setStatuses");
        return paymentStatusServiceBean.status_processing();
    }

    @Override
    @PostMapping("/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long pay(@RequestBody @Valid ClientDTO clientDTO, @PathVariable Double amount){
        log.info("[Payment system] Start method pay. ClientDTO: {} and amount: {} received", clientDTO, amount);
        return payServiceBean.do_payment(clientMapper.toObject(clientDTO), amount);
    }

}
