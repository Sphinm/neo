package com.example.neo.mybatis.mapper;

import com.example.neo.mybatis.model.NeoSp;
import com.example.neo.mybatis.model.NeoSpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeoSpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    long countByExample(NeoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int deleteByExample(NeoSpExample example);

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
    int insert(NeoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int insertSelective(NeoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    List<NeoSp> selectByExample(NeoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    NeoSp selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") NeoSp record, @Param("example") NeoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") NeoSp record, @Param("example") NeoSpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(NeoSp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_sp
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NeoSp record);
}