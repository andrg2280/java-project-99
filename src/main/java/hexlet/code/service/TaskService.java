package hexlet.code.service;

import hexlet.code.dto.TaskCreateDto;
import hexlet.code.dto.TaskDto;
import hexlet.code.dto.TaskParamsDto;
import hexlet.code.dto.TaskUpdateDto;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.model.Label;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.specification.TaskSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskSpecification taskSpecification;
    private final TaskStatusRepository taskStatusRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;


    public List<TaskDto> getAll(TaskParamsDto params) {
        var specification = taskSpecification.build(params);
        var tasks = taskRepository.findAll(specification);
        var result = tasks.stream()
                .map(taskMapper::map)
                .toList();
        return result;
    }

    public TaskDto findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        var result = taskMapper.map(task);
        return result;
    }

    public TaskDto create(TaskCreateDto dto) {
        var task = taskMapper.map(dto);

        User assignee = null;
        if (dto.getAssignee_id() != 0L) {
            assignee = userRepository.findById(dto.getAssignee_id()).orElse(null);
        }
        task.setAssignee(assignee);

        TaskStatus taskStatus = null;
        if (dto.getStatus() != null) {
            taskStatus = taskStatusRepository.findBySlug(dto.getStatus()).orElse(null);
        }
        task.setTaskStatus(taskStatus);

        Set<Label> labelSet = null;
        if (dto.getTaskLabelIds() != null) {
            labelSet = labelRepository.findByIdIn((dto.getTaskLabelIds())).orElse(null);
        }
        task.setLabels(labelSet);

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDto update(TaskUpdateDto data, Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + taskId + " not found."));

        taskMapper.update(data, task);

        User assignee = null;
        if (data.getAssignee_id() != null) {
            assignee = userRepository.findById(data.getAssignee_id().get()).orElse(null);
        }
        task.setAssignee(assignee);

        TaskStatus taskStatus = null;
        if (data.getStatus() != null) {
            taskStatus = taskStatusRepository.findBySlug(data.getStatus().get()).orElse(null);
            task.setTaskStatus(taskStatus);
        }

        Set<Label> labelSet = null;
        if (data.getTaskLabelIds() != null) {
            labelSet = labelRepository.findByIdIn((data.getTaskLabelIds()).get()).orElse(null);
            task.setLabels(labelSet);
        }


        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
