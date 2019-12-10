package com.cashback.usecase.sale.find;

import com.cashback.common.exception.ResourceNotFoundException;
import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import com.cashback.usecase.sale.find.sale.FindSale;
import com.cashback.usecase.sale.find.sale.FindSaleRequest;
import com.cashback.usecase.sale.find.sale.FindSaleResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindSaleImpl implements FindSale {

    private SaleRepository saleRepository;

    public FindSaleImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public FindSaleResponse execute(FindSaleRequest findSaleRequest) {

        Sale sale = saleRepository.findById(findSaleRequest.getIdentifier())
                .orElseThrow(ResourceNotFoundException::new);

        return FindSaleResponse.valueOf(sale.getId(), this.buildProduct(sale.getProducts()),
                sale.getTotal(), sale.getCashback(), sale.getCreateAt());
    }

    private List<com.cashback.usecase.sale.find.sale.representation.Product> buildProduct(List<Product> products){

        return products.parallelStream().map(product -> com.cashback.usecase.sale.find.sale.representation.Product.valueOf(
                product.getDescription(), product.getPrice(), product.getCashback()))
                .collect(Collectors.toList());
    }
}
