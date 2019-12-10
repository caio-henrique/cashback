package com.cashback.usecase.sale.find;

import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import com.cashback.usecase.sale.find.sales.FindSalesRequest;
import com.cashback.usecase.sale.find.sales.FindSalesResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindSalesTest {

    @Mock
    private SaleRepository saleRepositoryMocked;

    private FindSalesImpl findAlbums;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.findAlbums = new FindSalesImpl(saleRepositoryMocked);
    }

    @Test
    public void find_sales_test() {

        Product productMocked = new Product();
        productMocked.setId(1l);
        productMocked.setDescription("Test");
        productMocked.setCashback(BigDecimal.ONE);
        productMocked.setPrice(BigDecimal.ONE);

        Sale saleMocked = new Sale();
        saleMocked.setId(1l);
        saleMocked.setCashback(BigDecimal.ONE);
        saleMocked.setTotal(BigDecimal.ONE);
        saleMocked.setProducts(Arrays.asList(productMocked));

        PageImpl<Sale> pageAlbumsMocked = new PageImpl<>(Arrays.asList(saleMocked),
                PageRequest.of(1, 1), 1l);
        Mockito.when(saleRepositoryMocked.findByCreateAtBetween(Mockito.any(LocalDateTime.class),
                Mockito.any(LocalDateTime.class), Mockito.any(Pageable.class))).thenReturn(pageAlbumsMocked);

        FindSalesResponse findSalesResponse = findAlbums.execute(FindSalesRequest.valueOf(LocalDateTime.now(),
                LocalDateTime.now(), PageRequest.of(1, 1)));

        assertEquals(1, findSalesResponse.getContent().size());
        assertEquals(Integer.valueOf(1), findSalesResponse.getPageNumber());
        assertEquals(Integer.valueOf(1), findSalesResponse.getTotalElements());
        assertEquals(saleMocked.getId(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getId());
        assertEquals(saleMocked.getCashback(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getCashback());
        assertEquals(saleMocked.getTotal(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getTotal());
        assertEquals(productMocked.getDescription(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getProducts().get(0).getDescription());
        assertEquals(productMocked.getCashback(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getProducts().get(0).getCashback());
        assertEquals(productMocked.getPrice(), ((com.cashback.usecase.sale.find.sales.representation.Sale)findSalesResponse.getContent().get(0)).getProducts().get(0).getPrice());
    }
}
