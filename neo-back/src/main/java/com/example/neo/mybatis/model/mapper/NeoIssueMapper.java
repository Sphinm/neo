package com.example.neo.mybatis.model.mapper;

import com.example.neo.mybatis.model.NeoIssue;
import com.example.neo.mybatis.model.NeoIssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NeoIssueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    long countByExample(NeoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int deleteByExample(NeoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int insert(NeoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int insertSelective(NeoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    List<NeoIssue> selectByExample(NeoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    NeoIssue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") NeoIssue record, @Param("example") NeoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") NeoIssue record, @Param("example") NeoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(NeoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NeoIssue record);
}