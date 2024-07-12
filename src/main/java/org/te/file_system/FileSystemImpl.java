package org.te.file_system;

import org.apache.commons.lang3.StringUtils;
import org.te.file_system.directory.Directory;

import java.util.ArrayList;
import java.util.List;


public class FileSystemImpl implements FileSystem {
    private Directory root = new Directory("root");

    public void mkdir(String path) {
        mkDirWithReturn(path, root);
    }

    private Directory mkDirWithReturn(String path,  Directory current) {

        if (StringUtils.isNotBlank(path)) {
            String parts[] = path.split("/");
            for (int i = 1; i < parts.length; i++) {

                current = current.getOrCreateDirectory(parts[i]);
            }
            return current;
        }
        return null;
    }

    public void createFile(String filePath, String content) {
        if (StringUtils.isNotBlank(filePath)) {
            String parts[] = filePath.trim().split("/");
            String fileName = null;
            Directory current = root;
            if (parts.length >= 3) {
                fileName = parts[parts.length - 1];

                    for (int i = 1; i < parts.length - 1; i++) {
                        current = mkDirWithReturn("/" + parts[i], current);
                    }

            }

            if (current != null &&
                    fileName != null) {
                current.addFile(fileName, content);
            }
        }

    }

    public List<String> ls(String path) {

        if (StringUtils.isNotBlank(path)) {
            Directory current = root;
            String parts[] = null;
            if (path != null) {
                parts = path.split("/");
                for (int i = 1; i < parts.length; i++) {
                    current = current.getDirectory(parts[i]);
                    if (current == null) {
                        break;
                    }
                }
            }
            if (current != null) {

                List<String> filesAndDir = new ArrayList<>();
                filesAndDir.addAll( current.toFiles());
                filesAndDir.addAll( current.toDir());
                return filesAndDir;

            }

            if (parts.length > 0) {
                List<String> file = new ArrayList<>();
                file.add(parts[parts.length - 1]);
                return file;
            }
        }
        return new ArrayList<>();
    }


}
