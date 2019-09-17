/**
 * 
 */
package com.insigma.afc.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Column")
public class SearchTableColumn {

    @XStreamAsAttribute
    private String text;

    @XStreamAsAttribute
    private Integer index;

    @XStreamAsAttribute
    private String hql;

    @XStreamAsAttribute
    private String operationtable;

    @XStreamAsAttribute
    private String keyword;

    @XStreamAsAttribute
    private String versionnumberflag;

    @XStreamAsAttribute
    private Short componenttype;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the hql
     */
    public String getHql() {
        return hql;
    }

    /**
     * @param hql
     *            the hql to set
     */
    public void setHql(String hql) {
        this.hql = hql;
    }

    /**
     * @return the operationtable
     */
    public String getOperationtable() {
        return operationtable;
    }

    /**
     * @param operationtable
     *            the operationtable to set
     */
    public void setOperationtable(String operationtable) {
        this.operationtable = operationtable;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     *            the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the versionnumberflag
     */
    public String getVersionnumberflag() {
        return versionnumberflag;
    }

    /**
     * @param versionnumberflag
     *            the versionnumberflag to set
     */
    public void setVersionnumberflag(String versionnumberflag) {
        this.versionnumberflag = versionnumberflag;
    }

    /**
     * @return the componenttype
     */
    public Short getComponenttype() {
        return componenttype;
    }

    /**
     * @param componenttype
     *            the componenttype to set
     */
    public void setComponenttype(Short componenttype) {
        this.componenttype = componenttype;
    }

}
