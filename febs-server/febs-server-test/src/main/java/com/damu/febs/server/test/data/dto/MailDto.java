package com.damu.febs.server.test.data.dto;

import lombok.Data;
import org.springframework.core.io.FileSystemResource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class MailDto implements Serializable {

    private List<String> address;

    private String content;

    private String subject;

    private Map<String, FileSystemResource> fileMap;

    private String reportDay;
}
