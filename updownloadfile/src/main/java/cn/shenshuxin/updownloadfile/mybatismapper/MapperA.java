package cn.shenshuxin.updownloadfile.mybatismapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapperA {

    @Select("select concat('123_', #{some})")
    String querySomeThingForTest(@Param("some") String some);

}
