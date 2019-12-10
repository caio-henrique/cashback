package com.cashback.resource;

import com.cashback.usecase.sale.create.CreateSale;
import com.cashback.usecase.sale.create.CreateSaleRequest;
import com.cashback.usecase.sale.create.CreateSaleResponse;
import com.cashback.usecase.sale.find.sale.FindSale;
import com.cashback.usecase.sale.find.sale.FindSaleRequest;
import com.cashback.usecase.sale.find.sale.FindSaleResponse;
import com.cashback.usecase.sale.find.sales.FindSales;
import com.cashback.usecase.sale.find.sales.FindSalesRequest;
import com.cashback.usecase.sale.find.sales.FindSalesResponse;
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
    private FindSale findSale;

    public SalesResource(FindSales findSales, CreateSale createSale, FindSale findSale) {
        this.findSales = findSales;
        this.createSale = createSale;
        this.findSale = findSale;
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
    public CreateSaleResponse sellProduct(@RequestBody CreateSaleRequest createSaleRequest) {

        return createSale.execute(createSaleRequest);
    }

    @GetMapping("/{identifier}")
    public FindSaleResponse findSale(@PathVariable Long identifier) {

        return findSale.execute(FindSaleRequest.valueOf(identifier));
    }
}
