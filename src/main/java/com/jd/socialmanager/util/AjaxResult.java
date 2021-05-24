package com.jd.socialmanager.util;

import lombok.Data;

@Data
public class AjaxResult {
    private boolean success;
    private String message;
    private String type;
}
