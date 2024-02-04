package com.example.lmageeach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {
    private boolean isSystemMessage;
    private String fromName;
    private Object message;
}
