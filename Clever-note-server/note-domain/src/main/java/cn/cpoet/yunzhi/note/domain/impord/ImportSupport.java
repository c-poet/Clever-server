package cn.cpoet.yunzhi.note.domain.impord;

import cn.cpoet.yunzhi.note.annotation.util.JsonUtil;
import cn.cpoet.yunzhi.note.domain.configuration.auto.ImportProperties;
import io.ebean.DB;
import io.ebean.Database;
import io.ebeaninternal.api.SpiEbeanServer;
import io.ebeaninternal.server.deploy.BeanProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.util.ProxyUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数据导入支持
 *
 * @author CPoet
 */
@Slf4j
public class ImportSupport {

    @Autowired
    private ImportProperties importProperties;

    @Autowired
    private ObjectProvider<List<ImportScanner>> importScanners;

    /**
     * 导入数据到默认数据库
     */
    public void importToDatabase() {
        importToDatabase(DB.getDefault());
    }

    /**
     * 导入数据到指定的数据库
     *
     * @param database 数据库
     */
    public void importToDatabase(Database database) {
        List<ImportScanner> scanners = importScanners.getIfAvailable();
        if (CollectionUtils.isEmpty(scanners)) {
            log.warn("未找到可用的数据扫描器.");
            return;
        }
        List<String> paths = importProperties.getPaths();
        if (CollectionUtils.isEmpty(paths)) {
            log.warn("未配置数据路径.");
            return;
        }
        for (String path : paths) {
            for (ImportScanner scanner : scanners) {
                List<ImportEntity> entities = scanner.scanner(path);
                if (!CollectionUtils.isEmpty(entities)) {
                    writeEntity2Db(database, entities);
                }
            }
        }
    }

    /**
     * 写入实体数据到数据库
     *
     * @param database 数据库
     * @param entities 实体列表
     */
    @SuppressWarnings("rawtypes")
    private void writeEntity2Db(Database database, List<ImportEntity> entities) {
        for (ImportEntity entity : entities) {
            try {
                String type = entity.getType();
                Class<?> clazz = ClassUtils.forName(type, getClass().getClassLoader());
                for (Object content : entity.getEntities()) {
                    Map map = content instanceof Map ? (Map) content : JsonUtil.convertValue(content, Map.class);
                    Object obj = JsonUtil.convertValue(map, clazz);
                    if (!CollectionUtils.isEmpty(map)) {
                        BeanProperty idProperty = ((SpiEbeanServer) ProxyUtils.getTargetObject(database)).descriptor(clazz).idProperty();
                        Object id = map.get(idProperty.name());
                        if (id == null) {
                            database.save(obj);
                        } else {
                            Object oldObj = database
                                .find(clazz)
                                .where()
                                .idEq(id)
                                .findOne();
                            if (oldObj == null) {
                                database.save(obj);
                            } else {
                                // 覆盖旧值
                                for (Object property : map.keySet()) {
                                    PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(clazz, String.valueOf(property));
                                    if (descriptor != null) {
                                        if (!Objects.equals(idProperty.name(), descriptor.getName())) {
                                            Method readMethod = descriptor.getReadMethod();
                                            Method writeMethod = descriptor.getWriteMethod();
                                            writeMethod.invoke(oldObj, readMethod.invoke(obj));
                                        }
                                    } else {
                                        log.warn("实体类[{}]不存在属性[{}].", clazz.getName(), property);
                                    }
                                }
                                database.save(oldObj);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.warn("实体数据写入失败：{}", e.getMessage(), e);
            }
        }
    }
}
