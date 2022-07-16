package com.hw.tdd.extensions;

import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class TemporaryDirectoryExtension implements ParameterResolver, AfterEachCallback {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Path.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).getOrComputeIfAbsent("root", key -> createTempDir());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Path root = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("root", Path.class);
        if (root != null) {
            deleteRecursively(root);
        }
    }

    private Path createTempDir() {
        try {
            return Files.createTempDirectory("tempdir");
        } catch (IOException e) {
            throw new ParameterResolutionException("Couldn't create a temp dir", e);
        }
    }

    private void deleteRecursively(Path root) throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("Deleting file " + file);
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("Deleting dir " + dir);
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

}
