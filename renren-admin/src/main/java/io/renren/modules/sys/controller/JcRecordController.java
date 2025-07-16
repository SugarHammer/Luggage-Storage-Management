package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.JcRecord;
import io.renren.modules.sys.service.JcRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/JcRecord")
public class JcRecordController extends AbstractController {
    @Autowired
    private JcRecordService JcRecordService;
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = JcRecordService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<JcRecord> list = JcRecordService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JcRecord JcRecord = JcRecordService.getById(id);
        return R.ok().put("JcRecord", JcRecord);
    }
    @RequestMapping("/save")
    public R save(@RequestBody JcRecord JcRecord){
        JcRecordService.save(JcRecord);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody JcRecord JcRecord){
        JcRecordService.updateById(JcRecord);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        JcRecordService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}