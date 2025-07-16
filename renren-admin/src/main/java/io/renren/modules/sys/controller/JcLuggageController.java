package io.renren.modules.sys.controller;

import io.renren.common.utils.InvertCodeGenerator;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.JcLuggage;
import io.renren.modules.sys.service.JcLuggageService;
import io.renren.modules.sys.service.impl.JcLuggageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/JcLuggage")
public class JcLuggageController extends AbstractController {
    @Autowired
    private JcLuggageService JcLuggageService;

    @Autowired
     JcLuggageServiceImpl JcLuggageServiceImpe;
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = JcLuggageService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/getJcLuggageByType")
    public R getJcLuggageByType(){
        List<JcLuggage> list = JcLuggageServiceImpe.getJcLuggageByType("空闲");
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JcLuggage JcLuggage = JcLuggageService.getById(id);
        return R.ok().put("JcLuggage", JcLuggage);
    }
    @RequestMapping("/save")
    public R save(@RequestBody JcLuggage jcLuggage){
        jcLuggage.setStu("空闲");
        jcLuggage.setNum(InvertCodeGenerator.getRandomNickname(10));
        jcLuggage.setCreateTime(new Date());
        JcLuggageService.save(jcLuggage);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody JcLuggage jcLuggage){
        JcLuggageService.updateById(jcLuggage);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        JcLuggageService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}