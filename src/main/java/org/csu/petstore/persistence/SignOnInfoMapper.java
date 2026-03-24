package org.csu.petstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.petstore.entity.SignOnInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SignOnInfoMapper extends BaseMapper<SignOnInfo> {
}
