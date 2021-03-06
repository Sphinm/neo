package com.example.neo.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class NeoFinance implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.company_id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.total_recharge
     *
     * @mbg.generated
     */
    private Double totalRecharge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.total_issued
     *
     * @mbg.generated
     */
    private Double totalIssued;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.balance
     *
     * @mbg.generated
     */
    private Double balance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.rate
     *
     * @mbg.generated
     */
    private Float rate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.status
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.creator_id
     *
     * @mbg.generated
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.update_id
     *
     * @mbg.generated
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_finance.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table neo_finance
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.id
     *
     * @return the value of neo_finance.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.id
     *
     * @param id the value for neo_finance.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.company_id
     *
     * @return the value of neo_finance.company_id
     *
     * @mbg.generated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.company_id
     *
     * @param companyId the value for neo_finance.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.total_recharge
     *
     * @return the value of neo_finance.total_recharge
     *
     * @mbg.generated
     */
    public Double getTotalRecharge() {
        return totalRecharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.total_recharge
     *
     * @param totalRecharge the value for neo_finance.total_recharge
     *
     * @mbg.generated
     */
    public void setTotalRecharge(Double totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.total_issued
     *
     * @return the value of neo_finance.total_issued
     *
     * @mbg.generated
     */
    public Double getTotalIssued() {
        return totalIssued;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.total_issued
     *
     * @param totalIssued the value for neo_finance.total_issued
     *
     * @mbg.generated
     */
    public void setTotalIssued(Double totalIssued) {
        this.totalIssued = totalIssued;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.balance
     *
     * @return the value of neo_finance.balance
     *
     * @mbg.generated
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.balance
     *
     * @param balance the value for neo_finance.balance
     *
     * @mbg.generated
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.rate
     *
     * @return the value of neo_finance.rate
     *
     * @mbg.generated
     */
    public Float getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.rate
     *
     * @param rate the value for neo_finance.rate
     *
     * @mbg.generated
     */
    public void setRate(Float rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.status
     *
     * @return the value of neo_finance.status
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.status
     *
     * @param status the value for neo_finance.status
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.creator_id
     *
     * @return the value of neo_finance.creator_id
     *
     * @mbg.generated
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.creator_id
     *
     * @param creatorId the value for neo_finance.creator_id
     *
     * @mbg.generated
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.create_date
     *
     * @return the value of neo_finance.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.create_date
     *
     * @param createDate the value for neo_finance.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.update_id
     *
     * @return the value of neo_finance.update_id
     *
     * @mbg.generated
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.update_id
     *
     * @param updateId the value for neo_finance.update_id
     *
     * @mbg.generated
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_finance.update_date
     *
     * @return the value of neo_finance.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_finance.update_date
     *
     * @param updateDate the value for neo_finance.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_finance
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NeoFinance other = (NeoFinance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getTotalRecharge() == null ? other.getTotalRecharge() == null : this.getTotalRecharge().equals(other.getTotalRecharge()))
            && (this.getTotalIssued() == null ? other.getTotalIssued() == null : this.getTotalIssued().equals(other.getTotalIssued()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_finance
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getTotalRecharge() == null) ? 0 : getTotalRecharge().hashCode());
        result = prime * result + ((getTotalIssued() == null) ? 0 : getTotalIssued().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_finance
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", totalRecharge=").append(totalRecharge);
        sb.append(", totalIssued=").append(totalIssued);
        sb.append(", balance=").append(balance);
        sb.append(", rate=").append(rate);
        sb.append(", status=").append(status);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}