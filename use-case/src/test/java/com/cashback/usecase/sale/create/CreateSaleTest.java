package com.cashback.usecase.sale.create;

import com.cashback.common.enums.Gender;
import com.cashback.repository.SaleRepository;
import com.cashback.repository.entity.Album;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateSaleTest {

    @Mock
    private SaleRepository saleRepositoryMocked;

    @Mock
    private CreateProductImpl createProductMocked;

    @Mock
    private FindAlbumsImpl findAlbumsMocked;

    private CreateSaleImpl createSale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.createSale = new CreateSaleImpl(saleRepositoryMocked, createProductMocked, findAlbumsMocked);
    }

    @Test
    public void sales_creation_test() {

        Album albumMocked =  new Album();
        albumMocked.setId(1l);
        albumMocked.setGender(Gender.MPB);
        albumMocked.setArtists("Test");
        albumMocked.setPrice(BigDecimal.ONE);
        albumMocked.setTotalTracks(1);
        albumMocked.setName("Test");
        albumMocked.setReleaseDate("1992-10-21");

        Product productMocked = new Product();
        productMocked.setId(1l);
        productMocked.setDescription("Test");
        productMocked.setCashback(BigDecimal.ONE);
        productMocked.setPrice(BigDecimal.ONE);

        Product productMockedAux = new Product();
        productMockedAux.setId(2l);
        productMockedAux.setDescription("Test 2");
        productMockedAux.setCashback(BigDecimal.ONE);
        productMockedAux.setPrice(BigDecimal.ONE);

        Mockito.when(findAlbumsMocked.execute(Mockito.any(List.class)))
                .thenReturn(Collections.singletonList(albumMocked));
        Mockito.when(createProductMocked.execute(Mockito.any(List.class), Mockito.any(Sale.class)))
                .thenReturn(Arrays.asList(productMocked, productMockedAux));
        Mockito.when(saleRepositoryMocked.saveAndFlush(Mockito.any(Sale.class)))
                .thenReturn(new Sale());

        CreateSaleResponse createSaleResponse = createSale.execute(CreateSaleRequest.valueOf(Collections.singletonList(1l)));

        assertEquals(BigDecimal.valueOf(2l), createSaleResponse.getTotal());
        assertEquals(BigDecimal.valueOf(2l), createSaleResponse.getCashback());
        assertEquals(productMocked.getDescription(), createSaleResponse.getProducts().get(0).getDescription());
        assertEquals(productMocked.getPrice(), createSaleResponse.getProducts().get(0).getPrice());
        assertEquals(productMocked.getCashback(), createSaleResponse.getProducts().get(0).getCashback());
    }
}
