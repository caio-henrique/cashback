package com.cashback.usecase.sale.create;

import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Album;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateSaleImpl implements CreateSale {

    private SaleRepository saleRepository;
    private CreateProductImpl createProduct;
    private FindAlbumsImpl findAlbums;

    public CreateSaleImpl(SaleRepository saleRepository, CreateProductImpl createProduct, FindAlbumsImpl findAlbums) {
        this.saleRepository = saleRepository;
        this.createProduct = createProduct;
        this.findAlbums = findAlbums;
    }

    @Override
    @Transactional
    public CreateSaleResponse execute(CreateSaleRequest createSaleRequest) {

        List<Album> albums = findAlbums.execute(createSaleRequest.getProductIds());
        Sale sale = this.createSale();
        List<Product> products = createProduct.execute(albums, sale);
        sale.setTotal(this.sumPrices(products));
        sale.setCashback(this.sumCashbacks(products));
        sale.setCreateAt(LocalDateTime.now());

        return this.buildCreateSaleResponse(sale, products);
    }

    private Sale createSale() {

        Sale sale = new Sale();
        sale.setTotal(BigDecimal.ZERO);
        sale.setCashback(BigDecimal.ZERO);

        return saleRepository.saveAndFlush(sale);
    }

    private BigDecimal sumPrices(List<Product> products) {

        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .get();
    }

    private BigDecimal sumCashbacks(List<Product> products) {

        return products.stream()
                .map(Product::getCashback)
                .reduce(BigDecimal::add)
                .get();
    }

    private CreateSaleResponse buildCreateSaleResponse(Sale sale, List<Product> products) {

        List<com.cashback.usecase.sale.create.representation.Product> collect = products.parallelStream()
                .map(product -> com.cashback.usecase.sale.create.representation.Product.valueOf(
                        product.getDescription(), product.getPrice(), product.getCashback()))
                .collect(Collectors.toList());

        return CreateSaleResponse.valueOf(sale.getId(), collect, sale.getTotal(),
                sale.getCashback(), sale.getCreateAt());
    }
}
