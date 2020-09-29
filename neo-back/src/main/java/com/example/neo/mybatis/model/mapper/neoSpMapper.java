package com.example.neo.mybatis.model.mapper;

import com.example.neo.mybatis.model.neoSp;
import com.example.neo.mybatis.model.neoSpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface neoSpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    long countByExample(neoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int deleteByExample(neoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int insert(neoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int insertSelective(neoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    List<neoSp> selectByExample(neoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    neoSp selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") neoSp record, @Param("example") neoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") neoSp record, @Param("example") neoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(neoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(neoSp record);
}