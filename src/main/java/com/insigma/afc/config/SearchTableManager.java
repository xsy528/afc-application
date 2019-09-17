/* 
 * 日期：2011-2-12
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.config;

public class SearchTableManager {

    private static SearchTableConfig table;

    /**
     * @return the table
     */
    public static SearchTableConfig getTable() {
        if (table == null) {
            SearchTableReader.read("SearchTableConfig.xml");
        }
        return table;
    }

    /**
     * @param table
     *            the table to set
     */
    public static void setTable(SearchTableConfig table) {
        SearchTableManager.table = table;
    }
}
