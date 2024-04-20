package com.llj.model.vo;

import com.llj.model.pojo.HotActivity;
import lombok.Data;

@Data
public class HotActivityVO extends HotActivity {
    private Integer total; //参加人数
}
