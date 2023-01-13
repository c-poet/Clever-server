package cn.cpoet.yunzhi.note.web.admin.service;

import cn.cpoet.yunzhi.note.comm.service.ISettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author CPoet
 */
@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final ISettingService iSettingService;
}
