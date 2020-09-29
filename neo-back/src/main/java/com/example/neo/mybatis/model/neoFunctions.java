package com.example.neo.mybatis.model;

import java.io.Serializable;

public class neoFunctions implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_functions.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_functions.function_name
     *
     * @mbg.generated
     */
    private String functionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_functions.function_type
     *
     * @mbg.generated
     */
    private String functionType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_functions.function_info
     *
     * @mbg.generated
     */
    private String functionInfo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column neo_functions.is_locked
     *
     * @mbg.generated
     */
    private Boolean isLocked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_functions.id
     *
     * @return the value of neo_functions.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_functions.id
     *
     * @param id the value for neo_functions.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_functions.function_name
     *
     * @return the value of neo_functions.function_name
     *
     * @mbg.generated
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_functions.function_name
     *
     * @param functionName the value for neo_functions.function_name
     *
     * @mbg.generated
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_functions.function_type
     *
     * @return the value of neo_functions.function_type
     *
     * @mbg.generated
     */
    public String getFunctionType() {
        return functionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_functions.function_type
     *
     * @param functionType the value for neo_functions.function_type
     *
     * @mbg.generated
     */
    public void setFunctionType(String functionType) {
        this.functionType = functionType == null ? null : functionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_functions.function_info
     *
     * @return the value of neo_functions.function_info
     *
     * @mbg.generated
     */
    public String getFunctionInfo() {
        return functionInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_functions.function_info
     *
     * @param functionInfo the value for neo_functions.function_info
     *
     * @mbg.generated
     */
    public void setFunctionInfo(String functionInfo) {
        this.functionInfo = functionInfo == null ? null : functionInfo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column neo_functions.is_locked
     *
     * @return the value of neo_functions.is_locked
     *
     * @mbg.generated
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column neo_functions.is_locked
     *
     * @param isLocked the value for neo_functions.is_locked
     *
     * @mbg.generated
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
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
        neoFunctions other = (neoFunctions) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFunctionName() == null ? other.getFunctionName() == null : this.getFunctionName().equals(other.getFunctionName()))
            && (this.getFunctionType() == null ? other.getFunctionType() == null : this.getFunctionType().equals(other.getFunctionType()))
            && (this.getFunctionInfo() == null ? other.getFunctionInfo() == null : this.getFunctionInfo().equals(other.getFunctionInfo()))
            && (this.getIsLocked() == null ? other.getIsLocked() == null : this.getIsLocked().equals(other.getIsLocked()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFunctionName() == null) ? 0 : getFunctionName().hashCode());
        result = prime * result + ((getFunctionType() == null) ? 0 : getFunctionType().hashCode());
        result = prime * result + ((getFunctionInfo() == null) ? 0 : getFunctionInfo().hashCode());
        result = prime * result + ((getIsLocked() == null) ? 0 : getIsLocked().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neo_functions
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
        sb.append(", functionName=").append(functionName);
        sb.append(", functionType=").append(functionType);
        sb.append(", functionInfo=").append(functionInfo);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}