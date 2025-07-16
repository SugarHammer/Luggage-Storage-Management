
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.JcRecord;

import java.util.Map;

public interface JcRecordService extends IService<JcRecord> {

    PageUtils queryPage(Map<String, Object> params);
}

