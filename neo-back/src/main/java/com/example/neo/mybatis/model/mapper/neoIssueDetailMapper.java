package com.example.neo.mybatis.model.mapper;

import com.example.neo.mybatis.model.neoIssueDetail;
import com.example.neo.mybatis.model.neoIssueDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface neoIssueDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    long countByExample(neoIssueDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int deleteByExample(neoIssueDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int insert(neoIssueDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int insertSelective(neoIssueDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    List<neoIssueDetail> selectByExample(neoIssueDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    neoIssueDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") neoIssueDetail record, @Param("example") neoIssueDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") neoIssueDetail record, @Param("example") neoIssueDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(neoIssueDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue_detail
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(neoIssueDetail record);
}