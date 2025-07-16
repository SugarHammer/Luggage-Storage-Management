package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.JcLuggage;
import io.renren.modules.sys.entity.JcRecord;
import io.renren.modules.sys.entity.JcUserDeposit;
import io.renren.modules.sys.service.JcLuggageService;
import io.renren.modules.sys.service.JcRecordService;
import io.renren.modules.sys.service.JcUserDepositService;
import io.renren.modules.sys.service.impl.JcLuggageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/JcUserDeposit")
public class JcUserDepositController extends AbstractController {
    @Autowired
    private JcUserDepositService JcUserDepositService;

    @Autowired
    JcLuggageService jcLuggageService;

    @Autowired
    JcLuggageServiceImpl jcLuggageServiceImpl;

    @Autowired
    JcRecordService jcRecordService;

    //取出物品
    @RequestMapping("/take")
    public R list(@RequestParam("id") Integer id, @RequestParam("stu") String stu){
        JcUserDeposit  userDeposit= JcUserDepositService.getById(id);
        userDeposit.setLuggageStu("物品已取出");
        JcUserDepositService.saveOrUpdate(userDeposit);
        userDeposit.getLuggageNum();
        JcLuggage jcLuggage = jcLuggageServiceImpl.getJcLuggageByNum(userDeposit.getLuggageNum());
        jcLuggage.setStu("空闲");
        jcLuggageServiceImpl.saveOrUpdate(jcLuggage);
//        保存操作记录
        //        保存操作记录
        JcRecord jcRecord=new JcRecord();
        jcRecord.setUsername(getUser().getUsername());
        jcRecord.setJcTime(new Date());
        jcRecord.setLuggaeTitle(userDeposit.getLuggageTitle());
        DateFormat df2 = DateFormat.getDateTimeInstance();
        String date=df2.format(new Date());
        jcRecord.setInfo("用户【"+getUser().getUsername()+"】于【"+date+"】在"
                +"【"+userDeposit.getLuggageTitle()+"】"
                +"取出了"+"【"+userDeposit.getBz()+"】");
        jcRecordService.save(jcRecord);

        return R.ok();
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = JcUserDepositService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/Mylist")
    public R Mylist(@RequestParam Map<String, Object> params){
        params.put("userId",String.valueOf(getUserId()));
        PageUtils page = JcUserDepositService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/MylistTake")
    public R MylistTake(@RequestParam Map<String, Object> params){
        params.put("userId",String.valueOf(getUserId()));
        PageUtils page = JcUserDepositService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JcUserDeposit JcUserDeposit = JcUserDepositService.getById(id);
        return R.ok().put("JcUserDeposit", JcUserDeposit);
    }
    @RequestMapping("/save")
    public R save(@RequestBody JcUserDeposit JcUserDeposit){
        //修改柜子使用状态
        JcLuggage jcLuggage = jcLuggageServiceImpl.getJcLuggageByNum(JcUserDeposit.getLuggageNum());
        jcLuggage.setStu("已使用");
        jcLuggageService.saveOrUpdate(jcLuggage);

        JcUserDeposit.setCreateTime(new Date());
        JcUserDeposit.setUsername(getUser().getUsername());
        JcUserDeposit.setUserId(getUserId().intValue());
        JcUserDeposit.setAdminUser(jcLuggage.getCreateBy());
        JcUserDeposit.setLuggageStu("使用中");
        JcUserDepositService.save(JcUserDeposit);

//        保存操作记录
        JcRecord jcRecord=new JcRecord();
        jcRecord.setUsername(getUser().getUsername());
        jcRecord.setJcTime(new Date());
        jcRecord.setLuggaeTitle(JcUserDeposit.getLuggageTitle());
        DateFormat df2 = DateFormat.getDateTimeInstance();
         String date=df2.format(new Date());
        jcRecord.setInfo("用户【"+getUser().getUsername()+"】于【"+date+"】在"
                +"【"+JcUserDeposit.getLuggageTitle()+"】"
                +"存放了"+"【"+JcUserDeposit.getBz()+"】");
        jcRecordService.save(jcRecord);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody JcUserDeposit JcUserDeposit){
        JcUserDepositService.updateById(JcUserDeposit);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        JcUserDepositService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}