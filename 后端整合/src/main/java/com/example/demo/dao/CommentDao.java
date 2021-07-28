package com.example.demo.dao;

import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.model.Vo.CommentVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    @Select("select c.user_id,c.text,c.create_time,user.name,user.avatar_url " +
            "from comment c,customer user " +
            "where c.article_id=#{articleId} and c.user_id=user.id")
    @Results(
            value = {
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "text",property = "text",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = String.class,jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "article_id",property = "articleId",javaType = Integer.class,jdbcType = JdbcType.INTEGER)
            }
    )
    public List<Comment> getCommentByArticle(@Param("articleId")Integer articleId);

    @Select("select * from comment where user_id=#{uid}")
    @Results(
            value = {
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "text",property = "text",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = String.class,jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "article_id",property = "articleId",javaType = Integer.class,jdbcType = JdbcType.INTEGER)
            }
    )
    public List<Comment> getCommentUserPost(@Param("uid")Integer uid);

    @Select("select * from comment,article where comment.article_id=article.id and article.user_id=#{uid}")
    @Results(
            value = {
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "text",property = "text",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = String.class,jdbcType = JdbcType.TIMESTAMP),
                    @Result(column = "article_id",property = "articleId",javaType = Integer.class,jdbcType = JdbcType.INTEGER)
            }
    )
    public List<Comment> getCommentUserGet(@Param("uid")Integer uid);

    @Insert("insert into comment (user_id,article_id,text,create_time) values" +
            "(#{userId},#{articleId},#{desc},#{time})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Integer addComment(CommentVo vo);


}
