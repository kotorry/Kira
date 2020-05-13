package xyz.hydrion.care.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xyz.hydrion.care.domain.ElderDev;
import xyz.hydrion.care.domain.ElderDevStatus;

import java.util.List;

@Mapper
@Component(value = "elderDevMapper")
public interface ElderDevMapper {
    void insert(ElderDev elderDev);

    List<ElderDev> selectAll();

    ElderDev selectById(Integer id);

    List<ElderDevStatus> selectStatusByDevId(Integer devId);

    void insertStatus(ElderDevStatus status);


}
