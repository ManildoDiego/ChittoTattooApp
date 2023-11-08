package Windows;

import utils.BasicSubWindow;
import utils.Label;

public class Precios extends BasicSubWindow {
    private static class TextLabel extends Label {
        public TextLabel(String text, int x, int y, int fontSize) {
            super(text);
            setFont(getBasicFont(fontSize));
            setBounds(x, y, 181, 26);
            setForeground(colorVioleta);
        }
    }

    private void addText(String text, int x, int y) {
        var textLbl = new TextLabel(text, x, y, 16);
        getContentPane().add(textLbl);
    }

    public Precios() {
        super("Precios");
        getContentPane().setLayout(null);

        var lista = new TextLabel("Lista de Precios", getMiddleX(), 39, 20);
        getContentPane().add(lista);

        final var xPiercing = 30;
        final var xTattoo = 300;

        addText("Piercings:", xPiercing, 76);
        addText("-Helix 5k", xPiercing, 96);
        addText("-Septum 4k", xPiercing, 116);
        addText("-Tragus 4k", xPiercing, 136);
        addText("-Snake bites 8k", xPiercing, 156);
        addText("-Nostril 2k", xPiercing, 176);
        addText("-Bridge 5k", xPiercing, 196);
        addText("-Ceja 3k", xPiercing, 216);

        addText("Tattoos:", xTattoo, 76);
        addText("-Flash tattoo 5k", xTattoo, 96);
        addText("-Old school 15k", xTattoo, 116);
        addText("-Neo tradicional 25k", xTattoo, 136);
        addText("-Realismo 35k", xTattoo, 156);
        addText("-Japoneses 25k", xTattoo, 176);
        addText("-Letras 10", xTattoo, 196);
    }
}

