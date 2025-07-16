package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.JcRecordDao;
import io.renren.modules.sys.entity.JcRecord;
import io.renren.modules.sys.service.JcRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jcRecordService")
public class JcRecordServiceImpl extends ServiceImpl<JcRecordDao, JcRecord> implements JcRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String info = (String)params.get("info");
        IPage<JcRecord> page = this.page(
            new Query<JcRecord>().getPage(params),
            new QueryWrapper<JcRecord>()
                .like(StringUtils.isNotBlank(info),"info", info)
        );

        return new PageUtils(page);
    }

}
