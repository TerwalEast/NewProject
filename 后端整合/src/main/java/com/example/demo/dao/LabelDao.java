package com.example.demo.dao;

import com.example.demo.model.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LabelDao {

    @Select("select * from label")
    public List<Label> getLabels();

    @Select("select * from label where id = #{id}")
    public List<Label> getLabelById(@Param("id") Integer id);

    //通过text搜索标签id
    @Select("select id from label " +
            "where text=#{label}")
    public Integer getLabelIdByText(@Param("label")String label);
}
