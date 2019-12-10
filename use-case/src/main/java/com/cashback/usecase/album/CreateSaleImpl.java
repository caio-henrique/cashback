package com.cashback.usecase.album;

import com.cashback.common.enums.Gender;
import com.cashback.repository.AlbumRepository;
import com.cashback.repository.CashbackRepository;
import com.cashback.repository.ProductRepository;
import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Album;
import com.cashback.repository.entity.Cashback;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import com.cashback.usecase.sale.create.CreateSale;
import com.cashback.usecase.sale.create.CreateSaleRequest;
import com.cashback.usecase.sale.create.CreateSaleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateSaleImpl implements CreateSale {

    private SaleRepository saleRepository;
    private ProductRepository productRepository;
    private AlbumRepository albumRepository;
    private CashbackRepository cashbackRepository;

    public CreateSaleImpl(SaleRepository saleRepository, ProductRepository productRepository,
                          AlbumRepository albumRepository, CashbackRepository cashbackRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.albumRepository = albumRepository;
        this.cashbackRepository = cashbackRepository;
    }

    @Override
    @Transactional
    public CreateSaleResponse execute(CreateSaleRequest createSaleRequest) {

        List<Album> albums = albumRepository.findAllById(createSaleRequest.getProductIds());
        Sale sale = this.createSale();
        List<Product> products = this.buildProduct(albums, sale);
        sale.setTotal(this.sumPrices(products));
        sale.setCashback(this.sumCashbacks(products));
        sale.setCreateAt(LocalDateTime.now());
        this.saveProducts(products);

        return null;
    }

    private Sale createSale() {

        Sale sale = new Sale();
        sale.setTotal(BigDecimal.ZERO);
        sale.setCashback(BigDecimal.ZERO);

        return saleRepository.saveAndFlush(sale);
    }

    private List<Product> buildProduct(List<Album> albums, Sale sale) {

        return albums.parallelStream()
            .map(album -> {
                Product product = new Product();
                product.setPrice(album.getPrice());
                product.setDescription(this.buildDescriptionProduct(album));
                product.setCashback(this.calculateCashBack(album.getGender(), album.getPrice()));
                product.setCreateAt(LocalDateTime.now());
                product.setSale(sale);
                return product;
            })
            .collect(Collectors.toList());
    }

    private BigDecimal calculateCashBack(Gender gender, BigDecimal price) {

        Integer percentageCashback = this.getPercentageCashback(gender);
        return price.multiply(BigDecimal.valueOf(percentageCashback / 100.00));
    }

    private String buildDescriptionProduct(Album album) {

        return album.getName() + ", " + album.getArtists() + ", " + album.getGender();
    }

    private Integer getPercentageCashback(Gender gender) {

        Cashback percentageCashback = cashbackRepository.findByGender(gender).orElseThrow(() -> new RuntimeException());
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        if(DayOfWeek.MONDAY.equals(dayOfWeek))
            return percentageCashback.getMonday();
        else if(DayOfWeek.TUESDAY.equals(dayOfWeek))
            return percentageCashback.getTuesday();
        else if(DayOfWeek.WEDNESDAY.equals(dayOfWeek))
            return percentageCashback.getWednesday();
        else if(DayOfWeek.THURSDAY.equals(dayOfWeek))
            return percentageCashback.getThursday();
        else if(DayOfWeek.FRIDAY.equals(dayOfWeek))
            return percentageCashback.getFriday();
        else if(DayOfWeek.SATURDAY.equals(dayOfWeek))
            return percentageCashback.getSaturday();
        else if(DayOfWeek.SUNDAY.equals(dayOfWeek))
            return percentageCashback.getSunday();

        return 0;
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

    private void saveProducts(List<Product> products) {

        productRepository.saveAll(products);
    }
}
