package com.llj.model.dto;

import com.llj.model.pojo.Suggest;
import com.llj.model.pojo.SuggestRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class SuggestDto extends Suggest {
    private List<SuggestRemark> remarkList;
}
