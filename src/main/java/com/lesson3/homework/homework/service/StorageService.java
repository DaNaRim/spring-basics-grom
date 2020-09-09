package com.lesson3.homework.homework.service;

import com.lesson3.homework.homework.DAO.StorageDAO;
import com.lesson3.homework.homework.Exceptions.BadRequestException;
import com.lesson3.homework.homework.Exceptions.InternalServerException;
import com.lesson3.homework.homework.model.File;
import com.lesson3.homework.homework.model.Storage;

public class StorageService {

    public static Storage save(Storage storage) throws InternalServerException {
        return StorageDAO.save(storage);
    }

    public static void delete(long id) throws BadRequestException, InternalServerException {
        try {
            findById(id);
            StorageDAO.delete(id);
        } catch (BadRequestException e) {
            throw new BadRequestException("Cannot delete storage " + id + " : " + e.getMessage());
        }
    }

    public static Storage update(Storage storage) throws InternalServerException, BadRequestException {
        try {
            findById(storage.getId());
            checkFormatSupported(storage);
            return StorageDAO.update(storage);
        } catch (BadRequestException e) {
            throw new BadRequestException("Cannot update storage " + storage.getId() + " : " + e.getMessage());
        }
    }

    public static Storage findById(long id) throws BadRequestException, InternalServerException {
        return StorageDAO.findById(id);
    }

    private static void checkFormatSupported(Storage storage) throws BadRequestException {
        try {
            for (File file : storage.getFiles()) {
                FileService.checkFileFormat(storage, file);
            }
        } catch (BadRequestException e) {
            throw new BadRequestException("Files from this storage have a format that is no longer available");
        }
    }
}