package net.swordie.ms.util;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.*;

/**
 * Created on 1/3/2018.
 */
public class MetaProgramming {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static void makeStateless() {
        String dir = ServerConstants.SCRIPT_DIR;
        String[] subDirs = new String[]{"field", "item", "npc", "portal", "quest", "reactor"};
        for (String subDir : subDirs) {
            File exactDir = new File(dir + "/" + subDir);
            for (File file : exactDir.listFiles()) {
                makeStateless(file);
            }
        }
    }

    public static void makeStateless(File file) {
        File outFile = new File(file.getAbsolutePath().replace("Swordie\\scripts", "Swordie\\stateless"));
        boolean fromRemoved = true;
        StringBuilder sb = new StringBuilder();
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                int tabs = 0;
                String line = scanner.nextLine();
                char[] chars = line.toCharArray();
                int spaceCount = 0;
                for (char c : chars) {
                    if (c == '#') {
                        break;
                    }
                    if (c == '\t') {
                        tabs++;
                    } else if (c == ' ') {
                        spaceCount++;
                        if (spaceCount == 4) {
                            spaceCount = 0;
                            tabs++;
                        }
                    } else {
                        break;
                    }
                }
                if (line.contains("status")) {
                } else if (line.contains("def init") || line.contains("def action(response")) {
                    fromRemoved = true;
                }  else {
                    if (line.contains("def")) {
                        fromRemoved = false;
                    }
                    line = line.replace("\t", "").replace("    ", "");
                    String newLine = "";
                    for (int i = 0; i < (fromRemoved ? tabs - 1 : tabs); i++) {
                        newLine += "    ";
                    }
                    newLine += line;
                    newLine = newLine.replace("sm.sendAskYesNo", "response = sm.sendAskYesNo");
                    newLine = newLine.replace("sm.sendAskAccept", "response = sm.sendAskAccept");
//                    System.out.println(newLine + "              (neededtabs = " + neededTabs + ", tabs = " + tabs + ", changedLast = " + changedLast + ", fromRemoved = " + fromRemoved + ")");
                    sb.append(newLine).append("\r\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter fos = new PrintWriter(outFile)){
            fos.write(sb.toString());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void fixTempStats() {
//        Invincible(0x10000000, 2),
        int plus = 5;
        File file = new File("D:/SwordieMS/Swordie/info txts/tempstats.txt");
        try (Scanner scanner = new Scanner(file)) {

            String s = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 4) {
                    String[] split = line.split("[(,]");
                    String name = split[0];
                    int num;
                    if (split[1].contains("x")) {
                        if (split[1].equalsIgnoreCase("0x80000000")) {
                            num = 0x80000000;
                        } else {
                            num = Integer.parseInt(split[1].replace("0x", ""), 16);
                        }
                    } else {
                        num = Integer.parseInt(split[1]);
                    }
                    int pos = Integer.parseInt(split[2].replace(" ", "").replace(")", ""));
                    if (num / plus <= 1) {
                        pos++;
                        num = num << (32 - plus);
                    } else {
                        num = num >>> plus;
                        if (num == 0) {
                            num = 0x80000000;
                            pos++;
                        }
                    }
                    log.debug(name + "(" + String.format("0x%X", num) + ", " + pos + "),");
                    if (num == 0x1 || num == 0x100 || num == 0x10000 || num == 0x1000000) {
                        log.debug("");
                    }
                }
            }
           log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeHeaders() {

        File file = new File("D:/SwordieMS/Swordie/src/headerText.txt");
        try (Scanner scanner = new Scanner(file)) {
            String s = "";
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("case") && line.contains(":")) {
                    String s2 = line.replace(":", "");
                    String s3 = s2.replaceAll(" ", "");
                    String s4 = s3.replace("case", "");

                    num = Integer.parseInt(s4);
                } else if (line.contains("::")) {
                    log.debug(line.replace("LOBYTE(v3) = ", ""));
                    String op = line.replace("LOBYTE(v3) = ", "").split("[(|:]")[2].replace(":", "");
                    StringBuilder newOp = new StringBuilder();
                    for (char c : op.toCharArray()) {
                        if (Character.isUpperCase(c)) {
                            newOp.append("_").append(c);
                        } else {
                            newOp.append(c);
                        }
                    }
                    String fin = newOp.toString().toUpperCase().substring(1).replace("ON_", "");
                    s += fin + "(" + num + "), \r\n";
                }
            }
            log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeCTS() {
        File file = new File("D:/SwordieMS/Swordie/src/decodeforlocal.txt");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder s = new StringBuilder();
            int num = 53;
            String lastSeen = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Ends for loop")) {
                    return;
                }
                if (line.contains("operator bool")) {
                    String[] first = line.split(",");
                    String op = first[1].split("[)]")[0].replace(" ", "");
                    CharacterTemporaryStat cts = Arrays.stream(CharacterTemporaryStat.values()).filter(ctsa -> ctsa.getBitPos() == Integer.parseInt(op))
                            .findFirst().orElse(null);
                    System.out.print(cts == null ? "Unk" + op + ",": cts + ",");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeInHeaders() {
        File file = new File("D:/SwordieMS/Swordie/src/ins.txt");
        int change = 0;
        try {
            Scanner scanner = new Scanner(file);
            String s = "";
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.endsWith("h")) {
                    line = line.substring(0, line.length() - 1);
                }
                String[] split = line.split(" = ");
                String op = split[0].replace(" ", "");
                log.debug(op);
                if (op.contains("DropEnterField")) {
                    change = 152;
                }
                num = Integer.parseInt(split[1], 16) + change;
                StringBuilder newOp = new StringBuilder();
                for (char c : op.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        newOp.append("_").append(c);
                    } else {
                        newOp.append(c);
                    }
                }
                String fin = newOp.toString().toUpperCase().substring(1).replaceFirst("ON_", "");
                s += fin + "(" + num + "), \r\n";
            }
            log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeRemoteOrder() {

        File file = new File("D:/MapleDev/Docs/Doodles/205_decode_for_remote.txt");
        List<CharacterTemporaryStat> order = new ArrayList<>();
        List<CharacterTemporaryStat> dups = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder s = new StringBuilder();
            int num = 53;
            String lastSeen = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("LOBYTE(v437) = CInPacket::Decode1(a3);")) {
                    break;
                }
                if (line.contains("SecondaryStat::HasCTS")) {
                    String bit = line.split(", ")[1].split("[)]")[0];
                    int val = Integer.parseInt(bit);
                    CharacterTemporaryStat cts = Arrays.stream(CharacterTemporaryStat.values())
                            .filter(c -> c.getBitPos() == val).findFirst().orElse(null);
                    if (cts == null) {
                        System.out.println("Could not find cts " + bit);
                    } else {
                        if (order.contains(cts)) {
                            dups.add(cts);
                        }
                        order.add(cts);
                    }

                }
            }
            for (CharacterTemporaryStat cts : order) {
                System.out.print(cts + ",");
            }
            System.out.println("\r\n");
            System.out.println("DUPLCATES:");
            for (CharacterTemporaryStat cts : dups) {
                System.out.println(cts + ", " + cts.getBitPos());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printCts() {
        File file2 = new File("D:\\MapleDev\\Docs\\Doodles\\205_decode_for_local.txt");
        List<Integer> arr1 = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("sub_8FB9B0")) {
                    String bitNum = line.split(", ")[1].split("[)]")[0];
                    System.out.print(bitNum + ",");
                    arr1.add(Integer.parseInt(bitNum));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean init = true;
        int diff = 0;
        List<CharacterTemporaryStat> ctss = CharacterTemporaryStat.ORDER;
        System.out.println();
        for (int i = 1; i < ctss.size(); i++) {
            int diff1 = ctss.get(i).getBitPos() - ctss.get(i - 1).getBitPos();
            int diff2 = arr1.get(i) - arr1.get(i -1);
            if (diff1 != diff2) {
                System.out.printf("Different diff! %d -> %d (cts %s -> %s) (diff = %d), New = %d -> %d (diff = %d)%n",
                        ctss.get(i - 1).getBitPos(), ctss.get(i).getBitPos(), ctss.get(i - 1), ctss.get(i), diff1,
                        arr1.get(i - 1), arr1.get(i), diff2);
            }
        }
    }

    public static void checkSetField() {
        File file = new File("D:\\MapleDev\\Docs\\Doodles\\setfield_v200.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("if") && line.contains(" & ") && !line.contains("MEMORY")) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void checkCtsFromString() {
        String str = "00 00 00 00 \n" +
                "80 60 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 80 01 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 0C 00 \n" +
                "00 02 00 00 \n" +
                "00 A1 00 00 \n" +
                "02 04 00 00 \n" +
                "80 00 00 00 \n" +
                "20 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 F0 0F 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00 \n" +
                "00 00 00 00";
        byte[] bArr = Util.getByteArrayByString(str);
        int[] iArr = new int[bArr.length / 4];
        for (int i = 0; i < bArr.length; i += 4) {
            iArr[i / 4] |= bArr[i];
            iArr[i / 4] |= bArr[i + 1] << 8;
            iArr[i / 4] |= bArr[i + 2] << 16;
            iArr[i / 4] |= bArr[i + 3] << 24;
        }
        for (int i = 0; i < iArr.length; i++) {
            int mask = iArr[i];
            for (CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                if (cts.getPos() == i && (cts.getVal() & mask) != 0) {
                    log.error(String.format("Containes stat %s", cts.toString()));
                }
            }
        }

    }

    public static void main(String[] args) {
        makeRemoteOrder();
    }
}
