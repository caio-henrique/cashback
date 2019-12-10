package com.cashback.usecase.sale.create;

import com.cashback.common.enums.Gender;
import com.cashback.common.exception.GenericBusinessException;
import com.cashback.repository.CashbackRepository;
import com.cashback.repository.ProductRepository;
import com.cashback.repository.entity.Album;
import com.cashback.repository.entity.Cashback;
import com.cashback.repository.entity.Product;
import com.cashback.repository.entity.Sale;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class CreateProductTest {

    @Mock
    private CashbackRepository cashbackRepositoryMocked;

    @Mock
    private ProductRepository productRepositoryMocked;

    private CreateProductImpl createProduct;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.createProduct = new CreateProductImpl(cashbackRepositoryMocked, productRepositoryMocked);
    }

    @Test
    public void product_creation_test() {

        Album albumMocked = this.createAlbumMocked();

        Cashback cashbackMocked = new Cashback();
        cashbackMocked.setId(1l);
        cashbackMocked.setGender(Gender.MPB);
        cashbackMocked.setMonday(1);
        cashbackMocked.setTuesday(1);
        cashbackMocked.setWednesday(1);
        cashbackMocked.setThursday(1);
        cashbackMocked.setFriday(1);
        cashbackMocked.setSaturday(1);
        cashbackMocked.setSunday(1);

        Mockito.when(cashbackRepositoryMocked.findByGender(Gender.MPB)).thenReturn(Optional.of(cashbackMocked));
        ArgumentCaptor<List<Product>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        createProduct.execute(Collections.singletonList(albumMocked), new Sale());

        verify(productRepositoryMocked).saveAll(argumentCaptor.capture());
        assertEquals(albumMocked.getPrice(), argumentCaptor.getValue().get(0).getPrice());
        assertEquals(albumMocked.getName() + ", " + albumMocked.getArtists() + ", " + albumMocked.getGender(), argumentCaptor.getValue().get(0).getDescription());
        assertEquals(BigDecimal.valueOf(1.0).setScale(2), argumentCaptor.getValue().get(0).getCashback().setScale(2));
    }

    @Test(expected = GenericBusinessException.class)
    public void no_cashback_percentage_found_test() {

        Album albumMocked = this.createAlbumMocked();
        Mockito.when(cashbackRepositoryMocked.findByGender(Gender.MPB)).thenReturn(Optional.empty());

        createProduct.execute(Collections.singletonList(albumMocked), new Sale());
    }

    private Album createAlbumMocked() {

        Album albumMocked =  new Album();
        albumMocked.setId(1l);
        albumMocked.setGender(Gender.MPB);
        albumMocked.setArtists("Test");
        albumMocked.setPrice(BigDecimal.valueOf(100l));
        albumMocked.setTotalTracks(1);
        albumMocked.setName("Test");
        albumMocked.setReleaseDate("1992-10-21");

        return albumMocked;
    }
}
