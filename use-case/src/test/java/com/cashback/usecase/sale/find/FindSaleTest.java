package com.cashback.usecase.sale.find;

import com.cashback.common.exception.ResourceNotFoundException;
import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import com.cashback.usecase.sale.find.sale.FindSaleRequest;
import com.cashback.usecase.sale.find.sale.FindSaleResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class FindSaleTest {

    @Mock
    private SaleRepository saleRepositoryMocked;

    private FindSaleImpl findSale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.findSale = new FindSaleImpl(saleRepositoryMocked);
    }

    @Test
    public void find_sale_test() {

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

        Mockito.when(saleRepositoryMocked.findById(1l)).thenReturn(Optional.of(saleMocked));

        FindSaleResponse findSaleResponse = findSale.execute(FindSaleRequest.valueOf(1l));

        assertEquals(saleMocked.getId(), findSaleResponse.getId());
        assertEquals(saleMocked.getCashback(), findSaleResponse.getCashback());
        assertEquals(saleMocked.getTotal(), findSaleResponse.getTotal());
        assertEquals(productMocked.getDescription(), findSaleResponse.getProducts().get(0).getDescription());
        assertEquals(productMocked.getCashback(), findSaleResponse.getProducts().get(0).getCashback());
        assertEquals(productMocked.getPrice(), findSaleResponse.getProducts().get(0).getPrice());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_exception_sale_lookup_by_id() {

        Mockito.when(saleRepositoryMocked.findById(1l)).thenReturn(Optional.empty());

        findSale.execute(FindSaleRequest.valueOf(1l));
    }
}
