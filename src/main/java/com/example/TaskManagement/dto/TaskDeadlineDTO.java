package com.example.TaskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDeadlineDTO {
    private int taskId;
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
}
