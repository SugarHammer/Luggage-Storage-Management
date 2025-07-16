package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2021/4/25.
 */
@Data
public class JcUserDeposit {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    private Integer userId;
    private String username;
    private String phone;
    private String luggageNum;
    private String luggageTitle;
    private String luggageStu;
    private String bz;
    private String adminUser;
    private String ssjq;
    private Date createTime;
    private Date takesTime;

}
