package com.cashback.usecase.sale.find;

import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import com.cashback.usecase.sale.find.sales.FindSales;
import com.cashback.usecase.sale.find.sales.FindSalesRequest;
import com.cashback.usecase.sale.find.sales.FindSalesResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindSalesImpl implements FindSales {

    private SaleRepository saleRepository;

    public FindSalesImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public FindSalesResponse execute(FindSalesRequest findSalesRequest) {

        Page<Sale> sales = saleRepository.findByCreateAtBetween(findSalesRequest.getInitialDate(),
                findSalesRequest.getFinalDate(), findSalesRequest.getPageable());

        return this.buildFindAlbumsResponse(sales);
    }

    private FindSalesResponse buildFindAlbumsResponse(Page<Sale> sales) {

        List<com.cashback.usecase.sale.find.sales.representation.Sale> collect = sales.get().parallel().map(sale ->
                com.cashback.usecase.sale.find.sales.representation.Sale.valueOf(sale.getId(),
                        this.buildProduct(sale.getProducts()), sale.getTotal(),
                        sale.getCashback(), sale.getCreateAt()))
                .collect(Collectors.toList());

        return FindSalesResponse.valueOf(collect, sales.getNumberOfElements(), sales.getTotalPages(),
                sales.getPageable().getPageNumber(), sales.isLast(), sales.isFirst());
    }

    private List<com.cashback.usecase.sale.find.sales.representation.Product> buildProduct(List<Product> products){

        return products.parallelStream().map(product -> com.cashback.usecase.sale.find.sales.representation.Product.valueOf(
                product.getDescription(), product.getPrice(), product.getCashback()))
                .collect(Collectors.toList());
    }
}
