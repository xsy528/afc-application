package com.insigma.afc.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("TableColumns")
public class SearchTable {

    @XStreamImplicit
    private List<SearchTableColumn> searchTableColumns;

    @XStreamImplicit(itemFieldName = "SearchTableName")
    private List<String> searchTableName;

    /**
     * @return the searchTableColumns
     */
    public List<SearchTableColumn> getSearchTableColumns() {
        return searchTableColumns;
    }

    /**
     * @param searchTableColumns the searchTableColumns to set
     */
    public void setSearchTableColumns(List<SearchTableColumn> searchTableColumns) {
        this.searchTableColumns = searchTableColumns;
    }

    /**
     * @return the searchTableName
     */
    public List<String> getSearchTableName() {
        return searchTableName;
    }

    /**
     * @param searchTableName the searchTableName to set
     */
    public void setSearchTableName(List<String> searchTableName) {
        this.searchTableName = searchTableName;
    }

}
