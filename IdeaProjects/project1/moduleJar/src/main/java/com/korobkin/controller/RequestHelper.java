package com.korobkin.controller;

import com.korobkin.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Java Developer on 03.12.2015.
 *
 * This class helps to get Command object by request URL
 */
public class RequestHelper {
    private static final Logger logger = Logger.getLogger("ControllerHelper");

    // Singleton
    private static RequestHelper instance = new RequestHelper();

    // Contains instance of commands with requestURI key.
    private Map<String, Command> commands;


    private RequestHelper() {

        // add to field commands all Commands that the AnnotationCommands class find in this package.
        commands = new AnnotationCommands().putCommandsToMap("com.korobkin.command", "/page");
        logger.debug("Commands: " + commands);
    }

    /**
     * This method returns instance of Command Class.
     *
     * This is not thread-safe. This method returns the same instance every time for the same address.
     * So it good practice don't use Fields of Command class because of multi-threading.
     *
     * @param address Request URL
     * @return single instance of Command class that handle this request.
     */
    public Command getCommand(String address) {
        logger.debug(address);

        Command command = commands.get(address);

        // Returns normal request for bad request URL.
        if (command == null) {
            return commands.get("/page/catalog");
        }

        return command;
    }

    /**
     * This method returns instance of Command Class.
     *
     * This is not thread-safe. This method returns the same instance every time for the same address.
     * So it good practice don't use Fields of Command class because of multi-threading.
     *
     * @param request HttpServletRequest with correct URI.
     * @return single instance of Command class that handle this request.
     */
    public Command getCommand(HttpServletRequest request) {
        return getCommand(request.getRequestURI());
    }

    /**
     *
     * @return Singleton
     */
    public static RequestHelper getInstance() {
        return instance;
    }
}
