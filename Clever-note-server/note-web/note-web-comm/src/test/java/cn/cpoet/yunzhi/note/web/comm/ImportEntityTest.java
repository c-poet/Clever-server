package cn.cpoet.yunzhi.note.web.comm;

import cn.cpoet.yunzhi.note.domain.impord.ImportSupport;
import io.ebean.Database;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CPoet
 */
@SpringBootTest
public class ImportEntityTest {

    @Autowired
    private Database database;

    @Autowired
    private ImportSupport importSupport;

    @Test
    void start() {
        importSupport.importToDatabase(database);
    }
}
