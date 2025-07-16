package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("jc_record")
public class JcRecord {
    @TableId
    private Long id;
    private String username;
    private String info;
    private Date jcTime;
    private String luggaeTitle;
    private String bz;
}

