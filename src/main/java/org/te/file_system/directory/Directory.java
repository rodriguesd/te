package org.te.file_system.directory;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Directory {
    private String name;

    private Map<String, Directory> dir = new HashMap<>();
    private Map<String, String> filesAndContent = new HashMap<>();

    public Directory(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Directory getDirectory(String name) {

        return dir.get(name);

    }

    public Directory getOrCreateDirectory(String name) {

        Directory directory = dir.get(name);
        if (directory == null) {
            directory = new Directory(name);
            dir.put(name, directory);
        }
        return directory;

    }

    public void addFile(String fileName, String contents) {
        if(StringUtils.isNotBlank(fileName))
        {
           filesAndContent.put(fileName, contents);

        }

    }

    public List<String> toFiles()
    {
        return new ArrayList(filesAndContent.keySet());
    }

    public List<String> toDir()
    {
        return new ArrayList(dir.keySet());
    }


}
