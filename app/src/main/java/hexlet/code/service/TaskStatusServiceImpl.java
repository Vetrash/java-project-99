package hexlet.code.service;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.TaskStatusDTO;
import hexlet.code.dto.TaskStatusUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final TaskStatusMapper taskStatusMapper;

    @Override
    @Transactional
    public TaskStatusDTO createTaskStatus(TaskStatusCreateDTO taskStatusCreateDTO) {
        var taskStatus = taskStatusMapper.toEntity(taskStatusCreateDTO);
        var savedTaskStatus = taskStatusRepository.save(taskStatus);
        return taskStatusMapper.toDTO(savedTaskStatus);
    }

    @Override
    public List<TaskStatusDTO> getAll() {
        var taskStatuses = taskStatusRepository.findAll();
        return taskStatuses.stream()
                .map(taskStatusMapper::toDTO)
                .toList();
    }

    @Override
    public TaskStatusDTO getById(Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task status not found!"));
        return taskStatusMapper.toDTO(taskStatus);
    }

    @Override
    @Transactional
    public TaskStatusDTO updateTaskStatus(TaskStatusUpdateDTO taskStatusUpdateDTO, Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task status not found!"));

        if (taskStatusUpdateDTO.getName().isPresent()) {
            var name = taskStatusUpdateDTO.getName().orElse(null);
            if (name == null || name.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank");
            }
            taskStatus.setName(name);
        }

        if (taskStatusUpdateDTO.getSlug().isPresent()) {
            var slug = taskStatusUpdateDTO.getSlug().orElse(null);
            if (slug == null || slug.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Slug cannot be blank");
            }
            taskStatus.setSlug(slug);
        }

        var savedTaskStatus = taskStatusRepository.save(taskStatus);
        return taskStatusMapper.toDTO(savedTaskStatus);
    }

    @Override
    @Transactional
    public TaskStatusDTO deleteTaskStatus(Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task status not found!"));
        var taskStatusDTO = taskStatusMapper.toDTO(taskStatus);
        taskStatusRepository.delete(taskStatus);
        return taskStatusDTO;
    }
}
