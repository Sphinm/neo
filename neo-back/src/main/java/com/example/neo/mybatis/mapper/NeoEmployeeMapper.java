package com.example.neo.mybatis.mapper;

import com.example.neo.mybatis.model.NeoEmployee;
import com.example.neo.mybatis.model.NeoEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeoEmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    long countByExample(NeoEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int deleteByExample(NeoEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int insert(NeoEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int insertSelective(NeoEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    List<NeoEmployee> selectByExample(NeoEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    NeoEmployee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") NeoEmployee record, @Param("example") NeoEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") NeoEmployee record, @Param("example") NeoEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(NeoEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NeoEmployee record);
}