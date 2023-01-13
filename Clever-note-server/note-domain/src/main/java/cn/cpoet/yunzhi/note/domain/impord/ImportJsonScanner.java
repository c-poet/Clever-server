package cn.cpoet.yunzhi.note.domain.impord;

import cn.cpoet.yunzhi.note.api.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Json扫描器
 *
 * @author CPoet
 */
@Slf4j
public class ImportJsonScanner implements ImportScanner {

    private final static String JSON_EXT = ".json";
    private final static String JSON_ARRAY_PREFIX = "[";

    @Override
    public List<ImportEntity> scanner(String path) {
        List<File> jsonFiles = findJsonFiles(path);
        if (CollectionUtils.isEmpty(jsonFiles)) {
            return Collections.emptyList();
        }
        List<ImportEntity> importEntities = new ArrayList<>(jsonFiles.size());
        for (File jsonFile : jsonFiles) {
            List<ImportEntity> entities = readFileAsEntity(jsonFile);
            if (!CollectionUtils.isEmpty(entities)) {
                importEntities.addAll(entities);
            }
        }
        return importEntities;
    }

    /**
     * 读取文件
     *
     * @param file 文件
     * @return 实例信息列表
     */
    private List<ImportEntity> readFileAsEntity(File file) {
        String content = readFileAsString(file);
        if (!StringUtils.hasText(content)) {
            return Collections.emptyList();
        }
        try {
            // 判断Json是否是数组
            if (content.startsWith(JSON_ARRAY_PREFIX)) {
                return JsonUtil.readValue(content, new TypeReference<List<ImportEntity>>() {
                });
            }
            return Collections.singletonList(JsonUtil.readValue(content, ImportEntity.class));
        } catch (Exception e) {
            log.warn("Json数据读取失败: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 获取目录下的所有json文件
     *
     * @param path 路径
     * @return Json文件列表
     */
    private List<File> findJsonFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return Collections.emptyList();
        }
        if (file.isFile()) {
            return isJsonFile(path) ? Collections.singletonList(file) : Collections.emptyList();
        }
        File[] files = file.listFiles((dir, name) -> isJsonFile(name));
        return files == null || files.length == 0 ? Collections.emptyList() : Arrays.asList(files);
    }

    /**
     * 判断是否是Json文件
     *
     * @param filename 文件名
     * @return bool
     */
    private boolean isJsonFile(String filename) {
        return StringUtils.hasText(filename) && filename.lastIndexOf(JSON_EXT) != -1;
    }

    /**
     * 读取文件
     *
     * @param file 文件
     * @return 文件内容
     */
    private String readFileAsString(File file) {
        StringBuilder sb = new StringBuilder();
        try (Reader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        } catch (IOException e) {
            log.warn("文件读取失败：{}", e.getMessage(), e);
        }
        return sb.toString();
    }
}
