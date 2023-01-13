package cn.cpoet.yunzhi.note.web.comm.service;

import cn.cpoet.yunzhi.note.domain.model.I18n;
import cn.cpoet.yunzhi.note.domain.model.I18nItem;
import cn.cpoet.yunzhi.note.domain.model.query.QI18n;
import cn.cpoet.yunzhi.note.domain.model.query.QI18nItem;
import cn.cpoet.yunzhi.note.web.comm.dto.I18nQueryDTO;
import cn.cpoet.yunzhi.note.web.comm.vo.I18nMapVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CPoet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class I18nServiceImpl implements I18nService {
    @Override
    public I18nMapVO listI18n(I18nQueryDTO i18nQuery) {
        QI18n qi18n = new QI18n();
        if (StringUtils.hasText(i18nQuery.getScenes())) {
            qi18n.scenes.eq(i18nQuery.getScenes());
        }
        if (StringUtils.hasText(i18nQuery.getGroupName())) {
            qi18n.groupName.eq(i18nQuery.getGroupName());
        }
        List<I18n> i18ns = qi18n.findList();
        if (CollectionUtils.isEmpty(i18ns)) {
            return I18nMapVO.EMPTY;
        }
        Set<Long> i18nIds = i18ns
            .stream()
            .map(I18n::getId)
            .collect(Collectors.toSet());
        List<I18nItem> i18nItems = new QI18nItem()
            .i18nId.in(i18nIds)
            .locale.eq(i18nQuery.getLocale())
            .findList();
        Map<Long, String> contents = i18nItems
            .stream()
            .collect(Collectors.toMap(I18nItem::getI18nId, I18nItem::getContent));
        I18nMapVO i18nMap = new I18nMapVO();
        for (I18n i18n : i18ns) {
            i18nMap.put(i18n.getName(), contents.get(i18n.getId()));
        }
        return i18nMap;
    }
}
