package com.in.dev.cbts.config;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static org.mockito.Mockito.*;

class DBInfoTest {

    @Test
    void printDbInfo_uses_datasource() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        DatabaseMetaData meta = mock(DatabaseMetaData.class);
        when(ds.getConnection()).thenReturn(conn);
        when(conn.getMetaData()).thenReturn(meta);
        DBInfo info = new DBInfo();
        org.springframework.test.util.ReflectionTestUtils.setField(info, "dataSource", ds);
        info.printDbInfo();
        verify(ds).getConnection();
    }
}
