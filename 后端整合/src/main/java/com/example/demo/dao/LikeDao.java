package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeDao {

    @Delete("delete from likes where article_id=#{aid} and user_id=#{uid}")
    public void deleteLikeByUserArticle(@Param("aid")Integer aid,@Param("uid") Integer uid);
}
