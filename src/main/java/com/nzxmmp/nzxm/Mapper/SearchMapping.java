package com.nzxmmp.nzxm.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzxmmp.nzxm.entity.Rediscache;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@CacheNamespace(implementation = com.nzxmmp.nzxm.entity.Rediscache.class,eviction = Rediscache.class)
public interface SearchMapping extends BaseMapper<SearchNzxmbd> {


}
