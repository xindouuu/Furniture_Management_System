package com.jxd.furn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxd.furn.bean.Furn;
import com.jxd.furn.mapper.FurnMapper;
import com.jxd.furn.service.FurnService;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn> implements FurnService {
}
