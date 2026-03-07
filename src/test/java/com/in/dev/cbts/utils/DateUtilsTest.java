package com.in.dev.cbts.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void formatDate_validDate_returnsFormatted() {
        String input = "2020-01-02";
        assertEquals("02/01/2020", DateUtils.formatDate(input));
    }

    @Test
    void formatDate_null_returnsSame() {
        assertEquals(null, DateUtils.formatDate(null));
    }

    @Test
    void getSqlDateFromString_basic() {
        java.sql.Date d = DateUtils.getSqlDateFromString("02/01/2020");
        assertNotNull(d);
    }

    @Test
    void getPreviousDayDate_and_getAfterDayDate() {
        java.sql.Date prev = DateUtils.getPreviousDayDate("02/01/2020");
        assertNotNull(prev);
        String after = DateUtils.getAfterDayDate("02/01/2020");
        assertNotNull(after);
    }

    @Test
    void currentTimestamp_variants() {
        String ts = DateUtils.getCurrentTimestamp();
        assertNotNull(ts);
        String ts2 = DateUtils.getCurrentTimestamp((SimpleDateFormat) DateUtils.DEFAULT_TIMESTAMP_FORMAT);
        assertNotNull(ts2);
    }

    @Test
    void getDate_and_getDateWithFormat() {
        Date d = DateUtils.getDate("2020-01-02");
        assertNotNull(d);
        Date d2 = DateUtils.getDate("02/01/2020","dd/MM/yyyy");
        assertNotNull(d2);
    }

    @Test
    void sql_and_util_date_conversion() {
        java.sql.Date s = DateUtils.getCurrentSystemDate();
        assertNotNull(s);
        assertNotNull(DateUtils.getUtilDateFromSqlDate(s));
        assertNotNull(DateUtils.getSqlDateFromUtilDate(new Date()));
    }

    @Test
    void time_and_timestamp_helpers() {
        assertNotNull(DateUtils.getCurrentSystemTime());
        assertNotNull(DateUtils.getCurrentSystemTimestamp());
    }

    @Test
    void day_calculations() {
        int max = DateUtils.getMaxDateInMonth("2","2020");
        assertEquals(29, max);

        java.sql.Date d1 = DateUtils.getSqlDateFromString("01/01/2020");
        java.sql.Date d2 = DateUtils.getSqlDateFromString("03/01/2020");
        assertNotNull(d1);
        assertNotNull(d2);
        assertEquals(3, DateUtils.daysBetween(d1,d2));
        assertEquals(2, DateUtils.getDaysDifferenceBetweenDates(d1,d2));

        assertEquals("DATE2", DateUtils.compareDates(d1,d2));
    }

    @Test
    void getSqlTimeStamp_and_formatters() {
        java.sql.Time t = DateUtils.getSqlTimeStamp("01/01/2020","dd/MM/yyyy");
        assertNotNull(t);
    }
}
