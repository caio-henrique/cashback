package com.cashback.usecase.album;

import com.cashback.repository.SaleRepository;
import com.cashback.usecase.sale.FindSales;
import com.cashback.usecase.sale.FindSalesRequest;
import com.cashback.usecase.sale.FindSalesResponse;
import org.springframework.stereotype.Service;

@Service
public class FindSalesImpl implements FindSales {

    private SaleRepository saleRepository;

    public FindSalesImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public FindSalesResponse execute(FindSalesRequest findSalesRequest) {
        return null;
    }
}
