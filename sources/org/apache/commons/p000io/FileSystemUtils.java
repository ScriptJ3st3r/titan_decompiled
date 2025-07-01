package org.apache.commons.p000io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Deprecated
/* loaded from: Titan.jar:org/apache/commons/io/FileSystemUtils.class */
public class FileSystemUtils {
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();
    private static final int INIT_PROBLEM = -1;
    private static final int OTHER = 0;
    private static final int WINDOWS = 1;
    private static final int UNIX = 2;
    private static final int POSIX_UNIX = 3;

    /* renamed from: OS */
    private static final int f2OS;

    /* renamed from: DF */
    private static final String f3DF;

    static {
        int os;
        String osName;
        String dfPath = "df";
        try {
            osName = System.getProperty("os.name");
        } catch (Exception e) {
            os = -1;
        }
        if (osName == null) {
            throw new IOException("os.name not found");
        }
        String osName2 = osName.toLowerCase(Locale.ENGLISH);
        if (osName2.contains("windows")) {
            os = WINDOWS;
        } else if (osName2.contains("linux") || osName2.contains("mpe/ix") || osName2.contains("freebsd") || osName2.contains("openbsd") || osName2.contains("irix") || osName2.contains("digital unix") || osName2.contains("unix") || osName2.contains("mac os x")) {
            os = UNIX;
        } else if (osName2.contains("sun os") || osName2.contains("sunos") || osName2.contains("solaris")) {
            os = POSIX_UNIX;
            dfPath = "/usr/xpg4/bin/df";
        } else if (osName2.contains("hp-ux") || osName2.contains("aix")) {
            os = POSIX_UNIX;
        } else {
            os = OTHER;
        }
        f2OS = os;
        f3DF = dfPath;
    }

    @Deprecated
    public static long freeSpace(String path) throws IOException {
        return INSTANCE.freeSpaceOS(path, f2OS, false, -1L);
    }

    @Deprecated
    public static long freeSpaceKb(String path) throws IOException {
        return freeSpaceKb(path, -1L);
    }

    @Deprecated
    public static long freeSpaceKb(String path, long timeout) throws IOException {
        return INSTANCE.freeSpaceOS(path, f2OS, true, timeout);
    }

    @Deprecated
    public static long freeSpaceKb() throws IOException {
        return freeSpaceKb(-1L);
    }

    @Deprecated
    public static long freeSpaceKb(long timeout) throws IOException {
        return freeSpaceKb(new File(".").getAbsolutePath(), timeout);
    }

