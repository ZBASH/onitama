package view;

import core.Color;

import static core.Color.NONE;

// tile
class Tile {
    private char glyph;
    private Color foreground;
    private Color background;

    Tile(char glyph) {
        this(glyph, NONE, NONE);
    }

    Tile(char glyph, Color foreground, Color background) {
        this.glyph      = glyph;
        this.foreground = foreground;
        this.background = background;
    }

    String render() {
        return prefix() + glyph + suffix();
    }

    private String prefix() {
        return prefixBackground() + prefixForeground();
    }

    private String prefixForeground() {
        switch (foreground) {
            case RED:
                return "\033[0;31m";
            case BLUE:
                return "\033[0;34m";
            default:
                return "";
        }
    }

    private String prefixBackground() {
        switch (background) {
            case RED:
                return "\033[41;1m";
            case BLUE:
                return "\033[44;1m";
            default:
                return "";
        }
    }

    private String suffix() {
        if(foreground == NONE && background == NONE) {
            return "";
        }

        return "\033[0m";
    }
}
