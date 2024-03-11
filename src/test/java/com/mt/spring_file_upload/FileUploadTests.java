package com.mt.spring_file_upload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUploadTests {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/files";
    @Test
    public void givenExistentPath_whenConfirmFileExists_thenCorrect(){
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.exists(p));

    }
    @Test
    public void givenNonexistentPath_whenConfirmsFileNotExists_thenCorrect() {
        Path p = Paths.get(FILE_PATH + "/inexistent_file.txt");

        assertTrue(Files.notExists(p));
    }

    @Test
    public void givenDirPath_whenConfirmsNotRegularFile_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertFalse(Files.isRegularFile(p));
    }

    @Test
    public void givenExistentDirPath_whenConfirmsReadable_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isReadable(p));
    }

    @Test
    public void givenExistentDirPath_whenConfirmsWritable_thenCorrect() {
        Path p = Paths.get(FILE_PATH);

        assertTrue(Files.isWritable(p));
    }

    @Test
    public void givenSameFilePaths_whenConfirmsIsSame_thenCorrect() throws IOException {
        Path p1 = Paths.get(FILE_PATH);
        Path p2 = Paths.get(FILE_PATH);

        assertTrue(Files.isSameFile(p1, p2));
    }

    @Test
    public void givenFilePath_whenCreatesNewFile_thenCorrect() throws IOException {
        String fileName = "my_file_" + UUID.randomUUID() + ".txt";
        Path p = Paths.get(FILE_PATH + "/" + fileName);
        assertFalse(Files.exists(p));

        Files.createFile(p);

        assertTrue(Files.exists(p));
    }
    @Test
    public void givenDirPath_whenCreatesNewDir_thenCorrect() throws IOException {
        String dirName = "myDir_" + UUID.randomUUID().toString();
        Path p = Paths.get(FILE_PATH + "/" + dirName);
        assertFalse(Files.exists(p));

        Files.createDirectory(p);

        assertTrue(Files.exists(p));
        assertFalse(Files.isRegularFile(p));
        assertTrue(Files.isDirectory(p));
    }
    @Test
    public void givenDirPath_whenFailsToCreateRecursively_thenCorrect() throws IOException {
        String dirName = "myDir_" + UUID.randomUUID().toString() + "/subdir";
        Path p = Paths.get(FILE_PATH + "/" + dirName);
        assertFalse(Files.exists(p));

        Files.createDirectory(p);
    }

    @Test
    public void givenDirPath_whenCreatesRecursively_thenCorrect() throws IOException {
        Path dir = Paths.get(
                FILE_PATH + "/myDir_" + UUID.randomUUID().toString());
        Path subdir = dir.resolve("subdir");
        assertFalse(Files.exists(dir));
        assertFalse(Files.exists(subdir));

        Files.createDirectories(subdir);

        assertTrue(Files.exists(dir));
        assertTrue(Files.exists(subdir));
    }


    @Test
    public void givenPath_whenCreatesTempFileWithDefaults_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH + "/");

        Files.createTempFile(p, null, null);

        assertTrue(Files.exists(p));
    }

    @Test
    public void givenNoFilePath_whenCreatesTempFileInTempDir_thenCorrect() throws IOException {
        Path p = Files.createTempFile(null, null);

        assertTrue(Files.exists(p));
    }

    @Test
    public void givenPath_whenDeletes_thenCorrect() throws IOException {
        Path p = Paths.get(FILE_PATH + "/fileToDelete.txt");
        assertFalse(Files.exists(p));
        Files.createFile(p);
        assertTrue(Files.exists(p));

        Files.delete(p);

        assertFalse(Files.exists(p));
    }

}

