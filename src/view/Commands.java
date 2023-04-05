package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    REGISTER("register username (?<username>.+) password (?<password>.+)"),
    LOGIN("login username (?<username>.+) password (?<password>.+)"),
    EXIT("Exit"),
    LIST_USERS("list of users"),
    SCOREBOARD("scoreboard"),
    LOGOUT("logout"),
    PROFILE_MENU("profile menu"),
    SHOP_MENU("shop menu"),
    START_GAME("start game turns count (?<turnsCount>-?\\d+) username (?<username>\\S+)"),
    BACK("back"),
    CHANGE_PASSWORD("change password old password (?<oldPassword>.+) new password (?<newPassword>.+)"),
    INFO("Info"),
    REMOVE_CARD("remove from battle deck (?<cardName>.+)"),
    ADD_CARD("add to battle deck (?<cardName>.+)"),
    SHOW_CARD("show battle deck"),
    BUY_CARD("buy card (?<cardName>.+)"),
    SELL_CARD("sell card (?<cardName>.+)"),
    SHOW_HITPOINT("show the hitpoints left of my opponent"),
    SHOW_LINE("show line info (?<direction>.+)"),
    CARDS_TO_PLAY("number of cards to play"),
    MOVES_LEFT("number of moves left"),
    MOVE_TROOP("move troop in line (?<lineDirection>.+) and row (?<rowNumber>-?\\d+) (?<direction>.+)"),
    DEPLOY_TROOP("deploy troop (?<cardName>.+) in line (?<lineDirection>.+) and row (?<rowNumber>-?\\d+)"),
    DEPLOY_HEAL("deploy spell Heal in line (?<lineDirection>.+) and row (?<rowNumber>-?\\d+)"),
    DEPLOY_FIRE_BALL("deploy spell Fireball in line (?<lineDirection>.+)"),
    NEXT_TURN("next turn"),
    SHOW_CURRENT_MENU("show current menu");

    private final Pattern pattern;

    Commands(String command) {
        this.pattern = Pattern.compile(command);
    }

    static public Matcher getMatcher(String input, Commands commands) {
        return commands.pattern.matcher(input);
    }
}
