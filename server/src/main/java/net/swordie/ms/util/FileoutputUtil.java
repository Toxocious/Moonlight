/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.swordie.ms.util;

import net.swordie.ms.client.Client;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.loaders.StringData;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class FileoutputUtil {

    // Logging output file
    public static final String Acc_Stuck = "Log_AccountStuck.rtf",
            Login_Error = "Log_Login_Error.rtf",
    // IP_Log = "Log_AccountIP.rtf",
    //GMCommand_Log = "Log_GMCommand.rtf",
    // Zakum_Log = "Log_Zakum.rtf",
    //Horntail_Log = "Log_Horntail.rtf",
    Pinkbean_Log = "Log_Pinkbean.rtf",
            ScriptEx_Log = "Log_Script_Except.txt",
            PacketEx_Log = "Log_Packet_Except.txt", // I cba looking for every error, adding this back in.
            Hacker_Log = "Log_Hacker.rtf",
            Movement_Log = "Log_Movement.txt",
            CommandEx_Log = "Log_Command_Except.txt" //PQ_Log = "Log_PQ.rtf"
                    ;
    // End
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdfGMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf_ = new SimpleDateFormat("yyyy-MM-dd");
    private static final String FILE_PATH = "logs/" + sdf.format(Calendar.getInstance().getTime()) + "/";// + sdf.format(Calendar.getInstance().getTime()) + "/"
    private static final String ERROR = "error/";

    static {
        sdfGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static void log(final String file, final String msg) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(("\n------------------------ " + CurrentReadable_Time() + " ------------------------\n").getBytes());
            out.write(msg.getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void logToFile(final String file, final String msg) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(msg.getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void logToFileIfNotExists(final String file, final String msg) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            if (!out.toString().contains(msg)) {
                out.write(msg.getBytes());
            }
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void outputFileError(final String file, final Throwable t) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(("\n------------------------ " + CurrentReadable_Time() + " ------------------------\n").getBytes());
            out.write(getString(t).getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void printError(final String name, final Throwable t, final String info) {
        FileOutputStream out = null;
        String file = FILE_PATH + ERROR + name;
        try {
            File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write((info + "\r\n").getBytes());
            out.write(getString(t).getBytes());
            out.write("\n---------------------------------\r\n".getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void printError(final String name, final String s) {
        FileOutputStream out = null;
        String file = FILE_PATH + ERROR + name;
        try {
            File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write(s.getBytes());
            //out.write("\n---------------------------------\n".getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static void print(final String name, final String s) {
        print(name, s, true);
    }

    public static void print(final String name, final String s, boolean line) {
        FileOutputStream out = null;
        String file = FILE_PATH + name;
        try {
            File outputFile = new File(file);
            if (outputFile.getParentFile() != null) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file, true);
            out.write(s.getBytes());
            if (line) {
                out.write("\r\n---------------------------------\r\n".getBytes());
            }
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static String CurrentReadable_Date() {
        return sdf_.format(Calendar.getInstance().getTime());
    }

    public static String CurrentReadable_Time() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String CurrentReadable_TimeGMT() {
        return sdfGMT.format(new Date());
    }

    public static String getString(final Throwable e) {
        String retValue = null;
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            retValue = sw.toString();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException ignore) {
            }
        }
        return retValue;
    }

    public static void spamLog(final String file, final Client c, InHeader inHeader, InPacket inPacket) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file, true);
            out.write(("\n------------------------ " + CurrentReadable_Time() + " ------------------------\n").getBytes());
            String log = String.format("[%s 0x%s] CharOrAccId : %s, Field : %s, Count : %d\nData : %s",
                    inHeader,
                    Integer.toHexString(inHeader.getValue()),
                    c.getChr() != null ? c.getChr().getName() : "Accid: " + c.getAccount().getId(),
                    c.getChr() != null ? StringData.getMapStringById(c.getChr().getFieldID()) + "(" + ")" : "-1",
                    c.clientPacketSpamCount,
                    inPacket);
            out.write(log.getBytes());
        } catch (IOException ess) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }
}
