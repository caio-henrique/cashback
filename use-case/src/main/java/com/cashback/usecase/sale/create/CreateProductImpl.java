package com.cashback.usecase.sale.create;

import com.cashback.common.enums.Gender;
import com.cashback.common.exception.GenericBusinessException;
import com.cashback.repository.CashbackRepository;
import com.cashback.repository.ProductRepository;
import com.cashback.repository.entity.Album;
import com.cashback.repository.entity.Cashback;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
class CreateProductImpl {

    private CashbackRepository cashbackRepository;
    private ProductRepository productRepository;

    public CreateProductImpl(CashbackRepository cashbackRepository, ProductRepository productRepository) {
        this.cashbackRepository = cashbackRepository;
        this.productRepository = productRepository;
    }

    public List<Product> execute(List<Album> albums, Sale sale) {

        List<Product> products = albums.parallelStream()
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

        return productRepository.saveAll(products);
    }

    private String buildDescriptionProduct(Album album) {

        return album.getName() + ", " + album.getArtists() + ", " + album.getGender();
    }

    private BigDecimal calculateCashBack(Gender gender, BigDecimal price) {

        Integer percentageCashback = this.getPercentageCashback(gender);
        return price.multiply(BigDecimal.valueOf(percentageCashback / 100.00));
    }

    private Integer getPercentageCashback(Gender gender) {

        Cashback percentageCashback = cashbackRepository.findByGender(gender).orElseThrow(
                () -> new GenericBusinessException("error-no-cashback-percentage-found"));
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
}
