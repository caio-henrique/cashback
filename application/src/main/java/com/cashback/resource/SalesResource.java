package com.cashback.resource;

import com.cashback.usecase.sale.create.CreateSale;
import com.cashback.usecase.sale.create.CreateSaleRequest;
import com.cashback.usecase.sale.find.FindSales;
import com.cashback.usecase.sale.find.FindSalesRequest;
import com.cashback.usecase.sale.find.FindSalesResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/sale")
public class SalesResource {

    private FindSales findSales;
    private CreateSale createSale;

    public SalesResource(FindSales findSales, CreateSale createSale) {
        this.findSales = findSales;
        this.createSale = createSale;
    }

    @GetMapping
    public FindSalesResponse findSales(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate,
                                       @PageableDefault(size = 10) Pageable pageable) {

        return findSales.execute(FindSalesRequest.valueOf(initialDate.atTime(LocalTime.MIDNIGHT),
                finalDate.atTime(LocalTime.MIDNIGHT), pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sellProduct(@RequestBody CreateSaleRequest createSaleRequest) {

        createSale.execute(createSaleRequest);
    }
}
