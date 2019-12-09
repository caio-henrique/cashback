package com.cashback.usecase.sale;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FindSalesRequest implements Serializable {

    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}
