package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.JcUserDepositDao;
import io.renren.modules.sys.entity.JcUserDeposit;
import io.renren.modules.sys.service.JcUserDepositService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jcUserDepositService")
public class JcUserDepositServiceImpl extends ServiceImpl<JcUserDepositDao, JcUserDeposit> implements JcUserDepositService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");
        String userId = (String)params.get("userId");
        IPage<JcUserDeposit> page = this.page(
            new Query<JcUserDeposit>().getPage(params),
            new QueryWrapper<JcUserDeposit>()
                .like(StringUtils.isNotBlank(username),"username", username)
                    .eq(StringUtils.isNotBlank(userId),"user_id", userId)

        );
        return new PageUtils(page);
    }

}
