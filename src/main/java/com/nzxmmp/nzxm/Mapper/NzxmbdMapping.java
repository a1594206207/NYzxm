package com.nzxmmp.nzxm.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.Rediscache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
@CacheNamespace(implementation = com.nzxmmp.nzxm.entity.Rediscache.class,eviction = Rediscache.class)
public interface NzxmbdMapping extends BaseMapper<Nzxmbd> {

//    IPage<User> selectPageVo(Page<?> page, Integer state);





}
