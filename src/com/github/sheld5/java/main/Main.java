package main;

import org.xml.sax.SAXException;
import util.Resources;
import util.StartNotFoundException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Initializes the application, switches between menu and game.
 */
public class Main {

    private static final String GAME_TITLE = "Racetrack";
    private static final int GAME_WIDTH = 555;
    private static final int GAME_HEIGHT = 555;

    private static JFrame frame;
    private static Menu menu;
    private static Game game;

    /**
     * The main method of the application.
     * Calls Resources.load() to load resources.
     * Calls initialization methods.
     * @see Resources
     * @see Main#initFrame()
     * @see Main#initMenu()
     * @param args
     */
    public static void main(String[] args) {
        Resources.load();
        initFrame();
        initMenu();
    }

    /**
     * Initializes JFrame for the application.
     */
    private static void initFrame() {
        frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setIconImage(Resources.windowIcon);
        frame.setVisible(true);
    }

    /**
     * Initializes menu.
     * @see Menu
     */
    private static void initMenu() {
        menu = new Menu();
        menu.setVisible(true);
        frame.add(menu);
        frame.revalidate();
    }

    /**
     * Initializes game and hides menu.
     * @see Game
     */
    static void startGame() {
        try {
            game = new Game(menu);
            game.setVisible(true);
            frame.add(game);
            menu.setVisible(false);
            frame.revalidate();
        } catch (IOException | SAXException | ParserConfigurationException | StartNotFoundException | IllegalArgumentException e) {
            System.out.println("An error occurred. The game could not be initiated.");
            e.printStackTrace();
        }
    }

    /**
     * Hides game and shows menu.
     */
    static void goToMenu() {
        frame.remove(menu);
        frame.add(menu);
        menu.setVisible(true);
        frame.remove(game);
    }

}
