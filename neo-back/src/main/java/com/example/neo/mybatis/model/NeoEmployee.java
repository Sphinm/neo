package com.example.neo.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class NeoEmployee implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.company_id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.serial_number
     *
     * @mbg.generated
     */
    private String serialNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.id_verify
     *
     * @mbg.generated
     */
    private String idVerify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.tel
     *
     * @mbg.generated
     */
    private String tel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.id_check
     *
     * @mbg.generated
     */
    private Boolean idCheck;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.creator_id
     *
     * @mbg.generated
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.update_id
     *
     * @mbg.generated
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_employee.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.id
     *
     * @return the value of neo_employee.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.id
     *
     * @param id the value for neo_employee.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.company_id
     *
     * @return the value of neo_employee.company_id
     *
     * @mbg.generated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.company_id
     *
     * @param companyId the value for neo_employee.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.serial_number
     *
     * @return the value of neo_employee.serial_number
     *
     * @mbg.generated
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.serial_number
     *
     * @param serialNumber the value for neo_employee.serial_number
     *
     * @mbg.generated
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.name
     *
     * @return the value of neo_employee.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.name
     *
     * @param name the value for neo_employee.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.id_verify
     *
     * @return the value of neo_employee.id_verify
     *
     * @mbg.generated
     */
    public String getIdVerify() {
        return idVerify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.id_verify
     *
     * @param idVerify the value for neo_employee.id_verify
     *
     * @mbg.generated
     */
    public void setIdVerify(String idVerify) {
        this.idVerify = idVerify == null ? null : idVerify.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.tel
     *
     * @return the value of neo_employee.tel
     *
     * @mbg.generated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.tel
     *
     * @param tel the value for neo_employee.tel
     *
     * @mbg.generated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.id_check
     *
     * @return the value of neo_employee.id_check
     *
     * @mbg.generated
     */
    public Boolean getIdCheck() {
        return idCheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.id_check
     *
     * @param idCheck the value for neo_employee.id_check
     *
     * @mbg.generated
     */
    public void setIdCheck(Boolean idCheck) {
        this.idCheck = idCheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.remark
     *
     * @return the value of neo_employee.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.remark
     *
     * @param remark the value for neo_employee.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.creator_id
     *
     * @return the value of neo_employee.creator_id
     *
     * @mbg.generated
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.creator_id
     *
     * @param creatorId the value for neo_employee.creator_id
     *
     * @mbg.generated
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.create_date
     *
     * @return the value of neo_employee.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.create_date
     *
     * @param createDate the value for neo_employee.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.update_id
     *
     * @return the value of neo_employee.update_id
     *
     * @mbg.generated
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.update_id
     *
     * @param updateId the value for neo_employee.update_id
     *
     * @mbg.generated
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_employee.update_date
     *
     * @return the value of neo_employee.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_employee.update_date
     *
     * @param updateDate the value for neo_employee.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
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
        NeoEmployee other = (NeoEmployee) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getSerialNumber() == null ? other.getSerialNumber() == null : this.getSerialNumber().equals(other.getSerialNumber()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIdVerify() == null ? other.getIdVerify() == null : this.getIdVerify().equals(other.getIdVerify()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getIdCheck() == null ? other.getIdCheck() == null : this.getIdCheck().equals(other.getIdCheck()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIdVerify() == null) ? 0 : getIdVerify().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getIdCheck() == null) ? 0 : getIdCheck().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_employee
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
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", name=").append(name);
        sb.append(", idVerify=").append(idVerify);
        sb.append(", tel=").append(tel);
        sb.append(", idCheck=").append(idCheck);
        sb.append(", remark=").append(remark);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}