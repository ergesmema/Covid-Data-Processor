package com.company;

import java.util.*;

public class CommandLineParser {
    List <String> args;
    HashMap<String, String> map = new HashMap<>();

    public CommandLineParser(String[] arguments)
    {
        this.args = Arrays.asList(arguments);
        map();
    }

    public String getArgumentValue(String argumentName)
    {
        if(map.containsKey(argumentName.toUpperCase(Locale.ROOT)))
            return map.get(argumentName.toUpperCase()).replace("[", "").replace("]", "");
        else
            throw new IllegalArgumentException("The argument list is not valid. '" +argumentName+ "' is missing. Please check and try again!");
    }

    public void map()
    {
        args.stream()
                .filter(x -> args.get(args.indexOf(x)).startsWith("-"))
                .forEach(x -> map.put(args.get(args.indexOf(x)).replace("-", "").toUpperCase(), args.get(args.indexOf(x) + 1).toUpperCase()));
        }
    }



