package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2021/4/25.
 */
@Data
public class JcLuggage {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    private String title;
    private String num;
    private String bz;
    private Date createTime;
    private String createBy;
    private String ty;
    private String stu;

}
