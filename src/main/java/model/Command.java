package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {

    private final String message;       // #login Jean
    private String command;             // login
    private List<String> argumentList;  // Jean

    private final static String regex = " *#(\\S*)( +(\\S*))*";

    public Command(String message) {
        this.message = message;
        argumentList = new ArrayList<>();

        if (hasMatch(regex, message)) {
            List<String> allArgumentList = getGroup(regex, message);
            this.command = allArgumentList.get(1);
            this.argumentList = allArgumentList.subList(2, allArgumentList.size());

        }
    }

    private boolean hasMatch(String regex, String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    private List<String> getGroup(String regex, String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        List<String> groupList = new ArrayList<>();

        if (matcher.find()) {
            boolean hasNext = true;
            int i = 0;

            while (hasNext) {
                try {
                    groupList.add(matcher.group(i));
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    hasNext = false;
                }
            }
        }

        return groupList;
    }

    public String getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArgumentList() {
        return argumentList;
    }
}
