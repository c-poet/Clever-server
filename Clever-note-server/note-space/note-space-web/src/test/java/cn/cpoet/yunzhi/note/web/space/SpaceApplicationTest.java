package cn.cpoet.yunzhi.note.web.space;

import cn.cpoet.yunzhi.note.api.constant.SystemConst;
import cn.cpoet.yunzhi.note.api.core.AppInfo;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.IdGenerator;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * @author CPoet
 */
public class SpaceApplicationTest {
    @Test
    public void ddlGrtSql() throws IOException {
        Database database = createDatabase();
        DbMigration dbMigration = DbMigration.create();
        dbMigration.setServer(database);
        dbMigration.addPlatform(Platform.MYSQL);
        dbMigration.addPlatform(Platform.ORACLE);
        dbMigration.addPlatform(Platform.SQLSERVER);
        dbMigration.setVersion(AppInfo.INSTANCE.version() + "." + System.currentTimeMillis());
        dbMigration.generateMigration();
    }

    private Database createDatabase() {
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDriver("com.mysql.cj.jdbc.Driver");
        dsConfig.setUrl("jdbc:mysql://localhost:3306/yunzhi_note");
        dsConfig.setUsername("root");
        dsConfig.setPassword("123456");

        DatabaseConfig config = new DatabaseConfig();
        config.setName("Test");
        config.setIdGenerators(Collections.singletonList(new IdGenerator() {
            @Override
            public Object nextValue() {
                return SystemConst.DEFAULT_ENTITY_ID;
            }

            @Override
            public String getName() {
                return SystemConst.GLOBAL_ID_GENERATOR;
            }
        }));
        config.setDataSourceConfig(dsConfig);
        config.setCurrentUserProvider(() -> SystemConst.DEFAULT_ENTITY_ID);
        return DatabaseFactory.create(config);
    }
}
