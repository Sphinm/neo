package com.example.neo.mybatis.model.mapper;

import com.example.neo.mybatis.model.NeoFunctions;
import com.example.neo.mybatis.model.NeoFunctionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeoFunctionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    long countByExample(NeoFunctionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int deleteByExample(NeoFunctionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int insert(NeoFunctions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int insertSelective(NeoFunctions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    List<NeoFunctions> selectByExample(NeoFunctionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    NeoFunctions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") NeoFunctions record, @Param("example") NeoFunctionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") NeoFunctions record, @Param("example") NeoFunctionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(NeoFunctions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NeoFunctions record);
}