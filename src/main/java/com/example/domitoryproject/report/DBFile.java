package com.example.domitoryproject.report;

import javax.persistence.*;

@Entity
public class DBFile {
    @Id @GeneratedValue
    private Long id;

    private String fileName;

    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    private Report report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public DBFile() {
    }

    public DBFile(String fileName, byte[] data, Report report) {
        this.fileName = fileName;
        this.data = data;
        this.report = report;
    }
}
