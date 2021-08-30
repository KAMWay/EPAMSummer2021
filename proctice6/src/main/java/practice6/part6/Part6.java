package practice6.part6;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part6 {

    private static final Logger LOG = Logger.getLogger(Part6.class.getName());

    public static List<String> getInput(String fileName) {
        List<String> list = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            scanner.close();
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        }

        return list;
    }

    public static Map<String, String> setParam(String[] args) {
        Map<String, String> param = new HashMap<>();
        for (int i = 0; i < args.length / 2; i++) {
            if (2 * i + 1 < args.length && args[2 * i].contains("-") && !args[2 * i + 1].contains("-")) {
                param.put(selectParam(args[2 * i]), args[2 * i + 1]);
            }
        }
        return param;
    }

    private static String selectParam(String param) {
        if (param.equals("-i"))
            return "--input";
        if (param.equals("-t"))
            return "--task";

        return param;
    }

    private static void runSubtask(String fileName, String subtaskName) {
        if (subtaskName == null) subtaskName = "";
        if (fileName == null) subtaskName = "part6.txt";

        switch (subtaskName) {
            case "frequency":
                Part61.main(new String[]{fileName});
                break;
            case "length":
                Part62.main(new String[]{fileName});
                break;
            case "duplicates":
                Part63.main(new String[]{fileName});
                break;
            default:
                System.err.println("None select Subtask");
        }
    }

    public static void main(String[] args) {
        Map<String, String> param = setParam(args);
        runSubtask(param.get("--input"), param.get("--task"));
    }

}
