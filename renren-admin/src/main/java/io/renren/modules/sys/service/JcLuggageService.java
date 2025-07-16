
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.JcLuggage;

import java.util.Map;

public interface JcLuggageService extends IService<JcLuggage> {

    PageUtils queryPage(Map<String, Object> params);
}

