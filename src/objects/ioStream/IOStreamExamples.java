package objects.ioStream;

import java.io.*;
import java.util.Arrays;

/**
 * Created by ksenia on 14.04.2017.
 */
public class IOStreamExamples {
    public static void main(String[] args) {
        String path1 = "D:\\javaProjectsTest\\dir1\\fileToCopy1.txt";
        String path2 = "D:\\javaProjectsTest\\dir1\\fileToCopy2.txt";
        String path3 = "D:\\javaProjectsTest\\dir1\\lorem.txt";

        File file1 = new File(path1);
        File file2 = new File(path2);
        File file3 = new File(path3);

        fileCopy(file1, file2);
        splitFile(file3, 2048);

        stream();

        File cryptFile1 = new File("D:\\javaProjectsTest\\dir1\\crypt1.txt");
        File encryptFile1 = new File("D:\\javaProjectsTest\\dir1\\encrypt1.txt");
        File cryptFile2 = new File("D:\\javaProjectsTest\\dir1\\crypt2.txt");
        File encryptFile2 = new File("D:\\javaProjectsTest\\dir1\\encrypt2.txt");
        File password = new File("D:\\javaProjectsTest\\dir1\\wp.txt");

        crypt(file3, cryptFile1, encryptFile1, "password");
        crypt(file3, cryptFile2, encryptFile2, file1);

        System.out.println("'0' = " + zeroBit(file1));

        filterStream(file3);
    }

    public static void fileCopy(File file1, File file2) {
        try (InputStream in = new FileInputStream(file1);
             OutputStream out = new FileOutputStream(file2, false)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void splitFile(File file, int size) {
        int fileQty = (int) Math.ceil(file.length() / (double) size);
        String outputDir = "D:\\javaProjectsTest\\dir1\\splitFile\\";
        File resultFile = new File(outputDir + "result.txt");

        try (InputStream in = new FileInputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            int cursor = 0;
            int bufCursor = 0;
            boolean bufHasRemain = false;
            for (int i = 0; i < fileQty; i++) {
                File fileOut = new File(outputDir + "fileOut" + i + ".txt");
                if (cursor == size) {
                    cursor = 0;
                }
                try (OutputStream out = new FileOutputStream(fileOut)) {
                    while (bufHasRemain && (cursor != size)) {
                        if ((size - cursor) >= (buf.length - bufCursor)) {
                            out.write(buf, bufCursor, buf.length - bufCursor);
                            cursor += (buf.length - bufCursor);
                            bufCursor = buf.length;
                            bufHasRemain = false;
                        } else {
                            out.write(buf, bufCursor, size - cursor);
                            bufCursor += (size - cursor);
                            cursor = size;
                        }
                    }
                    if (bufCursor == buf.length) {
                        bufCursor = 0;
                    }
                    if (cursor == size) {
                        continue;
                    }
                    if (bufCursor == 0) {
                        while ((len = in.read(buf)) != -1) {
                            if ((size - cursor) >= buf.length) {
                                out.write(buf, 0, len);
                                cursor += buf.length;
                                if (cursor == size) {
                                    break;
                                }
                            } else {
                                out.write(buf, 0, size - cursor);
                                bufCursor += (size - cursor);
                                cursor = size;
                                bufHasRemain = true;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (OutputStream out = new FileOutputStream(resultFile, true)) {

            for (int i = 0; i < fileQty; i++) {
                File fileIn = new File(outputDir + "fileOut" + i + ".txt");

                try (InputStream in = new FileInputStream(fileIn)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void stream() {
        File fileRandom = new File("D:\\javaProjectsTest\\dir1\\myiostreamRandom.txt");
        File fileSaw = new File("D:\\javaProjectsTest\\dir1\\myiostreamSaw.txt");
/*
        try (MyInputStream in1 = new MyInputStream(true);
             OutputStream out1 = new FileOutputStream(fileRandom);
             MyInputStream in2 = new MyInputStream(false);
             OutputStream out2 = new FileOutputStream(fileSaw)) {
            int b;
            while ((b = in1.read()) != -1) {
                out1.write(b);
            }
            while ((b = in2.read()) != -1) {
                out2.write(b);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/

        try (MyInputStream in1 = new MyInputStream(true);
             ByteArrayOutputStream out1 = new ByteArrayOutputStream();
             MyInputStream in2 = new MyInputStream(false);
             ByteArrayOutputStream out2 = new ByteArrayOutputStream()) {
            int b;
            while ((b = in1.read()) != -1) {
                out1.write(b);
            }
            while ((b = in2.read()) != -1) {
                out2.write(b);
            }
            System.out.println(Arrays.toString(out1.toByteArray()));
            System.out.println(Arrays.toString(out2.toByteArray()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static void crypt(File originalFile, File cryptFile, File encryptFile, String password) {
        File file1;
        File file2;

        for (int k = 0; k < 2; k++) {
            if (k == 0) {
                file1 = originalFile;
                file2 = cryptFile;
            } else {
                file1 = cryptFile;
                file2 = encryptFile;
            }
            try (InputStream in = new FileInputStream(file1);
                 OutputStream out = new FileOutputStream(file2)) {
                int nextj = 0;
                int len;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) != -1) {
                    for (int i = 0; i < len; i += password.length()) {
                        for (int j = nextj, z = 0; (i + z) < len && j < password.length(); j++, z++) {
                            buf[i + z] ^= password.toCharArray()[j];
                            nextj = j + 1;
                            if (nextj == password.length()) {
                                nextj = 0;
                            }
                        }
                    }
                    out.write(buf, 0, len);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public static void crypt(File originalFile, File cryptFile, File encryptFile, File password) {
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new FileReader(password)) {
            char[] buf = new char[512];
            int len;
            while ((len = reader.read(buf)) != -1) {
                sb.append(buf, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        crypt(originalFile, cryptFile, encryptFile, sb.toString());
    }

    public static int zeroBit(File file) {
        String bitString;
        byte[] mask = {-128, 64, 32, 16, 8, 4, 2, 1};
        StringBuilder sb = new StringBuilder();
        try (InputStream in = new FileInputStream(file)) {
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                for (int i = 0; i < len; i++) {
                    for (byte m : mask) {
                        if ((buf[i] & m) != m) {
                            sb.append('0');
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        System.out.println(sb);
        return sb.length();
    }

    public static void filterStream(File file) {
        File filecrypt = new File("D:\\javaProjectsTest\\dir1\\cryptOutputStream.txt");
        File fileencrypt = new File("D:\\javaProjectsTest\\dir1\\encryptInputStream.txt");

        byte[] password = "password".getBytes();

        try (InputStream in = new FileInputStream(file);
             OutputStream out = new MyCryptOutputStream(new FileOutputStream(filecrypt, false), password)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (InputStream in = new MyCryptInputStream(new FileInputStream(filecrypt), password);
             OutputStream out = new FileOutputStream(fileencrypt, false)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}