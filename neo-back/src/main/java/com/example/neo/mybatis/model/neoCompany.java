package com.example.neo.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class neoCompany implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_name
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_tax
     *
     * @mbg.generated
     */
    private String companyTax;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_location
     *
     * @mbg.generated
     */
    private String companyLocation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_bank_name
     *
     * @mbg.generated
     */
    private String companyBankName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_bank_number
     *
     * @mbg.generated
     */
    private String companyBankNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_industry
     *
     * @mbg.generated
     */
    private String companyIndustry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_rate
     *
     * @mbg.generated
     */
    private String companyRate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.contact_name
     *
     * @mbg.generated
     */
    private String contactName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_fixed_tel
     *
     * @mbg.generated
     */
    private String companyFixedTel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.contact_tel
     *
     * @mbg.generated
     */
    private String contactTel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.recipient_name
     *
     * @mbg.generated
     */
    private String recipientName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.recipient_tel
     *
     * @mbg.generated
     */
    private String recipientTel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.recipient_address
     *
     * @mbg.generated
     */
    private String recipientAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_status
     *
     * @mbg.generated
     */
    private Boolean companyStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.creator_id
     *
     * @mbg.generated
     */
    private Integer creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.update_id
     *
     * @mbg.generated
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_company.company_type
     *
     * @mbg.generated
     */
    private Boolean companyType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table neo_company
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.id
     *
     * @return the value of neo_company.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.id
     *
     * @param id the value for neo_company.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_name
     *
     * @return the value of neo_company.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_name
     *
     * @param companyName the value for neo_company.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_tax
     *
     * @return the value of neo_company.company_tax
     *
     * @mbg.generated
     */
    public String getCompanyTax() {
        return companyTax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_tax
     *
     * @param companyTax the value for neo_company.company_tax
     *
     * @mbg.generated
     */
    public void setCompanyTax(String companyTax) {
        this.companyTax = companyTax == null ? null : companyTax.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_location
     *
     * @return the value of neo_company.company_location
     *
     * @mbg.generated
     */
    public String getCompanyLocation() {
        return companyLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_location
     *
     * @param companyLocation the value for neo_company.company_location
     *
     * @mbg.generated
     */
    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation == null ? null : companyLocation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_bank_name
     *
     * @return the value of neo_company.company_bank_name
     *
     * @mbg.generated
     */
    public String getCompanyBankName() {
        return companyBankName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_bank_name
     *
     * @param companyBankName the value for neo_company.company_bank_name
     *
     * @mbg.generated
     */
    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName == null ? null : companyBankName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_bank_number
     *
     * @return the value of neo_company.company_bank_number
     *
     * @mbg.generated
     */
    public String getCompanyBankNumber() {
        return companyBankNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_bank_number
     *
     * @param companyBankNumber the value for neo_company.company_bank_number
     *
     * @mbg.generated
     */
    public void setCompanyBankNumber(String companyBankNumber) {
        this.companyBankNumber = companyBankNumber == null ? null : companyBankNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_industry
     *
     * @return the value of neo_company.company_industry
     *
     * @mbg.generated
     */
    public String getCompanyIndustry() {
        return companyIndustry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_industry
     *
     * @param companyIndustry the value for neo_company.company_industry
     *
     * @mbg.generated
     */
    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry == null ? null : companyIndustry.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_rate
     *
     * @return the value of neo_company.company_rate
     *
     * @mbg.generated
     */
    public String getCompanyRate() {
        return companyRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_rate
     *
     * @param companyRate the value for neo_company.company_rate
     *
     * @mbg.generated
     */
    public void setCompanyRate(String companyRate) {
        this.companyRate = companyRate == null ? null : companyRate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.contact_name
     *
     * @return the value of neo_company.contact_name
     *
     * @mbg.generated
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.contact_name
     *
     * @param contactName the value for neo_company.contact_name
     *
     * @mbg.generated
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_fixed_tel
     *
     * @return the value of neo_company.company_fixed_tel
     *
     * @mbg.generated
     */
    public String getCompanyFixedTel() {
        return companyFixedTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_fixed_tel
     *
     * @param companyFixedTel the value for neo_company.company_fixed_tel
     *
     * @mbg.generated
     */
    public void setCompanyFixedTel(String companyFixedTel) {
        this.companyFixedTel = companyFixedTel == null ? null : companyFixedTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.contact_tel
     *
     * @return the value of neo_company.contact_tel
     *
     * @mbg.generated
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.contact_tel
     *
     * @param contactTel the value for neo_company.contact_tel
     *
     * @mbg.generated
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.recipient_name
     *
     * @return the value of neo_company.recipient_name
     *
     * @mbg.generated
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.recipient_name
     *
     * @param recipientName the value for neo_company.recipient_name
     *
     * @mbg.generated
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName == null ? null : recipientName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.recipient_tel
     *
     * @return the value of neo_company.recipient_tel
     *
     * @mbg.generated
     */
    public String getRecipientTel() {
        return recipientTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.recipient_tel
     *
     * @param recipientTel the value for neo_company.recipient_tel
     *
     * @mbg.generated
     */
    public void setRecipientTel(String recipientTel) {
        this.recipientTel = recipientTel == null ? null : recipientTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.recipient_address
     *
     * @return the value of neo_company.recipient_address
     *
     * @mbg.generated
     */
    public String getRecipientAddress() {
        return recipientAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.recipient_address
     *
     * @param recipientAddress the value for neo_company.recipient_address
     *
     * @mbg.generated
     */
    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress == null ? null : recipientAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_status
     *
     * @return the value of neo_company.company_status
     *
     * @mbg.generated
     */
    public Boolean getCompanyStatus() {
        return companyStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_status
     *
     * @param companyStatus the value for neo_company.company_status
     *
     * @mbg.generated
     */
    public void setCompanyStatus(Boolean companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.creator_id
     *
     * @return the value of neo_company.creator_id
     *
     * @mbg.generated
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.creator_id
     *
     * @param creatorId the value for neo_company.creator_id
     *
     * @mbg.generated
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.create_date
     *
     * @return the value of neo_company.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.create_date
     *
     * @param createDate the value for neo_company.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.update_id
     *
     * @return the value of neo_company.update_id
     *
     * @mbg.generated
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.update_id
     *
     * @param updateId the value for neo_company.update_id
     *
     * @mbg.generated
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.update_date
     *
     * @return the value of neo_company.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.update_date
     *
     * @param updateDate the value for neo_company.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_company.company_type
     *
     * @return the value of neo_company.company_type
     *
     * @mbg.generated
     */
    public Boolean getCompanyType() {
        return companyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_company.company_type
     *
     * @param companyType the value for neo_company.company_type
     *
     * @mbg.generated
     */
    public void setCompanyType(Boolean companyType) {
        this.companyType = companyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_company
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
        neoCompany other = (neoCompany) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCompanyTax() == null ? other.getCompanyTax() == null : this.getCompanyTax().equals(other.getCompanyTax()))
            && (this.getCompanyLocation() == null ? other.getCompanyLocation() == null : this.getCompanyLocation().equals(other.getCompanyLocation()))
            && (this.getCompanyBankName() == null ? other.getCompanyBankName() == null : this.getCompanyBankName().equals(other.getCompanyBankName()))
            && (this.getCompanyBankNumber() == null ? other.getCompanyBankNumber() == null : this.getCompanyBankNumber().equals(other.getCompanyBankNumber()))
            && (this.getCompanyIndustry() == null ? other.getCompanyIndustry() == null : this.getCompanyIndustry().equals(other.getCompanyIndustry()))
            && (this.getCompanyRate() == null ? other.getCompanyRate() == null : this.getCompanyRate().equals(other.getCompanyRate()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getCompanyFixedTel() == null ? other.getCompanyFixedTel() == null : this.getCompanyFixedTel().equals(other.getCompanyFixedTel()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getRecipientName() == null ? other.getRecipientName() == null : this.getRecipientName().equals(other.getRecipientName()))
            && (this.getRecipientTel() == null ? other.getRecipientTel() == null : this.getRecipientTel().equals(other.getRecipientTel()))
            && (this.getRecipientAddress() == null ? other.getRecipientAddress() == null : this.getRecipientAddress().equals(other.getRecipientAddress()))
            && (this.getCompanyStatus() == null ? other.getCompanyStatus() == null : this.getCompanyStatus().equals(other.getCompanyStatus()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateId() == null ? other.getUpdateId() == null : this.getUpdateId().equals(other.getUpdateId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getCompanyType() == null ? other.getCompanyType() == null : this.getCompanyType().equals(other.getCompanyType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_company
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCompanyTax() == null) ? 0 : getCompanyTax().hashCode());
        result = prime * result + ((getCompanyLocation() == null) ? 0 : getCompanyLocation().hashCode());
        result = prime * result + ((getCompanyBankName() == null) ? 0 : getCompanyBankName().hashCode());
        result = prime * result + ((getCompanyBankNumber() == null) ? 0 : getCompanyBankNumber().hashCode());
        result = prime * result + ((getCompanyIndustry() == null) ? 0 : getCompanyIndustry().hashCode());
        result = prime * result + ((getCompanyRate() == null) ? 0 : getCompanyRate().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getCompanyFixedTel() == null) ? 0 : getCompanyFixedTel().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getRecipientName() == null) ? 0 : getRecipientName().hashCode());
        result = prime * result + ((getRecipientTel() == null) ? 0 : getRecipientTel().hashCode());
        result = prime * result + ((getRecipientAddress() == null) ? 0 : getRecipientAddress().hashCode());
        result = prime * result + ((getCompanyStatus() == null) ? 0 : getCompanyStatus().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateId() == null) ? 0 : getUpdateId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getCompanyType() == null) ? 0 : getCompanyType().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_company
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
        sb.append(", companyName=").append(companyName);
        sb.append(", companyTax=").append(companyTax);
        sb.append(", companyLocation=").append(companyLocation);
        sb.append(", companyBankName=").append(companyBankName);
        sb.append(", companyBankNumber=").append(companyBankNumber);
        sb.append(", companyIndustry=").append(companyIndustry);
        sb.append(", companyRate=").append(companyRate);
        sb.append(", contactName=").append(contactName);
        sb.append(", companyFixedTel=").append(companyFixedTel);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", recipientName=").append(recipientName);
        sb.append(", recipientTel=").append(recipientTel);
        sb.append(", recipientAddress=").append(recipientAddress);
        sb.append(", companyStatus=").append(companyStatus);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", companyType=").append(companyType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}