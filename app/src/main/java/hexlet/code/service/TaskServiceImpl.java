package hexlet.code.service;

import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;
import hexlet.code.dto.TaskParamsDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskSpecification specBuilder;
    private final UserRepository userRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final LabelRepository labelRepository;

    @Override
    public List<TaskDTO> getAllTasks(TaskParamsDTO params) {
        var spec = specBuilder.build(params);
        var tasks = taskRepository.findAll(spec);
        return tasks.stream().map(taskMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public TaskDTO createTask(TaskCreateDTO dto) {
        var task = taskMapper.toEntity(dto);

        if (dto.getIndex() == null) {
            task.setIndex(taskRepository.getMaxIndex() + 1);
        }

        if (dto.getAssigneeId() != null) {
            task.setAssignee(userRepository.findById(dto.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assignee not found!")));
        }

        task.setTaskStatus(taskStatusRepository.findBySlug(dto.getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("Task status not found!")));

        if (dto.getTaskLabelIds() != null) {
            var labels = labelRepository.findAllById(dto.getTaskLabelIds());
            if (labels.size() != dto.getTaskLabelIds().size()) {
                throw new ResourceNotFoundException("One or more labels not found!");
            }
            task.setLabels(new HashSet<>(labels));
        } else {
            task.setLabels(new HashSet<>());
        }

        var savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found!"));
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public TaskDTO updateTask(TaskUpdateDTO dto, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found!"));

        // title, content, index — обновляем вручную через isPresent()
        if (dto.getTitle().isPresent()) {
            task.setTitle(dto.getTitle().orElse(null));
        }
        if (dto.getContent().isPresent()) {
            task.setContent(dto.getContent().orElse(null));
        }
        if (dto.getIndex().isPresent()) {
            task.setIndex(dto.getIndex().orElse(null));
        }

        // assignee — из репозитория
        if (dto.getAssigneeId().isPresent()) {
            var newAssigneeId = dto.getAssigneeId().orElse(null);
            if (newAssigneeId != null) {
                task.setAssignee(userRepository.findById(newAssigneeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Assignee not found!")));
            } else {
                task.setAssignee(null);
            }
        }

        // status — из репозитория
        if (dto.getStatus().isPresent()) {
            var slug = dto.getStatus().orElse(null);
            if (slug == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status cannot be null");
            }
            task.setTaskStatus(taskStatusRepository.findBySlug(slug)
                    .orElseThrow(() -> new ResourceNotFoundException("Task status not found!")));
        }

        // labels — из репозитория
        if (dto.getTaskLabelIds().isPresent()) {
            var ids = dto.getTaskLabelIds().orElse(null);
            if (ids == null || ids.isEmpty()) {
                task.setLabels(new HashSet<>());
            } else {
                var labels = labelRepository.findAllById(ids);
                if (labels.size() != ids.size()) {
                    throw new ResourceNotFoundException("One or more labels not found!");
                }
                task.setLabels(new HashSet<>(labels));
            }
        }

        var updatedTask = taskRepository.save(task);
        return taskMapper.toDTO(updatedTask);
    }

    @Override
    @Transactional
    public TaskDTO deleteTask(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found!"));
        var taskDTO = taskMapper.toDTO(task);
        taskRepository.delete(task);
        return taskDTO;
    }
}
