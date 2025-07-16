
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.JcUserDeposit;

import java.util.Map;

public interface JcUserDepositService extends IService<JcUserDeposit> {

    PageUtils queryPage(Map<String, Object> params);
}

