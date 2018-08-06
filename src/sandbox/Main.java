package sandbox;

import trees.Tree;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by Yonatan on 21/11/2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            String directory = "C:/Users/User/Desktop";
            Set<StandardOpenOption> writeOptions = new HashSet();
            writeOptions.add(StandardOpenOption.CREATE);
            writeOptions.add(StandardOpenOption.APPEND);

            /*ReadableByteChannel downloadChannel = Files.newByteChannel(Paths.get(directory + "/javaTestSource.txt"));
            SeekableByteChannel writeChannel = Files.newByteChannel(Paths.get(directory + "/javaTestTarget.txt"), writeOptions);
            ByteBuffer downloadBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            int bytesRead;

            do {
                bytesRead = downloadChannel.read(downloadBuffer);
                downloadBuffer.flip();

                while (downloadBuffer.hasRemaining()) {
                    writeBuffer.put(downloadBuffer.get());
                }
                downloadBuffer.clear();

                writeChannel.write(writeBuffer);
                writeBuffer.clear();
            } while (bytesRead != -1);*/

            FileChannel downloadChannel = FileChannel.open(Paths.get(directory + "/javaTestSource.txt"));
            FileChannel writeChannel = FileChannel.open(Paths.get(directory + "/javaTestTarget.txt"), writeOptions);
            downloadChannel.transferTo(0L, downloadChannel.size(), writeChannel);
            //downloadChannel.transferTo(0L, Long.MAX_VALUE, writeChannel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
