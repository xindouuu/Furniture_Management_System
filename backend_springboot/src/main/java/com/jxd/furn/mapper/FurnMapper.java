package com.jxd.furn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxd.furn.bean.Furn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FurnMapper extends BaseMapper<Furn> {
//    if have other methods, declare them in this interface
//    and configure their implementation in the corresponding Mapper.xml
}
