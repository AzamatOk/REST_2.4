package org.rest.service.impl;

import org.rest.model.FileModel;
import org.rest.repository.hibernate.FileRepositoryImpl;
import org.rest.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    FileRepositoryImpl fileRepository = new FileRepositoryImpl();
    @Override
    public FileModel getById(Integer integer) {
        return fileRepository.getById(integer);
    }

    @Override
    public List<FileModel> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public FileModel save(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }

    @Override
    public FileModel update(FileModel fileModel) {
        return fileRepository.update(fileModel);
    }

    @Override
    public void deleteById(Integer integer) {
        fileRepository.deleteById(integer);
    }
}
