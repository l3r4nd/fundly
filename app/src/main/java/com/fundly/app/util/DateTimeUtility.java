package com.fundly.app.util;

import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class DateTimeUtility {

    public int comparePaymentAndDueDates(Date paymentDate, Date dueDate) {
        return paymentDate.compareTo(dueDate);
    }
}
