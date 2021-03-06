package com.example.neo.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class NeoIssue implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.company_id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.order_number
     *
     * @mbg.generated
     */
    private String orderNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.task_name
     *
     * @mbg.generated
     */
    private String taskName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.amount
     *
     * @mbg.generated
     */
    private Double amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.status
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.rebate
     *
     * @mbg.generated
     */
    private Double rebate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.creator_id
     *
     * @mbg.generated
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.update_id
     *
     * @mbg.generated
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.provide_status
     *
     * @mbg.generated
     */
    private Boolean provideStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_issue.is_detele
     *
     * @mbg.generated
     */
    private Boolean isDetele;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.id
     *
     * @return the value of neo_issue.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.id
     *
     * @param id the value for neo_issue.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.company_id
     *
     * @return the value of neo_issue.company_id
     *
     * @mbg.generated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.company_id
     *
     * @param companyId the value for neo_issue.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.order_number
     *
     * @return the value of neo_issue.order_number
     *
     * @mbg.generated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.order_number
     *
     * @param orderNumber the value for neo_issue.order_number
     *
     * @mbg.generated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.task_name
     *
     * @return the value of neo_issue.task_name
     *
     * @mbg.generated
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.task_name
     *
     * @param taskName the value for neo_issue.task_name
     *
     * @mbg.generated
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.amount
     *
     * @return the value of neo_issue.amount
     *
     * @mbg.generated
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.amount
     *
     * @param amount the value for neo_issue.amount
     *
     * @mbg.generated
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.status
     *
     * @return the value of neo_issue.status
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.status
     *
     * @param status the value for neo_issue.status
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.rebate
     *
     * @return the value of neo_issue.rebate
     *
     * @mbg.generated
     */
    public Double getRebate() {
        return rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.rebate
     *
     * @param rebate the value for neo_issue.rebate
     *
     * @mbg.generated
     */
    public void setRebate(Double rebate) {
        this.rebate = rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.creator_id
     *
     * @return the value of neo_issue.creator_id
     *
     * @mbg.generated
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.creator_id
     *
     * @param creatorId the value for neo_issue.creator_id
     *
     * @mbg.generated
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.create_date
     *
     * @return the value of neo_issue.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.create_date
     *
     * @param createDate the value for neo_issue.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.update_id
     *
     * @return the value of neo_issue.update_id
     *
     * @mbg.generated
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.update_id
     *
     * @param updateId the value for neo_issue.update_id
     *
     * @mbg.generated
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.update_date
     *
     * @return the value of neo_issue.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.update_date
     *
     * @param updateDate the value for neo_issue.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.provide_status
     *
     * @return the value of neo_issue.provide_status
     *
     * @mbg.generated
     */
    public Boolean getProvideStatus() {
        return provideStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.provide_status
     *
     * @param provideStatus the value for neo_issue.provide_status
     *
     * @mbg.generated
     */
    public void setProvideStatus(Boolean provideStatus) {
        this.provideStatus = provideStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_issue.is_detele
     *
     * @return the value of neo_issue.is_detele
     *
     * @mbg.generated
     */
    public Boolean getIsDetele() {
        return isDetele;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_issue.is_detele
     *
     * @param isDetele the value for neo_issue.is_detele
     *
     * @mbg.generated
     */
    public void setIsDetele(Boolean isDetele) {
        this.isDetele = isDetele;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
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
        NeoIssue other = (NeoIssue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRebate() == null ? other.getRebate() == null : this.getRebate().equals(other.getRebate()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getProvideStatus() == null ? other.getProvideStatus() == null : this.getProvideStatus().equals(other.getProvideStatus()))
            && (this.getIsDetele() == null ? other.getIsDetele() == null : this.getIsDetele().equals(other.getIsDetele()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRebate() == null) ? 0 : getRebate().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getProvideStatus() == null) ? 0 : getProvideStatus().hashCode());
        result = prime * result + ((getIsDetele() == null) ? 0 : getIsDetele().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_issue
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
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", taskName=").append(taskName);
        sb.append(", amount=").append(amount);
        sb.append(", status=").append(status);
        sb.append(", rebate=").append(rebate);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", provideStatus=").append(provideStatus);
        sb.append(", isDetele=").append(isDetele);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}