package com.example.TaskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserDTO {
    private int taskId;
    private String taskDescription;
    private int userId;
    private String userName;
}
