package org.te.file_system;

import java.util.List;

public interface FileSystem {

    void mkdir(String path);

    void createFile(String filePath, String content);

    List<String> ls(String path);
}
