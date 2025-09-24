package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {

    private final Path dataDir;
    private final Path backupRoot;

    public BackupService(Path dataDir, Path backupRoot) throws IOException {
        this.dataDir = dataDir;
        this.backupRoot = backupRoot;
        if (!Files.exists(backupRoot)) {
            Files.createDirectories(backupRoot);
        }
    }

    public Path createBackup() throws IOException {
        if (!Files.exists(dataDir)) {
            throw new IOException("Data folder does not exist. Please export data first.");
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = backupRoot.resolve("backup_" + timestamp);
        Files.createDirectories(backupDir);

        try (Stream<Path> files = Files.walk(dataDir)) {
            files.forEach(src -> {
                try {
                    Path dest = backupDir.resolve(dataDir.relativize(src));
                    if (Files.isDirectory(src)) {
                        Files.createDirectories(dest);
                    } else {
                        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return backupDir;
    }

    public long computeDirectorySize(Path dir) throws IOException {
        try (Stream<Path> files = Files.walk(dir)) {
            return files
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0L;
                        }
                    }).sum();
        }
    }
}