    long freeSpaceOS(String path, int os, boolean kb, long timeout) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        switch (os) {
            case OTHER /* 0 */:
                throw new IllegalStateException("Unsupported operating system");
            case WINDOWS /* 1 */:
                return kb ? freeSpaceWindows(path, timeout) / FileUtils.ONE_KB : freeSpaceWindows(path, timeout);
            case UNIX /* 2 */:
                return freeSpaceUnix(path, kb, false, timeout);
            case POSIX_UNIX /* 3 */:
                return freeSpaceUnix(path, kb, true, timeout);
            default:
                throw new IllegalStateException("Exception caught when determining operating system");
        }
    }

    long freeSpaceWindows(String path, long timeout) throws IOException {
        String normPath = FilenameUtils.normalize(path, false);
        if (normPath == null) {
            throw new IllegalArgumentException(path);
        }
        if (normPath.length() > 0 && normPath.charAt(OTHER) != '\"') {
            normPath = "\"" + normPath + "\"";
        }
        String[] cmdAttribs = {"cmd.exe", "/C", "dir /a /-c " + normPath};
        List<String> lines = performCommand(cmdAttribs, Integer.MAX_VALUE, timeout);
        for (int i = lines.size() - WINDOWS; i >= 0; i--) {
            String line = lines.get(i);
            if (line.length() > 0) {
                return parseDir(line, normPath);
            }
        }
        throw new IOException("Command line 'dir /-c' did not return any info for path '" + normPath + "'");
    }

    long parseDir(String line, String path) throws IOException {
        int bytesStart = OTHER;
        int bytesEnd = OTHER;
        int j = line.length() - WINDOWS;
        while (true) {
            if (j < 0) {
                break;
            }
            if (Character.isDigit(line.charAt(j))) {
                bytesEnd = j + WINDOWS;
                break;
            }
            j--;
        }
        while (true) {
            if (j < 0) {
                break;
            }
            char c = line.charAt(j);
            if (!Character.isDigit(c) && c != ',' && c != '.') {
                bytesStart = j + WINDOWS;
                break;
            }
            j--;
        }
        if (j < 0) {
            throw new IOException("Command line 'dir /-c' did not return valid info for path '" + path + "'");
        }
        StringBuilder buf = new StringBuilder(line.substring(bytesStart, bytesEnd));
        int k = OTHER;
        while (k < buf.length()) {
            if (buf.charAt(k) == ',' || buf.charAt(k) == '.') {
                int i = k;
                k--;
                buf.deleteCharAt(i);
            }
            k += WINDOWS;
        }
        return parseBytes(buf.toString(), path);
    }

    long freeSpaceUnix(String path, boolean kb, boolean posix, long timeout) throws IOException {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Path must not be empty");
        }
        String flags = "-";
        if (kb) {
            flags = flags + "k";
        }
        if (posix) {
            flags = flags + "P";
        }
        String[] cmdAttribs = flags.length() > WINDOWS ? new String[]{f3DF, flags, path} : new String[]{f3DF, path};
        List<String> lines = performCommand(cmdAttribs, POSIX_UNIX, timeout);
        if (lines.size() < UNIX) {
            throw new IOException("Command line '" + f3DF + "' did not return info as expected for path '" + path + "'- response was " + lines);
        }
        String line2 = lines.get(WINDOWS);
        StringTokenizer tok = new StringTokenizer(line2, " ");
        if (tok.countTokens() < 4) {
            if (tok.countTokens() == WINDOWS && lines.size() >= POSIX_UNIX) {
                String line3 = lines.get(UNIX);
                tok = new StringTokenizer(line3, " ");
            } else {
                throw new IOException("Command line '" + f3DF + "' did not return data as expected for path '" + path + "'- check path is valid");
            }
        } else {
            tok.nextToken();
        }
        tok.nextToken();
        tok.nextToken();
        String freeSpace = tok.nextToken();
        return parseBytes(freeSpace, path);
    }

    long parseBytes(String freeSpace, String path) throws IOException {
        try {
            long bytes = Long.parseLong(freeSpace);
            if (bytes < 0) {
                throw new IOException("Command line '" + f3DF + "' did not find free space in response for path '" + path + "'- check path is valid");
            }
            return bytes;
        } catch (NumberFormatException ex) {
            throw new IOException("Command line '" + f3DF + "' did not return numeric data as expected for path '" + path + "'- check path is valid", ex);
        }
    }

    List<String> performCommand(String[] cmdAttribs, int max, long timeout) throws IOException {
        List<String> lines = new ArrayList<>(20);
        Process proc = null;
        try {
            try {
                Thread monitor = ThreadMonitor.start(timeout);
                Process proc2 = openProcess(cmdAttribs);
                InputStream in = proc2.getInputStream();
                OutputStream out = proc2.getOutputStream();
                InputStream err = proc2.getErrorStream();
                BufferedReader inr = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));
                for (String line = inr.readLine(); line != null && lines.size() < max; line = inr.readLine()) {
                    lines.add(line.toLowerCase(Locale.ENGLISH).trim());
                }
                proc2.waitFor();
                ThreadMonitor.stop(monitor);
                if (proc2.exitValue() != 0) {
                    throw new IOException("Command line returned OS error code '" + proc2.exitValue() + "' for command " + Arrays.asList(cmdAttribs));
                }
                if (lines.isEmpty()) {
                    throw new IOException("Command line did not return any info for command " + Arrays.asList(cmdAttribs));
                }
                inr.close();
                in.close();
                if (out != null) {
                    out.close();
                    out = OTHER;
                }
                if (err != null) {
                    err.close();
                    err = OTHER;
                }
                IOUtils.closeQuietly((InputStream) null);
                IOUtils.closeQuietly(out);
                IOUtils.closeQuietly(err);
                IOUtils.closeQuietly((Reader) null);
                if (proc2 != null) {
                    proc2.destroy();
                }
                return lines;
            } catch (InterruptedException ex) {
                throw new IOException("Command line threw an InterruptedException for command " + Arrays.asList(cmdAttribs) + " timeout=" + timeout, ex);
            }
        } catch (Throwable th) {
            IOUtils.closeQuietly((InputStream) null);
            IOUtils.closeQuietly((OutputStream) null);
            IOUtils.closeQuietly((InputStream) null);
            IOUtils.closeQuietly((Reader) null);
            if (OTHER != 0) {
                proc.destroy();
            }
            throw th;
        }
    }

    Process openProcess(String[] cmdAttribs) throws IOException {
        return Runtime.getRuntime().exec(cmdAttribs);
    }
}
