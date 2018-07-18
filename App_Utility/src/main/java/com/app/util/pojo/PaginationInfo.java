package com.app.util.pojo;

/**
 * Page info to be sent with response
 * 
 */
public class PaginationInfo {

    private int pageSize;

    private int totalPages;

    private int totalRecords;

    private int curPage;

    /**
     * @return The pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize The pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return The totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * @param totalRecords The totalRecords to set
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * @return The curPage
     */
    public int getCurPage() {
        return curPage;
    }

    /**
     * @param curPage The curPage to set
     */
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PaginationInfo [pageSize=");
        builder.append(pageSize);
        builder.append(", totalPages=");
        builder.append(totalPages);
        builder.append(", totalRecords=");
        builder.append(totalRecords);
        builder.append(", curPage=");
        builder.append(curPage);
        builder.append("]");
        return builder.toString();
    }

}