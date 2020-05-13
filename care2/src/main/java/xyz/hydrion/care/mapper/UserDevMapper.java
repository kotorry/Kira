package xyz.hydrion.care.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xyz.hydrion.care.domain.ElderDev;
import xyz.hydrion.care.domain.User;

import java.util.List;

@Mapper
@Component(value = "userDevMapper")
public interface UserDevMapper {
    void insert(String userId, Integer devId);

    List<String> selectUserByDevId(Integer devId);

    List<ElderDev> selectDevByUserId(String userId);

    void delete(String userId, Integer devId);
    String getPassword(String username);
}
