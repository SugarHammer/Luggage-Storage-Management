package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.JcLuggageDao;
import io.renren.modules.sys.entity.JcLuggage;
import io.renren.modules.sys.service.JcLuggageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("jcLuggageService")
public class JcLuggageServiceImpl extends ServiceImpl<JcLuggageDao, JcLuggage> implements JcLuggageService {

   @Autowired
    JcLuggageService JcLuggageService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String)params.get("title");
        IPage<JcLuggage> page = this.page(
            new Query<JcLuggage>().getPage(params),
            new QueryWrapper<JcLuggage>()
                .like(StringUtils.isNotBlank(title),"title", title)
        );

        return new PageUtils(page);
    }

    public List<JcLuggage> getJcLuggageByType(String type) {
        QueryWrapper<JcLuggage> jcLuggageQueryWrapper = new QueryWrapper<>();
        jcLuggageQueryWrapper.eq("stu","空闲");
        List<JcLuggage> list = JcLuggageService.list(jcLuggageQueryWrapper);
        return list;
    }

    public JcLuggage getJcLuggageByNum(String num) {
        QueryWrapper<JcLuggage> jcLuggageQueryWrapper = new QueryWrapper<>();
        jcLuggageQueryWrapper.eq("num",num);
        JcLuggage jcLuggage = JcLuggageService.getOne(jcLuggageQueryWrapper);
        return jcLuggage;
    }

    public JcLuggage getJcLuggageByI(String num) {
        QueryWrapper<JcLuggage> jcLuggageQueryWrapper = new QueryWrapper<>();
        jcLuggageQueryWrapper.eq("num",num);
        JcLuggage jcLuggage = JcLuggageService.getOne(jcLuggageQueryWrapper);
        return jcLuggage;
    }



}
