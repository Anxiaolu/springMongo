/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author huanlu
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageSize;
    private int currentPage;
    private int totalRecord;
    private int totalPage;
    private List<T> dataList;

    public Page() {
    }

    public Page(int pageNum, int pageSize, List<T> sourceList) {
        if (sourceList == null) {
            return;
        }
        this.totalRecord = sourceList.size();
        this.pageSize = pageSize;
        this.totalPage = this.totalRecord / this.pageSize;
        if (this.totalRecord % this.pageSize != 0) {
            this.totalPage = this.totalPage + 1;
        }
        this.totalPage = this.totalPage < pageNum ? this.totalPage : pageNum;
        //起始索引
        int fromIndex = this.pageSize * (this.currentPage - 1);
        //结束索引
        int toIndex = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
        this.dataList = sourceList.subList(fromIndex, toIndex);
    }

    public Page(int pageSize, int currentPage, int totalRecord, int totalPage, List<T> dataList) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
